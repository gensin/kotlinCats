package es.gensin.kotlincats

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View
import kotlinx.android.synthetic.main.activity_detail.*
import kotlinx.android.synthetic.main.activity_main.*

internal class DetailActivity : AppCompatActivity() {

    companion object {
        val ITEM_ID = "itemID"
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        progress.visible()
        MediaProvider.fetchMediaAsync(this) { media ->
            val item = media.firstOrNull {
                it.id == intent.getIntExtra(ITEM_ID, -1)
            }
            if (item != null) {
                supportActionBar?.title = item.title
                detail_thumb.loadUrl(item.url)
                detail_video_indicator.visibility = when (item.type) {
                    MediaItem.Type.PHOTO -> View.GONE
                    MediaItem.Type.VIDEO -> View.VISIBLE
                }
            }
            progress.gone()
        }
    }

}
