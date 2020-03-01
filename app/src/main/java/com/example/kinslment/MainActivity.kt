package com.example.kinslment

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import base.BaseActivity
import bean.Gaode
import com.example.kinslment.R
import com.google.gson.Gson
import com.kaopiz.kprogresshud.KProgressHUD
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import net.Api
import net.exc.retrofit
import net.exc.retrofitApi

class MainActivity : BaseActivity() {

    var boolean = true

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        accountInfoGde(mapOf());
        button.setOnClickListener {
            if (boolean) {
                accountInfo(mapOf());
            } else {
                accountInfoGde(mapOf());
            }
            boolean = !boolean
        }
    }

    //https://proandroiddev.com/suspend-what-youre-doing-retrofit-has-now-coroutines-support-c65bd09ba067
    private fun accountInfo(map: Map<String, String>) {
        var dalogX = KProgressHUD.create(this)
            .setStyle(KProgressHUD.Style.SPIN_INDETERMINATE)
            .setCancellable(false)
            .setAnimationSpeed(2).setDimAmount(0.5f)
        dalogX.show()
        retrofitApi<String> {
            api =
                Api.instance.service.getHtmlContext1("https://github.com/square/retrofit/blob/master/CHANGELOG.md#version-260-2019-06-05")
            onSuccess { html, _, _ ->
                txtinof.text = html
            }
            onFail { _, _, _ ->
            }
            onComplete {
                dalogX?.dismiss()
            }
        }
    }

    private fun accountInfoGde(map: Map<String, String>) {
//       GlobalScope.launch {
//           var dad = Api.instance.service.getGaode1("dsada")
//           dad.await().body()
//       }
        var dalogX = KProgressHUD.create(this)
            .setStyle(KProgressHUD.Style.SPIN_INDETERMINATE)
            .setCancellable(false)
            .setAnimationSpeed(2).setDimAmount(0.5f)
        dalogX.show()

        retrofitApi<Gaode> {
            api =
                Api.instance.service.getGaode("https://restapi.amap.com/v3/geocode/regeo?output=json&location=116.310003,39.991957&key=0ae809af76f32dae8f3efefa01fb220f&radius=1000&extensions=all");
            onSuccess { data, _, _ ->
                txtinof.text = Gson().toJson(data)

            }
            onFail { _, _, _ ->
            }
            onComplete {
                dalogX?.dismiss()
            }
        }
    }


}
