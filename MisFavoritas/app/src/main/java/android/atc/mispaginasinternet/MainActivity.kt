package android.atc.mispaginasinternet

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import kotlinx.android.synthetic.main.activity_main.*

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

        // TODO 13: Inicializamos el notificationManager

        // TODO 5: Hacemos que la pagina web se cargue dentro de la app
//        webView.webViewClient = object : WebViewClient() {
//            @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
//            override fun shouldOverrideUrlLoading(view: WebView?, request: WebResourceRequest?): Boolean {
//                val url: String = request?.url.toString()
//                view?.loadUrl(url)
//                return true
//            }
//
//            override fun shouldOverrideUrlLoading(view: WebView?, url: String?): Boolean {
//                view?.loadUrl(url)
//                return true
//            }
//
//            // TODO 8: Para que nuestro menu se pueda actualizar tenemos que llamar a invalidateOpcionsMenu()

//            // TODO 8: Para que nuestro menu se pueda actualizar tenemos que llamar a invalidateOpcionsMenu()
//        }

        // TODO 2:  Configuramos el webView
        // Habilitar javascript
        // cargar wikipedia como pagina principal
        // deshabilitar scroll horizontal

        // TODO 3: Habilitando Zoom

        // TODO 4: Probar que wikipedia se cargue al iniciar la aplicación
        // Verificar que la pagina se cargo en el navegador!!

    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onPrepareOptionsMenu(menu: Menu?): Boolean {
        Log.d("TEST", "LLamando a onPrepareOptionMenu")

        // TODO 11: Vemos si cambiar o no el color del bookmark

        // TODO 7: Deshabilitamos el boton de ir atras si ya no podemos ir atras

        // TODO 7: Deshabilitamos el boton para ir hacia adelante si ya no podemos ir hacia adelante

        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.action_bookmark -> {
                // TODO 9: Guardar las paginas favoritas

                // TODO 10: LLamamos al metodo que hace que nuestro menu se pueda actualizar y el color
                // del icono del bookmar pueda cambiar

                // TODO 14: Creamos la notificacion cada que se pulsa en el BookMark mostrando el mismo mensaje

            }
            R.id.action_back -> back()
            R.id.action_forward -> forward()
        }

        return super.onOptionsItemSelected(item)
    }

    // TODO 6: Implementamos la opción de ir hacia atras o adelante
    fun back() {

    }
    // TODO 6: Implementamos la opción de ir hacia atras o adelante
    fun forward() {
    }

    // TODO 12: Manejamos el boton de atras del dispositivo

}
