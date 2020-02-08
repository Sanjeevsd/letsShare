package com.example.letsshare

class Users(val uid:String,val names:String,val number:String,val email:String){
    constructor():this("","","","")
}

class books(val bookname:String,val price:String,val semester:String,val fileurl:String,val uname:String){
    constructor():this("","","","","")

}
class notesend(val fname:String,val furl:String){
    constructor():this("","")
}