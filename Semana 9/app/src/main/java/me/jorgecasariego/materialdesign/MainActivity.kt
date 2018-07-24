package me.jorgecasariego.materialdesign

import android.os.Bundle
import android.support.design.widget.NavigationView
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.Toolbar
import android.view.Gravity
import android.view.MenuItem
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.content_main.*

class MainActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setSupportActionBar(toolbar as Toolbar)
        supportActionBar?.setHomeAsUpIndicator(R.drawable.ic_menu)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        nav_view.setNavigationItemSelectedListener(this)
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        when(item?.itemId) {
            android.R.id.home -> drawer.openDrawer(Gravity.START)
        }

        return  true
    }

    override fun onNavigationItemSelected(menu: MenuItem): Boolean {

        when(menu.itemId) {
            R.id.menu_seccion1 -> mostrarMensaje("Opción 1")
            R.id.menu_seccion2 -> mostrarMensaje("Opción 2")
            R.id.menu_seccion3 -> mostrarMensaje("Opción 3")
        }

        menu.isChecked = true
        drawer.closeDrawers()

        return true
    }

    fun mostrarMensaje(mensaje: String) =
            Toast.makeText(this, mensaje, Toast.LENGTH_SHORT).show()

}
