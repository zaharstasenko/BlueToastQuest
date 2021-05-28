package com.bluetoastquest.utils

import android.app.Application
import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LiveData
import androidx.lifecycle.observe
import com.bluetoastquest.R
import com.bluetoastquest.ScannerActivity
import com.bluetoastquest.di.commonModule
import com.bluetoastquest.di.questListModule
import com.google.zxing.integration.android.IntentIntegrator
import org.koin.android.ext.koin.androidContext
import org.koin.android.logger.AndroidLogger
import org.koin.core.context.startKoin

fun Application.setupDependencyInjection() {
    startKoin {
        logger(AndroidLogger())
        androidContext(this@setupDependencyInjection)
        modules(
            listOf(
                commonModule,
                questListModule
            )
        )
    }
}

inline fun <T> LifecycleOwner.bind(liveData: LiveData<T>, crossinline onChanged: (T) -> Unit) {
    liveData.observe(this) { onChanged.invoke(it) }
}

inline fun <T> LifecycleOwner.bindNotNull(
    liveData: LiveData<T>,
    crossinline onChanged: (T) -> Unit
) {
    liveData.observe(this) { if (it != null) onChanged.invoke(it) }
}

fun LifecycleOwner.bindVisibility(liveData: LiveData<Boolean>, view: View) {
    liveData.observe(this) { view.isVisible = it }
}

fun LifecycleOwner.bindText(liveData: LiveData<String>, textView: TextView) {
    liveData.observe(this) { textView.text = it }
}

fun LifecycleOwner.bindDrawable(liveData: LiveData<Int>, imageView: ImageView) {
    liveData.observe(this) { imageView.setImageDrawable(imageView.context.getDrawable(it)) }
}

fun ViewGroup.inflateView(resId: Int, attachToRoot: Boolean = false): View {
    return LayoutInflater.from(context).inflate(
        resId,
        this,
        attachToRoot
    )
}

fun View.onSingleClick(action: (() -> Unit)?) {
    if (action == null) {
        setOnClickListener(null)
    } else {
        setOnClickListener(OnSingleClickListener.init {
            action()
        })
    }
}

abstract class OnSingleClickListener : View.OnClickListener {
    private var mLastClickTime: Long = 0
    abstract fun onSingleClick(v: View?)

    override fun onClick(v: View) {
        val currentClickTime = System.currentTimeMillis()
        val elapsedTime = currentClickTime - mLastClickTime
        mLastClickTime = currentClickTime
        if (elapsedTime <= MINIMAL_CLICK_INTERVAL) return
        onSingleClick(v)
    }

    companion object {
        private const val MINIMAL_CLICK_INTERVAL: Long = 600

        fun init(onSingleClick: () -> Unit): OnSingleClickListener {
            return object : OnSingleClickListener() {
                override fun onSingleClick(v: View?) {
                    onSingleClick.invoke()
                }
            }
        }
    }
}

fun Intent?.toScanResult(
    requestCode: Int,
    resultCode: Int
) = IntentIntegrator.parseActivityResult(requestCode, resultCode, this)?.contents

fun Fragment.navigateToScanner() = IntentIntegrator.forSupportFragment(this).apply {
    setBeepEnabled(false)
    setPrompt("")
    captureActivity = ScannerActivity::class.java
    initiateScan()
}

fun Context.toast(textId: Int, length: Int = Toast.LENGTH_LONG) {
    Toast.makeText(this, textId, length).show()
}

fun Fragment.toast(textId: Int) = context?.toast(textId)

fun Fragment.showScannerError() = toast(R.string.scanner_error)