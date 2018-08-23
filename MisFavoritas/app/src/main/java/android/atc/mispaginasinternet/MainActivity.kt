package android.atc.mispaginasinternet

import android.graphics.Bitmap
import android.os.Build
import android.os.Bundle
import android.support.annotation.RequiresApi
import android.support.design.widget.Snackbar
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.webkit.WebResourceRequest
import android.webkit.WebView
import android.webkit.WebViewClient
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.content_main.*

class MainActivity : AppCompatActivity() {

    companion object {
        private val NOTIFICATION_FAVORITOS = 1100
        private val NOTIFICATION_REMOVER_FAVORITOS = 1200
    }

    /*
     * Notification Helper es un helper class para inicializar los canales y enviar las notificacione
     */
    private lateinit var mNotificationHelper: NotificationHelper

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)
        supportActionBar?.title = "Favoritos"

        // Inicializamos el notificationManager
        mNotificationHelper = NotificationHelper(this)

        // Hacemos que la pagina web se cargue dentro de la app
        webView.webViewClient = object : WebViewClient() {
            @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
            override fun shouldOverrideUrlLoading(view: WebView?, request: WebResourceRequest?): Boolean {
                val url: String = request?.url.toString()
                view?.loadUrl(url)
                return true
            }

            override fun shouldOverrideUrlLoading(view: WebView?, url: String?): Boolean {
                view?.loadUrl(url)
                return true
            }

            override fun onPageStarted(view: WebView?, url: String?, favicon: Bitmap?) {
                super.onPageStarted(view, url, favicon)
                invalidateOptionsMenu()
            }

            override fun onPageFinished(view: WebView?, url: String?) {
                super.onPageFinished(view, url)
                invalidateOptionsMenu()
            }
        }


        webView.settings.javaScriptEnabled = true
        webView.loadUrl("http://www.wikipedia.org")
        webView.isHorizontalScrollBarEnabled = false

        // Habilitando Zoom
        webView.settings.setSupportZoom(true)
        webView.settings.builtInZoomControls = true
        webView.settings.displayZoomControls = true

        // Probar que wikipedia se cargue al iniciar la aplicación
        // Verificar que la pagina se cargo en el navegador!!

    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onPrepareOptionsMenu(menu: Menu?): Boolean {
        Log.d("TEST", "LLamando a onPrepareOptionMenu")

        // Vemos si cambiar o no el color del bookmark
        if(Utils.isBookmarked(this, webView.url)) {
            Utils.tintMenuIcon(this, menu?.getItem(0), R.color.colorAccent)
        } else {
            Utils.tintMenuIcon(this, menu?.getItem(0), android.R.color.white)
        }

        // Deshabilitamos el boton de ir atras si ya no podemos ir atras
        if (!webView.canGoBack()) {
            menu?.findItem(R.id.action_back)?.isEnabled = false
            menu?.findItem(R.id.action_back)?.icon?.alpha = 130
        } else {
            menu?.findItem(R.id.action_back)?.isEnabled = true
            menu?.findItem(R.id.action_back)?.icon?.alpha = 255
        }

        // Deshabilitamos el boton para ir hacia adelante si ya no podemos ir hacia adelante
        if (!webView.canGoForward()) {
            menu?.findItem(R.id.action_forward)?.isEnabled = false
            menu?.findItem(R.id.action_forward)?.icon?.alpha = 130
        } else {
            menu?.findItem(R.id.action_forward)?.isEnabled = true
            menu?.findItem(R.id.action_forward)?.icon?.alpha = 255
        }

        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.action_bookmark -> {
                // Guardar las paginas favoritas
                Utils.bookmarkUrl(this, webView.url)
                val mensaje = if (Utils.isBookmarked(this, webView.url))
                    webView.title + " ha sido agregado a los favoritos" else
                    webView.title + " ha sido removido de los favoritos"

                Snackbar.make(contenedor, mensaje, Snackbar.LENGTH_LONG).show()

                // LLamamos al metodo que hace que nuestro menu se pueda actualizar y el color
                // del icono del bookmar pueda cambiar
                invalidateOptionsMenu()

                if (Utils.isBookmarked(this, webView.url)) {
                    // Creamos la notificacion cada que se pulsa en el BookMark mostrando el mismo mensaje
                    mNotificationHelper.notify(NOTIFICATION_FAVORITOS,
                            mNotificationHelper.getNotificationFavoritos("Favoritos", mensaje))
                } else {
                    // Creamos la notificacion cada que se pulsa en el BookMark mostrando el mismo mensaje
                    mNotificationHelper.notify(NOTIFICATION_REMOVER_FAVORITOS,
                            mNotificationHelper.getNotificationDirectas("Todo mal", mensaje))
                }

            }
            R.id.action_back -> back()
            R.id.action_forward -> forward()
        }

        return super.onOptionsItemSelected(item)
    }

    // Implementamos la opción de ir hacia atras o adelante
    fun back() {
        if (webView.canGoBack()){
            webView.goBack()
        }
    }

    // Implementamos la opción de ir hacia atras o adelante
    fun forward() {
        if (webView.canGoForward()) {
            webView.goForward()
        }
    }

    override fun onBackPressed() {

        if(webView.canGoBack()) {
            webView.goBack()
        } else {
            super.onBackPressed()
        }
    }

}
