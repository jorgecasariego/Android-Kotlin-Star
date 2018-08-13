package android.atc.mispaginasinternet

import android.app.*
import android.content.Context
import android.content.ContextWrapper
import android.content.Intent
import android.graphics.Color
import android.os.Build

class NotificationHelper(context: Context) : ContextWrapper(context)  {
    companion object {
        val FAVORITOS_CHANNEL = "mis_favoritos"
        // TODO 15: Agregar nuevo canal para mensajes directos
    }

    private val mNotificationManager: NotificationManager by lazy {
        getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
    }

    init {

        // Creamos un objeto channel para los favoritos
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val favoritosChannel = NotificationChannel(
                    FAVORITOS_CHANNEL,
                    getString(R.string.mis_favoritos),
                    NotificationManager.IMPORTANCE_DEFAULT)

            // Configuramos el canal con las preferencias basicas
            favoritosChannel.lightColor = Color.GREEN
            favoritosChannel.vibrationPattern = longArrayOf(100, 200, 300, 400, 500, 400, 500, 200, 500)
            favoritosChannel.setShowBadge(false)

            // Creamos el canal utilizando el notification manager
            mNotificationManager.createNotificationChannel(favoritosChannel)

            // TODO: 16. la tarea ahora es crear un mensaje directo con prioridad alta para cuando
            // TODO: 16. se borre un articulo del bookmark

        }
    }

    fun getNotificationFavoritos(titulo: String, cuerpo: String): Notification.Builder {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            return Notification.Builder(applicationContext, FAVORITOS_CHANNEL)
                    .setContentTitle(titulo)
                    .setContentText(cuerpo)
                    .setSmallIcon(smallIcon)
                    .setAutoCancel(true)
                    .setContentIntent(getPendingIntent(cuerpo))
        } else {
            return Notification.Builder(applicationContext)
                    .setContentTitle(titulo)
                    .setContentText(cuerpo)
                    .setSmallIcon(smallIcon)
                    .setAutoCancel(true)
                    .setContentIntent(getPendingIntent(cuerpo))
        }

    }

    //TODO 17: crear el metodo para enviar notificaciones directas
//    fun getNotificationDirectas(titulo: String, cuerpo: String): Notification.Builder {

//    }

    /**
     * Metodo para enviar la notificacion
     *
     * @param id           ID de la notificacion
     * *
     * @param notification notification object
     */
    fun notify(id: Int, notification: Notification.Builder) {
        mNotificationManager.notify(id, notification.build())
    }

    /**
     * Obtenemos el icono de nuestra app

     * @return El id del recurso del iconos
     */
    private val smallIcon: Int
        get() = android.R.drawable.stat_notify_chat

    /**
     * El objeto Stack builder contendrá una pila artificial para la actividad inicializada.
     * Esto va a asegurar que cuando pulsemos el botón de atras no salga de la aplicación y vaya
     * al Home Screen
     * Luego agregamos el Intent que inicia la actividad arriba de la pila
     */
    fun getPendingIntent(mensaje: String): PendingIntent {
        val openResultadoIntent = Intent(this, ResultadoActivity::class.java)
        openResultadoIntent.putExtra("Mensaje", mensaje)
        openResultadoIntent.addFlags(Intent.FLAG_ACTIVITY_MULTIPLE_TASK)
        val stackBuilder = TaskStackBuilder.create(this)
        stackBuilder.addParentStack(ResultadoActivity::class.java)
        stackBuilder.addNextIntent(openResultadoIntent)
        return stackBuilder.getPendingIntent(0, PendingIntent.FLAG_UPDATE_CURRENT)

    }
}