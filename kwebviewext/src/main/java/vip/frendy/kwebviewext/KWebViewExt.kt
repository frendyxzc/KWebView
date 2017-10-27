package vip.frendy.kwebviewext

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Bitmap
import android.graphics.drawable.Drawable
import android.util.AttributeSet
import android.view.KeyEvent
import android.view.View
import android.webkit.*
import android.widget.LinearLayout
import android.widget.ProgressBar
import vip.frendy.kwebviewext.interfaces.JSInterface

/**
 * Created by frendy on 2017/7/24.
 */
class KWebViewExt @JvmOverloads constructor(context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0) : LinearLayout(context, attrs, defStyleAttr) {

    internal var mWebView: KWebView? = null
    internal var mProgressBar: ProgressBar? = null
    internal var mNoImageMode: Boolean = false

    internal var _onPageStarted: (url: String?) -> Unit = { }
    internal var _onPageFinished: (url: String?) -> Unit = { }

    init { initView(context) }

    private fun initView(context: Context) {
        val view = View.inflate(context, R.layout.kweb_view_ext, this)
        mWebView = view.findViewById(R.id.web_view) as KWebView
        mProgressBar = view.findViewById(R.id.progress_bar) as ProgressBar
    }

    fun loadUrl(url: String?) {
        if (url == null) return
        initWebview(url)
    }

    fun reload() {
        mWebView?.reload()
    }

    fun setNoImageMode(enable: Boolean) {
        mNoImageMode = enable
        mWebView?.clearCache(true)
    }

    fun setTextSize(size: WebSettings.TextSize) {
        mWebView?.settings?.textSize = size
    }

    fun setBackgroundTransparent() {
        mWebView?.setBackgroundColor(0)
    }

    @SuppressLint("JavascriptInterface")
    fun addJavascriptInterface(jsInterface: JSInterface) {
        mWebView!!.addJavascriptInterface(jsInterface, "android")
    }

    fun setProgressDrawable(drawable: Drawable) {
        mProgressBar?.progressDrawable = drawable
    }

    fun getProgressBar(): ProgressBar? {
        return mProgressBar
    }

    fun setPageListener(onPageStarted: (url: String?) -> Unit, onPageFinished: (url: String?) -> Unit) {
        _onPageStarted = onPageStarted
        _onPageFinished = onPageFinished
    }

    private fun initWebview(url: String) {

        mWebView!!.loadUrl(url)

        // 懒加载图片
        mWebView?.settings?.blockNetworkImage = true

        // 设置WebViewClient
        mWebView!!.setWebViewClient(object : WebViewClient() {
            override fun shouldOverrideUrlLoading(view: WebView?, request: WebResourceRequest?): Boolean {
                // 使用自己的WebView组件来响应Url加载事件，而不是使用默认浏览器器加载页面
                view?.loadUrl(url)
                // 相应完成返回true
                return true
                // return super.shouldOverrideUrlLoading(view, url);
            }
            override fun onPageStarted(view: WebView?, url: String?, favicon: Bitmap?) {
                _onPageStarted(url)
                mProgressBar!!.visibility = View.VISIBLE
                super.onPageStarted(view, url, favicon)
            }
            override fun onPageFinished(view: WebView?, url: String?) {
                _onPageFinished(url)
                mProgressBar!!.visibility = View.GONE
                if(!mNoImageMode) view?.settings?.blockNetworkImage = false
                super.onPageFinished(view, url)
            }
            override fun onLoadResource(view: WebView?, url: String?) {
                super.onLoadResource(view, url)
            }
            override fun onReceivedError(view: WebView?, request: WebResourceRequest?, error: WebResourceError?) {
//				view.loadData(errorHtml, "text/html; charset=UTF-8", null);
                super.onReceivedError(view, request, error)
            }
        })

        // 设置WebChromeClient
        mWebView!!.setWebChromeClient(object : WebChromeClient() {
            override fun onJsAlert(view: WebView?, url: String?, message: String?, result: JsResult?): Boolean {
                return super.onJsAlert(view, url, message, result)
            }
            override fun onJsConfirm(view: WebView?, url: String?, message: String?, result: JsResult?): Boolean {
                return super.onJsConfirm(view, url, message, result)
            }
            override fun onJsPrompt(view: WebView?, url: String?, message: String?, defaultValue: String?, result: JsPromptResult?): Boolean {
                return super.onJsPrompt(view, url, message, defaultValue, result)
            }
            override fun onProgressChanged(view: WebView?, newProgress: Int) {
                mProgressBar!!.progress = newProgress
                super.onProgressChanged(view, newProgress)
            }
            override fun onReceivedTitle(view: WebView?, title: String?) {
                super.onReceivedTitle(view, title)
            }
        })

        mWebView!!.setOnKeyListener(object : View.OnKeyListener {
            override fun onKey(v: View, keyCode: Int, event: KeyEvent): Boolean {
                if (event.getAction() == KeyEvent.ACTION_DOWN) {
                    // 表示按返回键
                    if (keyCode == KeyEvent.KEYCODE_BACK && mWebView!!.canGoBack()) {
                        mWebView!!.goBack()
                        return true // 已处理
                    }
                }
                return false
            }
        })
    }

    fun goBack(): Boolean {
        if (mWebView!!.canGoBack()) {
            mWebView!!.goBack()
            return true
        }
        return false
    }

    fun destroy() {
        mWebView?.removeAllViews()
        mWebView?.destroy()
    }
}