package me.jorgecasariego.peliculas.fragments


import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.URLUtil
import android.widget.SearchView
import android.widget.Toast
import androidx.core.content.FileProvider
import androidx.fragment.app.Fragment
import es.voghdev.pdfviewpager.library.RemotePDFViewPager
import es.voghdev.pdfviewpager.library.adapter.PDFPagerAdapter
import es.voghdev.pdfviewpager.library.remote.DownloadFile
import es.voghdev.pdfviewpager.library.util.FileUtil
import kotlinx.android.synthetic.main.fragment_fragment4.*
import me.jorgecasariego.peliculas.BuildConfig
import java.io.File

// 1. Implementar DownloadFile.Listener
// Documentación: https://github.com/voghDev/PdfViewPager
class Fragment4 : Fragment(), DownloadFile.Listener {
    // otra url: https://www.cultura.gob.cl/wp-content/uploads/2014/01/un-cuento-al-dia-antologia.pdf
    //val urlCuentos = "http://10.10.13.82:8080/Expert360/Servicios/BalancesPru.pdf"
    val urlCuentos = "https://www.cultura.gob.cl/wp-content/uploads/2014/01/un-cuento-al-dia-antologia.pdf"

    lateinit var remotePDFViewPager: RemotePDFViewPager
    lateinit var adapter: PDFPagerAdapter
    lateinit var archivoPdf: File


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

        compartir.setOnClickListener {
            // 1. Creamos un nuevo Intent
            val intent = Intent(Intent.ACTION_VIEW)
            // 2. Seteamos el FLAG para dar permiso temporal a apps externas para usar nuestro FileProvider
            intent.flags = Intent.FLAG_GRANT_READ_URI_PERMISSION

            // 3. Generamos el URI
            //    El segundo parametro es el authority el cual nosotros definimos en el Manifest que sea igual
            //    a nuestro applicationId
            //    El ultimo parametro es nuestro archivo PDF el cual obtuvimos en caso de exito luego de abrir el PDF
            val uri = FileProvider.getUriForFile(requireContext(), BuildConfig.APPLICATION_ID, archivoPdf)

            // 4. En nuestro caso estamos abriendo un archivo PDF por lo que damos el MIME adecuado para PDFs
            intent.setDataAndType(uri, "application/pdf")

            // 5. Validamos que el dispositivo pueda abrir el archivo PDF
            val pm = activity!!.packageManager
            if (intent.resolveActivity(pm) != null) {
                startActivity(intent)
            }

        }

    }

    // PDF Viewer
    override fun onSuccess(url: String?, destinationPath: String?) {
        progressBar4.visibility = View.GONE
        // 3. Caso de exito
        adapter = PDFPagerAdapter(context, FileUtil.extractFileNameFromURL(url))
        pdfViewPager.adapter = adapter
        Log.d("URl archivo", "destinationPath: " + destinationPath)
        Log.d("URl archivo", "URL: " + FileUtil.extractFileNameFromURL(url))

        archivoPdf = File(destinationPath)
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
        if (adapter != null) {
            adapter.close()
        }

    }

}
