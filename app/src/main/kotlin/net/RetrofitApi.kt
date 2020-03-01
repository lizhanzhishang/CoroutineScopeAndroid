package net

import bean.BaseResponse
import kotlinx.coroutines.Deferred
import retrofit2.Call
import retrofit2.Response

// https://github.com/JakeWharton/SdkSearch
// https://android.jlelse.eu/kotlin-coroutines-and-retrofit-e0702d0b8e8f

class RetrofitApi<T>{

    lateinit var api: (Deferred<Response<T>>)
    lateinit var apiDeferred: (Deferred<Response<BaseResponse<T>>>)
    // 项目中的其他网络请求接口处理
    fun isDeferrInitialized():Boolean{
        return this::apiDeferred.isInitialized
    }

    fun isApiInitialized():Boolean{
        return this::api.isInitialized
    }

    internal var onComplete: (() -> Unit)? = null
        private set
    // 项目中的网络请求处理
    internal var onSuccess: ((t:T?,code: String, msg: String) -> Unit)? = null
        private set
    internal var onFail: ((t:T?,code: String, msg: String) -> Unit)? = null
        private set

    /**
     * 获取数据成功
     * @param block (T) -> Unit
     */

    fun onSuccess(block: (t:T?,code: String, msg: String) -> Unit) {
        this.onSuccess = block
    }

    /**
     * 获取数据失败
     * @param block (msg: String, errorCode: Int) -> Unit
     */
    fun onFail(block: (t:T?,code: String, msg: String) -> Unit) {
        this.onFail = block
    }

    /**
     * 访问完成
     * @param block () -> Unit
     */
    fun onComplete(block: () -> Unit) {
        this.onComplete = block
    }

    internal fun clean() {
        onSuccess = null
        onComplete = null
        onFail = null
    }


}