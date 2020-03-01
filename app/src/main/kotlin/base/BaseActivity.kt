package base

import android.app.Activity
import android.content.Context
import android.os.Bundle
import android.text.TextUtils
import androidx.appcompat.app.AppCompatActivity
import com.scwang.smartrefresh.layout.footer.ClassicsFooter
import com.scwang.smartrefresh.layout.header.ClassicsHeader
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import pub.devrel.easypermissions.EasyPermissions
import java.util.*
import kotlin.coroutines.CoroutineContext

open class BaseActivity : AppCompatActivity() , CoroutineScope {
    protected lateinit var job: Job
    override val coroutineContext: CoroutineContext get() = Dispatchers.Main + job

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        job = Job()
    }


    override fun onDestroy() {
        super.onDestroy()
        // 关闭页面后，结束所有协程任务
        job.cancel()
    }

    fun makeText(msg:String){
//        ToastUtil.show(this.application,msg)
    }
    fun getActivity(): Activity {
        return this
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)

        // Forward results to EasyPermissions
        EasyPermissions.onRequestPermissionsResult(requestCode, permissions, grantResults, this)
    }
}