package net.services

import bean.BaseResponse
import bean.Gaode
import kotlinx.coroutines.Deferred
import retrofit2.Response

import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Url

interface ApiServices {

    //@Headers("Content-Type:application/x-www-form-urlencoded; charset=utf-8")
    // 上面这样写法就是修改IMEI

    //请求类型 + 路由
    @GET()
    fun getHtmlContext(@Url url: String ): Deferred<String>//由于json采用手动解，所以没有用泛型

    //请求类型 + 路由
    @GET()
    fun getHtmlContext1(@Url url: String ): Deferred<Response<String>>//由于json采用手动解，所以没有用泛型


    //请求类型 + 路由
    @GET()
    fun getGaode12(@Url url: String ): Deferred<Response<BaseResponse<Gaode>>>//由于json采用手动解，所以没有用泛型



    //请求类型 + 路由
    @GET()
    fun getGaode(@Url url: String ): Deferred<Response<Gaode>>//由于json采用手动解，所以没有用泛型


    //请求类型 + 路由
    @GET()
    suspend fun getGaode1(@Url url: String ): Deferred<Response<Gaode>>//由于json采用手动解，所以没有用泛型



}