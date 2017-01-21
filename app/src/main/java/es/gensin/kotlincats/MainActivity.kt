package es.gensin.kotlincats

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import es.gensin.kotlincats.MediaItem.Type
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    val adapter =  MediaAdapter(MediaProvider.getMedia(this)) { toast(it.title)}

    override fun onCreate(savedInstanceState: Bundle?): Unit {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        recycler.adapter = adapter
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.main_menu, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem):Boolean {
        adapter.data = MediaProvider.getMedia(this).filter {
            when(item.itemId) {
                R.id.filter_all -> true
                R.id.filter_photos -> it.type == Type.PHOTO
                R.id.filter_videos -> it.type == Type.VIDEO
                else -> false
            }
        }
        return true
    }
}