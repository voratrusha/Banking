package com.example.banking

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.widget.Toast

class Database(context: Context) : SQLiteOpenHelper(context,"Banking.db",null,1) {
    var context = context
    override fun onCreate(p0: SQLiteDatabase?) {
        var sql = "CREATE TABLE budget(id INTEGER PRIMARY KEY AUTOINCREMENT, name TEXT, bank TEXT, type TEXT, amount INTEGER, account INTEGER, time TEXT, date TEXT)"
        p0?.execSQL(sql)
    }

    override fun onUpgrade(p0: SQLiteDatabase?, p1: Int, p2: Int) {
    }

    fun insertData(name:String, bank:String, type:String, account: String, amount:Int, time:String, date:String){
        var db = writableDatabase
        var values = ContentValues()
        values.put("name",name)
        values.put("type",type)
        values.put("bank",bank)
        values.put("amount",amount)
        values.put("account",account)
        values.put("time",time)
        values.put("date",date)

        var insrt = db.insert("budget",null,values)
        if (insrt.toInt() == -1){
            Toast.makeText(context,"Data is not inserted", Toast.LENGTH_SHORT).show()
        } else{
            Toast.makeText(context,"Data is inserted", Toast.LENGTH_SHORT).show()
        }
    }

    fun showData(): ArrayList<UserModel>{
        var modelList = ArrayList<UserModel>()
        var db = readableDatabase
        var sql = "SELECT * FROM budget"
        var cursor = db.rawQuery(sql,null)
        cursor.moveToFirst()

        for (i in 0 .. cursor.count-1){
            var id = cursor.getInt(0)
            var name = cursor.getString(1)
            var bank = cursor.getString(2)
            var account = cursor.getInt(3)
            var amount = cursor.getInt(4)
            var type = cursor.getString(5)
            var time = cursor.getString(6)
            var date = cursor.getString(7)
            var model = UserModel(id, name, bank, type, account, amount, time, date)
            modelList.add(model)
            cursor.moveToNext()
        }
        return modelList
    }

    fun updateData(id: Int, name:String, bank:String, type:String, account: Int, amount: Int, time: String, date: String){
        var db = writableDatabase
        var values = ContentValues()
        values.put("name",name)
        values.put("bank",bank)
        values.put("account",account)
        values.put("amount",amount)
        values.put("type",type)
        values.put("time",time)
        values.put("date",date)
        db.update("budget",values,"id=$id",null)
    }

    fun deleteData(id: Int){
        var db = writableDatabase
        db.delete("budget","id=$id",null)
    }

    fun totalbalance(total: Int){
        var total = 0
        for (i in MainActivity.list){
            total += i.amount
        }
    }

}