package bean

open class BaseResponseBean<T>{
    var code: String= ""
    var message: String=""
    var result:T?=null
}