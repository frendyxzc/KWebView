package vip.frendy.demo

import android.webkit.JavascriptInterface
import com.iimedia.appbase.view.webview.interfaces.JSInterface

/**
 * Created by iiMedia on 2017/9/12.
 */
open class MyInterface(val listener: (Int, String) -> Unit): JSInterface() {

    companion object {
        val GOTO_UGC_DETAIL_ACTIVITY = 0
    }

    @JavascriptInterface
    fun gotoUgcDetail() {
        listener(GOTO_UGC_DETAIL_ACTIVITY, "")
    }

}