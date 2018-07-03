package com.androidatc.recyclerview

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.widget.Toast
import com.androidatc.recyclerview.adapters.MyAdapter
import com.androidatc.recyclerview.model.Item
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    val items = arrayListOf<Item>(
            Item(1, "CAFÉ LITERARIO", "https://s3.us-east-2.amazonaws.com/karuguasu/cache/08/31/083160211bc7947a6776109c78b944bf.jpg"),
            Item(2, "LONG BAR", "https://s3.us-east-2.amazonaws.com/karuguasu/cache/ac/03/ac032fbc5415cb9b8a002d41eba22862.jpg"),
            Item(3, "EL BAR", "https://s3.us-east-2.amazonaws.com/karuguasu/cache/9e/8f/9e8fb575b78598ad54e11418db2f77d8.jpg"),
            Item(4, "MOBY DICK", "https://s3.us-east-2.amazonaws.com/karuguasu/cache/2a/ed/2aed55904ad90a372b232fe305f911e0.jpg")
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        mainRecyclerView.layoutManager = LinearLayoutManager(this)
        mainRecyclerView.adapter = MyAdapter(items) {
            Toast.makeText(this, "Click en ${it.titulo}", Toast.LENGTH_LONG).show()
        }
    }
}
