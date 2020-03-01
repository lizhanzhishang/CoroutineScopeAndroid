package net.exc



import android.util.Log
import bean.BaseResponse
import bean.BaseResponseBean
import com.google.gson.JsonSyntaxException
import kotlinx.coroutines.*
import net.RetrofitApi
import retrofit2.HttpException
import java.lang.Exception
import java.net.ConnectException
import java.net.SocketTimeoutException
import java.net.UnknownHostException

// https://github.com/JakeWharton/retrofit2-kotlin-coroutines-adapter/blob/master/src/test/java/com/jakewharton/retrofit2/adapter/kotlin/coroutines/DeferredTest.kt
// https://github.com/JakeWharton/retrofit2-kotlin-coroutines-adapter
fun <T> CoroutineScope.retrofit(dsl: RetrofitApi<T>.() -> Unit) {
    val lock = Any()
    //在主线程中开启协程
    launch(Dispatchers.Main) {
//        synchronized(lock) { //代码锁住，只需当前执行，防止其
//        Log.e("Thread","--Thread->>: "+Thread.currentThread().name)
//        }
        val carotene = RetrofitApi<T>().apply(dsl)
        if(carotene.isDeferrInitialized()){
            try {
                val response =carotene.apiDeferred.await();
                if(response.isSuccessful){// code in 200..299
                    val body=response.body();
                    carotene.onSuccess?.invoke(body?.result,"100", "Successl")
                    if(body?.code=="200"){

                    }

                }else{
                    carotene.onFail?.invoke(response.body()?.result,"5555", "Successl")
                }
            } catch (e: Exception) {
                if(e is UnknownHostException || e is ConnectException){
                    carotene.onFail?.invoke(null,"5551", "无效请求地址或者无法链接")
                }else if(e is JsonSyntaxException){
                    carotene.onFail?.invoke(null,"5552", "数据解析失败")
                }else if(e is HttpException){
                    carotene.onFail?.invoke(null,"5553", "网络请求异常")
                }else if(e is SocketTimeoutException){
                    carotene.onFail?.invoke(null,"5556", "网络请求异常")
                }else{
                    carotene.onFail?.invoke(null,"5557", "网络请求异常${e.message}")
                }
                Log.e("Ids","--->> \n "+e)

            }
        }
        carotene.onComplete?.invoke()
    }
}



fun <T> CoroutineScope.retrofitApi(dsl: RetrofitApi<T>.() -> Unit) {
    val lock = Any()
    //在主线程中开启协程
    launch(Dispatchers.Main) {
        //        synchronized(lock) { //代码锁住，只需当前执行，防止其
//        Log.e("Thread","--Thread->>: "+Thread.currentThread().name)
//        }
        val carotene = RetrofitApi<T>().apply(dsl)
        if(carotene.isApiInitialized()){
            try {
                val response =carotene.api.await();
                if(response.isSuccessful){// code in 200..299
                    val body=response.body();
                    carotene.onSuccess?.invoke(body,"100", "Successl")
                }else{
                    carotene.onFail?.invoke(response.body(),"5555", "Successl")
                }
            } catch (e: Exception) {
                if(e is UnknownHostException || e is ConnectException){
                    carotene.onFail?.invoke(null,"5551", "无效请求地址或者无法链接")
                }else if(e is JsonSyntaxException){
                    carotene.onFail?.invoke(null,"5552", "数据解析失败")
                }else if(e is HttpException){
                    carotene.onFail?.invoke(null,"5553", "网络请求异常")
                }else if(e is SocketTimeoutException){
                    carotene.onFail?.invoke(null,"5556", "网络请求异常")
                }else{
                    carotene.onFail?.invoke(null,"5557", "网络请求异常${e.message}")
                }
                Log.e("Ids","--->> \n "+e)

            }
        }
        carotene.onComplete?.invoke()
    }
}


//  https://github.com/square/retrofit/blob/master/CHANGELOG.md#version-260-2019-06-05

//ew: Support suspend modifier on functions for Kotlin! This allows you to express the asynchrony of HTTP requests in an idiomatic fashion for the language.
//
//@GET("users/{id}")
//suspend fun user(@Path("id") id: Long): User
//Behind the scenes this behaves as if defined as fun user(...): Call<User> and then invoked with Call.enqueue. You can also return Response<User> for access to the response metadata.
//
//Currently this integration only supports non-null response body types. Follow issue 3075 for nullable type support.

//                var x1=response;
//                var x2=response.body();
//
//                if (x2 is BaseResponse){
//                    response.let {
////                        if (response.isSuccessful) {
////                            //访问接口成功
////                            if (response.code == "1") {
////                                //判断status 为1 表示获取数据成功
////                                coroutine.onSuccess?.invoke(response.body())
////                            } else {
////                                coroutine.onFail?.invoke(response.data==null: "返回数据为空", response.code())
////                            }
////                        } else {
////                            coroutine.onFail?.invoke(response.errorBody().toString(), response.code())
////                        }
//                    }
//
//                }else{
//                    response.let {
//                        if (response.isSuccessful) {
//                            coroutine.onSuccess?.invoke(response.body()!!)
//                        } else {
//                            coroutine.onFail?.invoke(response.body()?.toString()?: "返回数据为空", response.code().toString())
//                        }
//                    }
//
//                }
