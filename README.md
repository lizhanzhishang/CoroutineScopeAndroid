# CoroutineScopeAndroid
CoroutineScope
~~~
基于:
okhttp4.4.0
retrofit:2.7.2
kotlinx-coroutines-core:1.3.3
retrofit2-kotlin-coroutines-adapter:0.9.2
~~~

写的一个DSL网络请求框架，简单方便

例子可以从MainActivity做简单入手看起。
代码写有编写时候的测试代码还存在的，可帮助思考为什么这样写

~~~
 .addCallAdapterFactory(CoroutineCallAdapterFactory()) //Coroutine的适配器
 .addConverterFactory(StringConverterFactory()) // 使用Gson作为数据转换器
 .addConverterFactory(GsonConverterFactory.create()) // 使用Gson作为数据转换器
~~~

 编写了一个RetrofitApi DSL，然后对于CoroutineScope做了一个延展。

~~~
 
 CoroutineCallAdapterFactory 里面的代码有这样一句话
 deferred.invokeOnCompletion {
        if (deferred.isCancelled) {
          call.cancel()
       }
   }
~~~

~~~
   它自己做了如果协成重，协成取消，底层的网络自然也就取消了。感觉就没必要使用ViewModule了
   因为我们自己在处理coroutines的综合处理就好了
   

   https://kotlinlang.org/docs/reference/coroutines/coroutines-guide.html
   官方协成使用和对于的github链接都在里面
~~~  
   
