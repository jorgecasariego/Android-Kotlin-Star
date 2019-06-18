package me.jorgecasariego.peliculas.fragments


import android.graphics.Bitmap
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.*
import android.widget.SearchView
import androidx.fragment.app.Fragment
import kotlinx.android.synthetic.main.fragment_fragment3.*
import me.jorgecasariego.peliculas.R

class Fragment3 : Fragment() {

    val url = "http://files.unicef.org/republicadominicana/Manual_de_Cuentos_y_fabulas.pdf"
    val urlGoogle = "http://www.google.com"
    val searchPath = "/search?q="

    companion object {
        fun newInstance() = Fragment3()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_fragment3, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Refresh
        swipe.setOnRefreshListener {
            webview.reload()
        }

        // serchView
        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener, androidx.appcompat.widget.SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                query.let {
                    if (URLUtil.isValidUrl(it)) {
                        webview.loadUrl(it)
                    } else {
                        webview.loadUrl("$urlGoogle$searchPath$it")
                    }
                }

                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                return false
            }

        })


        // WebView
        webview.webChromeClient  = object : WebChromeClient() {

        }

        webview.webViewClient = object  : WebViewClient() {
            override fun onPageStarted(view: WebView?, url: String?, favicon: Bitmap?) {
                super.onPageStarted(view, url, favicon)
                swipe.isRefreshing = true
                // Solo ponemos la url y no hacemos submit
                searchView.setQuery(url, false)
            }

            override fun onPageFinished(view: WebView?, url: String?) {
                super.onPageFinished(view, url)
                swipe.isRefreshing = false
            }

            override fun shouldOverrideUrlLoading(view: WebView?, request: WebResourceRequest?): Boolean {
                // con esto manejamos nosotros las nuevas cargas de las urls
                return false
            }
        }


        val settings = webview.settings
        settings.javaScriptEnabled = true

        webview.loadUrl(urlGoogle)

//        webview.settings.javaScriptEnabled = true
//        webview.settings.loadWithOverviewMode = true
//        webview.settings.useWideViewPort = true
//        webview.loadUrl(url)
//        webview.webViewClient = object: WebViewClient() {
//            override fun onPageStarted(view: WebView?, url: String?, favicon: Bitmap?) {
//                super.onPageStarted(view, url, favicon)
//                progressBar.visibility = View.VISIBLE
//            }
//            override fun shouldOverrideUrlLoading(view: WebView?, request: WebResourceRequest?): Boolean {
//                return false
//            }
//
//            override fun onPageFinished(view: WebView?, url: String?) {
//                super.onPageFinished(view, url)
//                progressBar.visibility = View.GONE
//            }
//        }


    }



}
