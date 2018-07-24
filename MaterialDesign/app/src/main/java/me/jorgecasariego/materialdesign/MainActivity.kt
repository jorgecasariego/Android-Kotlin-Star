package me.jorgecasariego.materialdesign

import android.os.Bundle
import android.support.design.widget.NavigationView
import android.support.v4.app.Fragment
import android.support.v4.view.GravityCompat
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.Toolbar
import android.view.MenuItem
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.content_main.*
import me.jorgecasariego.materialdesign.fragments.Fragment1
import me.jorgecasariego.materialdesign.fragments.Fragment2
import me.jorgecasariego.materialdesign.fragments.Fragment3

class MainActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setSupportActionBar(toolbar as Toolbar?)
        supportActionBar?.setHomeAsUpIndicator(R.drawable.ic_nav_menu)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        nav_view.setNavigationItemSelectedListener(this)
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        when(item.itemId) {
            R.id.menu_seccion_1 -> cambiarFragment(Fragment1.newInstance())
            R.id.menu_seccion_2 -> cambiarFragment(Fragment2.newInstance())
            R.id.menu_seccion_3 -> cambiarFragment(Fragment3.newInstance())
            else -> {
                mostrarMensaje("Click en otro lugar")
            }
        }
        item.setChecked(true)
        drawer.closeDrawers()

        return true
    }

    fun mostrarMensaje(mensaje: String) {
        Toast.makeText(this, mensaje, Toast.LENGTH_SHORT).show()
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        when(item?.itemId) {
            android.R.id.home -> drawer.openDrawer(GravityCompat.START)
        }

        return true
    }

    fun cambiarFragment(fragment: Fragment) {
        supportFragmentManager
                .beginTransaction()
                .replace(R.id.contenedor, fragment)
                .commit()
    }

}
