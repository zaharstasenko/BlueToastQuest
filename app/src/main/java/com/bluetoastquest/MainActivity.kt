package com.bluetoastquest

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    override fun onStop() {
//        val toast = Toast.makeText(applicationContext, "", Toast.LENGTH_LONG)
//        val layout = LinearLayout(applicationContext)
//        val imageView = ImageView(applicationContext)
//
//        imageView.setImageDrawable(applicationContext?.getDrawable(R.drawable.grinka_5))
//        layout.addView(imageView)
//        toast.view = layout
//        toast.show()
        showToast()
        super.onStop()
    }

    class MyToast(context: Context) :  Toast(context) {

    }

    private fun showToast() {
        val inflater = LayoutInflater.from(this)
        val view = inflater.inflate(R.layout.toast_scan_error, null)

        val toast = Toast.makeText(this, "Foo", Toast.LENGTH_SHORT)

       (toast.view as LinearLayout).addView(view, 1)
        //toast.view = view
        toast.show()
    }
//
//    override fun onDestroy() {
//        Toast.makeText(this, "onDestroy()”", Toast.LENGTH_SHORT).show()
//        val toast = Toast.makeText(applicationContext, "", Toast.LENGTH_LONG)
//        val layout = LinearLayout(applicationContext)
//        val imageView = ImageView(applicationContext)
//
//        imageView.setImageDrawable(applicationContext?.getDrawable(R.drawable.grinka_5))
//        layout.addView(imageView)
//        toast.view = layout
//        toast.show()
//        super.onDestroy()
//        Toast.makeText(this, "onDestroy()”", Toast.LENGTH_SHORT).show()
//    }
}
