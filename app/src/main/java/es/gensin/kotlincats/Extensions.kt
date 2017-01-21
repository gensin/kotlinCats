package es.gensin.kotlincats

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.Toast
import com.squareup.picasso.Picasso

/**
 * Created by pau on 21/01/17.
 */

fun Context.toast(message: CharSequence, length: Int = Toast.LENGTH_LONG) {
    Toast.makeText(this, message, length).show()
}

fun ViewGroup.inflate(layout: Int, attachToRoot: Boolean = false): View {
    return LayoutInflater.from(this.context).inflate(layout, this, attachToRoot)
}

fun ImageView.loadUrl(url: String) {
    Picasso.with(this.context).load(url).into(this)
}

inline fun <reified T: Activity> Context.startActivity() {
    startActivity(Intent(this, T::class.java))
}

inline fun <reified T: View> View.find(id: Int): T {
    return findViewById(id) as T
}