package me.jorgecasariego.peliculas

import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.core.view.GravityCompat
import androidx.fragment.app.Fragment
import com.google.android.material.navigation.NavigationView
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.content_main.*
import me.jorgecasariego.peliculas.fragments.FavoritasFragment
import me.jorgecasariego.peliculas.fragments.Fragment1
import me.jorgecasariego.peliculas.fragments.Fragment2
import me.jorgecasariego.peliculas.fragments.Fragment3

class MainActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // 2019-1
        nav_view.setNavigationItemSelectedListener(this)

        // 2019-2
        setSupportActionBar(my_toolbar as Toolbar)
        supportActionBar?.setHomeAsUpIndicator(R.drawable.ic_nav_menu)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        mostrarFragment(Fragment1.newInstance())
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        when(item.itemId) {
            R.id.menu_seccion_1 -> mostrarFragment(Fragment1.newInstance())
            R.id.menu_seccion_2 -> mostrarFragment(Fragment2.newInstance())
            R.id.menu_seccion_3 -> mostrarFragment(Fragment3.newInstance())
            else -> {
                return true
            }
        }

        item.isChecked = true
        drawer.closeDrawers()

        return true
    }


    private fun mostrarFragment(fragment: Fragment) {
        supportFragmentManager
                .beginTransaction()
                .replace(R.id.contenedor, fragment)
                .commit()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        val inflater: MenuInflater = menuInflater
        inflater.inflate(R.menu.menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {

        when(item?.itemId) {
            R.id.ver_favoritos -> {
                mostrarFragment(FavoritasFragment.newInstance())
            }
            android.R.id.home -> drawer.openDrawer(GravityCompat.START)
        }


        return true
    }
}
