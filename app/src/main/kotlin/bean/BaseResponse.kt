package bean

data class BaseResponse<T>(var code: String= "",var message: String="",var result:T?=null)