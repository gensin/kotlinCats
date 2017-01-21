package es.gensin.kotlincats

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import es.gensin.kotlincats.MediaItem.Type
import kotlinx.android.synthetic.main.activity_main.*
import org.jetbrains.anko.startActivity

class MainActivity : AppCompatActivity() {

    val adapter =  MediaAdapter {
        startActivity<DetailActivity>(DetailActivity.ITEM_ID to it.id)
    }

    override fun onCreate(savedInstanceState: Bundle?): Unit {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        recycler.adapter = adapter

        progress.visible()
        MediaProvider.fetchMediaAsync(this) {
            updateData(it)
            progress.gone()
        }
    }

    private fun updateData(media: List<MediaItem>, itemId: Int = R.id.filter_all) {
        adapter.data = media.filter {
            when(itemId) {
                R.id.filter_all -> true
                R.id.filter_photos -> it.type == Type.PHOTO
                R.id.filter_videos -> it.type == Type.VIDEO
                else -> false
            }
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.main_menu, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem):Boolean {
        progress.visible()
        MediaProvider.fetchMediaAsync(this) {
            updateData(it, item.itemId)
            progress.gone()
        }
        return true
    }
}