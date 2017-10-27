package vip.frendy.demo

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import kotlinx.android.synthetic.main.activity_main.*

/**
 * Created by frendy on 2017/9/22.
 */
class MainActivity: AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        webView.setProgressDrawable(resources.getDrawable(R.drawable.progress_bar_demo))
        webView.loadUrl("http://frendy.vip/")

//        webView.addJavascriptInterface(MyInterface { action, data ->
//            when(action) {
//                MyInterface.GOTO_UGC_DETAIL_ACTIVITY -> {
//                    toast("Data is ${data}")
//                }
//            }
//        })
//        webView.loadUrl("http://app-api.ngzb.com.cn/pureArticle?news_id=18711532&app_id=5020")

        var startTime = 0L
        var endTime = 0L
        webView.setPageListener({ url ->
            startTime = System.currentTimeMillis()
        }, { url ->
            endTime = System.currentTimeMillis()
            Log.i("", "** load ${url}, cost : ${endTime - startTime}")
        })
    }
}