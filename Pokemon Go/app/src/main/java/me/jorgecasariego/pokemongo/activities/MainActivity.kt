package me.jorgecasariego.pokemongo.activities

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.widget.Toast
import com.xwray.groupie.GroupAdapter
import com.xwray.groupie.Item
import com.xwray.groupie.ViewHolder
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.pokemon_row.view.*
import me.jorgecasariego.pokemongo.R
import me.jorgecasariego.pokemongo.R.id.lista_pokemones
import me.jorgecasariego.pokemongo.models.Model
import me.jorgecasariego.pokemongo.network.PokemonApi
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {

    companion object {
        val ID_POKEMON = "ID_POKEMON"
    }

    // 1. Usando lazy lo que hacemos es inicializar la variable pokemonApiService recien una vez
    //    que se empiece a usar (en obtenerPokemones() )
    private val pokemonApiService by lazy {
        PokemonApi.create()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // 2. LLamamos al API para obtener los pokemones
        obtenerPokemones()
    }

    private fun obtenerPokemones() {
        val call= pokemonApiService.getPokemones()

        call.enqueue(object : Callback<Model.PokemonInfo>{
            override fun onFailure(call: retrofit2.Call<Model.PokemonInfo>?, t: Throwable?) {
                Toast.makeText(this@MainActivity, "Error al obtener pokemones", Toast.LENGTH_SHORT).show()
            }

            override fun onResponse(call: retrofit2.Call<Model.PokemonInfo>?, response: Response<Model.PokemonInfo>?) {
                if(response != null) {
                    val pokemones: Model.PokemonInfo? = response.body()
                    Log.d("TEST", "Cantidad de pokemones: " + pokemones?.count)

                    val adapter = GroupAdapter<ViewHolder>()

                    // Recorremos cada uno de los pokemones y agregamos a la lista
                    pokemones?.results?.forEach {
                        adapter.add(PokemonItem(it))
                    }

                    adapter.setOnItemClickListener { item, view ->
                        val pokemonItem = item as PokemonItem
                        val intent = Intent(this@MainActivity, DetalleActivity::class.java)
                        intent.putExtra(ID_POKEMON, pokemonItem.pokemon.url)
                        startActivity(intent)
                    }

                    lista_pokemones.adapter = adapter
                }
            }

        })
    }
}

class PokemonItem(val pokemon: Model.Pokemon): Item<ViewHolder>() {
    override fun getLayout(): Int {
        return R.layout.pokemon_row
    }

    override fun bind(viewHolder: ViewHolder, position: Int) {
        viewHolder.itemView.nombre.text = pokemon.name

    }

}
