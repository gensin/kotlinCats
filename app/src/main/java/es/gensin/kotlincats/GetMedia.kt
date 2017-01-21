package es.gensin.kotlincats

import android.content.Context
import es.gensin.kotlincats.MediaItem.Type.PHOTO
import es.gensin.kotlincats.MediaItem.Type.VIDEO

/**
 * Created by pau on 21/01/17.
 */
object MediaProvider {
    private val thumbBase = "http://lorempixel.com/400/400/cats/"

    fun getMedia(context: Context): List<MediaItem> = (1..10).map { MediaItem(context.getString(R.string.title) + " $it", "${thumbBase}$it", if (it % 3 == 0) VIDEO else PHOTO) }
}
