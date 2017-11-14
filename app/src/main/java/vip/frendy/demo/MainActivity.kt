package vip.frendy.demo

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import kotlinx.android.synthetic.main.activity_main.*
import org.jsoup.Jsoup
import vip.frendy.extension.ext.postDelayedToUI

/**
 * Created by frendy on 2017/9/22.
 */
class MainActivity: AppCompatActivity() {

    val URL_VIDEO_LIST = "https://www.youtube.com/results?sp=EgIQAVAU&search_query=%E8%B0%A2%E9%9C%86%E9%94%8B"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        webView.setProgressDrawable(resources.getDrawable(R.drawable.progress_bar_demo))
//        webView.loadUrl("http://frendy.vip/")

//        webView.addJavascriptInterface(MyInterface { action, data ->
//            when(action) {
//                MyInterface.GOTO_UGC_DETAIL_ACTIVITY -> {
//                    toast("Data is ${data}")
//                }
//            }
//        })
//        webView.loadUrl("http://app-api.ngzb.com.cn/pureArticle?news_id=18711532&app_id=5020")

//        var startTime = 0L
//        var endTime = 0L
//        webView.setPageListener({ url ->
//            startTime = System.currentTimeMillis()
//        }, { url ->
//            endTime = System.currentTimeMillis()
//            Log.i("", "** load ${url}, cost : ${endTime - startTime}")
//        })

        webView.setLazyLoadImageEnable(false)
        webView.addJavascriptInterface(MyInterface { action, data ->
            when(action) {
                MyInterface.PRASE_YOUTUBE_VIDEO_LIST -> {
                    val doc = Jsoup.parse(data)
                    val content = doc.getElementById("content")
                    val list = content.getElementsByClass("_mzjb _makb _mbkb _mggc")
                    for (item in list) {
                        val elements = item.getElementsByClass("_mzgc")
                        val links = item.getElementsByTag("a")

                        Log.i("JSOUP", "** " + links.get(0).attr("href") + " | " + elements.text())
                    }
                }
            }
        })
        webView.setPageListener({}, { view, url ->
            postDelayedToUI({
                view?.loadUrl("javascript:window.android.praseYoutubeVideoList(document.body.innerHTML);")
            }, 5000)
        })
        webView.loadUrl(URL_VIDEO_LIST)
    }
}