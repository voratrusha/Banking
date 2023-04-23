package com.example.banking

import android.icu.util.CurrencyAmount
import java.util.Date

class UserModel {

    var id = 0
    lateinit var name: String
    lateinit var bank: String
     var amount: Int= 0
     var account: Int= 0
     lateinit var time : String
     lateinit var date : String
    lateinit var type: String


    constructor(id: Int, name: String, bank: String, amount: Int, account: Int, type: String, time:String, date: String){
        this.id = id
        this.name = name
        this.bank = bank
        this.account = account
        this.amount = amount
        this.type = type
        this.time  = time
        this.date = date
    }
    constructor()
}