package vip.frendy.kwebviewext

import android.annotation.SuppressLint
import android.content.Context
import android.util.AttributeSet
import android.view.MotionEvent
import android.webkit.WebSettings
import android.webkit.WebView

/**
 * Created by frendy on 2017/7/24.
 */

class KWebView @JvmOverloads constructor(context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0) : WebView(context, attrs, defStyleAttr) {

    var isProceedTouchEvent = false

    init { init() }

    private fun init() {
        setWebSettings(this)
    }

    @SuppressLint("JavascriptInterface", "SetJavaScriptEnabled")
    private fun setWebSettings(webView: WebView) {
        val settings = webView.settings
        //设置webview自适应屏幕大小
        settings.useWideViewPort = true
        settings.loadWithOverviewMode = true
        //设置，可能的话使所有列的宽度不超过屏幕宽度
        settings.layoutAlgorithm = WebSettings.LayoutAlgorithm.NARROW_COLUMNS

        //启动javascript
        settings.javaScriptEnabled = true
        settings.builtInZoomControls = true
        settings.displayZoomControls = false

        //优化页面加载速度
        settings.setRenderPriority(WebSettings.RenderPriority.HIGH)
        settings.blockNetworkImage = true
        //设置缓存模式
        settings.cacheMode = WebSettings.LOAD_DEFAULT
        //设置H5的缓存打开,默认关闭
        settings.setAppCacheEnabled(true)
        // 开启 DOM storage API 功能
        settings.domStorageEnabled = true

        //js和android交互
//        settings.javaScriptCanOpenWindowsAutomatically = true
    }

    override fun onTouchEvent(event: MotionEvent?): Boolean {
        if(isProceedTouchEvent) {
            super.onTouchEvent(event)
            return false
        } else {
            return super.onTouchEvent(event)
        }
    }
}
