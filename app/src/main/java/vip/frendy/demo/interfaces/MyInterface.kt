package vip.frendy.demo

import android.webkit.JavascriptInterface
import vip.frendy.kwebviewext.interfaces.JSInterface

/**
 * Created by frendy on 2017/9/12.
 */
open class MyInterface(val listener: (Int, String) -> Unit): JSInterface() {

    companion object {
        val GOTO_UGC_DETAIL_ACTIVITY = 0
        val PRASE_YOUTUBE_VIDEO_LIST = 1
    }

    @JavascriptInterface
    fun gotoUgcDetail() {
        listener(GOTO_UGC_DETAIL_ACTIVITY, "")
    }

    @JavascriptInterface
    fun praseYoutubeVideoList(html: String) {
        listener(PRASE_YOUTUBE_VIDEO_LIST, html)
    }
}