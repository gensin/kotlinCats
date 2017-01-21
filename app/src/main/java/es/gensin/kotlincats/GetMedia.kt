package es.gensin.kotlincats

import android.content.Context
import es.gensin.kotlincats.MediaItem.Type.PHOTO
import es.gensin.kotlincats.MediaItem.Type.VIDEO
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.uiThread

/**
 * Created by pau on 21/01/17.
 */
object MediaProvider {
    private val thumbBase = "http://lorempixel.com/400/400/cats/"

    fun fetchMediaAsync(context: Context, callback: (List<MediaItem>) -> Unit) {
        doAsync {
            Thread.sleep(2000)
            val result = (1..10).map {
                MediaItem(it, context.getString(R.string.title) + " $it", "${thumbBase}$it", if (it % 3 == 0) VIDEO else PHOTO)
            }

            uiThread {
                callback(result)
            }
        }
    }
}
