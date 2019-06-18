package me.jorgecasariego.peliculas.fragments


import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.URLUtil
import android.widget.SearchView
import android.widget.Toast
import androidx.fragment.app.Fragment
import es.voghdev.pdfviewpager.library.RemotePDFViewPager
import es.voghdev.pdfviewpager.library.adapter.PDFPagerAdapter
import es.voghdev.pdfviewpager.library.remote.DownloadFile
import es.voghdev.pdfviewpager.library.util.FileUtil
import kotlinx.android.synthetic.main.fragment_fragment4.*

// 1. Implementar DownloadFile.Listener
// Documentación: https://github.com/voghDev/PdfViewPager
class Fragment4 : Fragment(), DownloadFile.Listener {
    // otra url: https://www.cultura.gob.cl/wp-content/uploads/2014/01/un-cuento-al-dia-antologia.pdf
    val urlCuentos = "http://files.unicef.org/republicadominicana/Manual_de_Cuentos_y_fabulas.pdf"

    lateinit var remotePDFViewPager: RemotePDFViewPager
    lateinit var adapter: PDFPagerAdapter

    companion object {
        fun newInstance() = Fragment4()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(me.jorgecasariego.peliculas.R.layout.fragment_fragment4, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        //2. Crear el objeto RemotePDFViewPager
        remotePDFViewPager = RemotePDFViewPager(context, urlCuentos, this)
        remotePDFViewPager.id = me.jorgecasariego.peliculas.R.id.pdfViewPager

        // serchView
        searchView4.setOnQueryTextListener(object : SearchView.OnQueryTextListener, androidx.appcompat.widget.SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                query.let {
                    if (URLUtil.isValidUrl(it)) {
                        remotePDFViewPager = RemotePDFViewPager(context, it, this@Fragment4)
                    }
                }

                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                return false
            }

        })

    }

    // PDF Viewer
    override fun onSuccess(url: String?, destinationPath: String?) {
        progressBar4.visibility = View.GONE
        // 3. Caso de exito
        adapter = PDFPagerAdapter(context, FileUtil.extractFileNameFromURL(url))
        pdfViewPager.adapter = adapter

    }

    override fun onFailure(e: Exception?) {
        progressBar4.visibility = View.GONE
        Log.e("PDFVIERWER", "Causa: " + e?.cause)
        Log.e("PDFVIERWER", "Mensaje: " + e?.message)
        Toast.makeText(context, "Fallo al cargar pdf", Toast.LENGTH_LONG).show()
    }

    override fun onProgressUpdate(progress: Int, total: Int) {
        progressBar4.visibility = View.VISIBLE
    }

    override fun onDestroy() {
        super.onDestroy()
        // No olvidarse de cerrar el adapter para liberar todos los recursos
        adapter.close()
    }

}
