package es.gensin.kotlincats

/**
 * Created by pau on 21/01/17.
 */
data class MediaItem(val title: String, val url: String, val type: Type) {

    enum class Type {
        PHOTO,
        VIDEO
    }
}