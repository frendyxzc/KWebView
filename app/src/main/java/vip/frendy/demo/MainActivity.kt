package vip.frendy.demo

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*

/**
 * Created by iiMedia on 2017/9/22.
 */
class MainActivity: AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        webView.loadUrl("http://frendy.vip/")
    }
}