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

    val URL_DEFAULT = "http://frendy.vip/"
    val URL_VIDEO_LIST = "https://www.youtube.com/results?sp=EgIQAVAU&search_query=谢霆锋&p=1"
    val URL_TEST = "https://www.thestartmagazine.com/feed/summary?isDesktop=false&publisherId=AmitG-Test&key=c3m1HOi41aZMgJ6WAPBef3cvv26Gw3Tn&vendor=365scores&genericDimension=WorldCup&countryCode=UK&language=en"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        webView.setProgressDrawable(resources.getDrawable(R.drawable.progress_bar_demo))
        webView.loadUrl(URL_TEST)

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

//        webView.setLazyLoadImageEnable(false)
//        webView.addJavascriptInterface(MyInterface { action, data ->
//            when(action) {
//                MyInterface.PRASE_YOUTUBE_VIDEO_LIST -> {
//                    val doc = Jsoup.parse(data)
//                    val list = doc.getElementsByTag("a")
//
//                    for(item in list) {
//                        val href = item.attr("href")
//                        val label = item.attr("aria-label").split(" - ")[0]
//
//                        if(href.contains("/watch?v")) {
//                            val id = href.replace("/watch?v=", "")
//
//                            Log.i("jsoup", "** id = ${id}, label = ${label}")
//                        }
//                    }
//                }
//            }
//        })
//        webView.setPageListener({}, { view, url ->
//            postDelayedToUI({
//                view?.loadUrl("javascript:window.android.praseYoutubeVideoList(document.body.innerHTML);")
//            }, 1000)
//        })
//        webView.loadUrl(URL_VIDEO_LIST)
    }
}