package bean

data class Gaode(var status:String,var infocode:String,var info:String,var regeocode:Regeocode)
data class Regeocode(var pois:List<poisBean>,var formatted_address:String)
data class poisBean(var id:String,var direction:String,var businessarea:String,var address:String,var name:String,var type:String)
