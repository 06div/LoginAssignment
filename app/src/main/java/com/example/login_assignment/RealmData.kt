package com.example.login_assignment


import io.realm.RealmObject
import io.realm.annotations.PrimaryKey
import io.realm.annotations.RealmClass


@RealmClass
open class RealmData(@PrimaryKey
                     var id:Int = 0,
                     var name:String = "",
                     var age:Int = 0,
                     var city:String = "") : RealmObject(){
//    fun name(name: String): String {
//        this.name =name
//        return name
//    }
//
//    fun age(age: Int): Int {
//        this.age=age
//        return age
//    }
//
//    fun city(city: String): String {
//        this.city=city
//        return city
//    }

//    @PrimaryKey
//    var id:Int = 0
//    var name:String = ""
//    var age:Int = 0
//    var city:String = ""

}
