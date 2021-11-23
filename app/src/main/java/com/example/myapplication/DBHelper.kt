package com.example.myapplication

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.util.Log

class DBHelper(
    context: Context?,
)
    : SQLiteOpenHelper(context, DATABASE_NAME,FACTORY, DATABASE_VERSION) {
    companion object{
        var DATABASE_VERSION=1
        var DATABASE_NAME="mydb"
        var TABLE_USERS="user"
        var KEY_ID="id"
        var KEY_NAME="name"
        var KEY_MAIL="mail"
        var KEY_PASSWORD="password"
        var FACTORY = null
    }

    override fun onCreate(db: SQLiteDatabase?) {
        db?.execSQL("create table "+ TABLE_USERS + "("+KEY_ID+" integer primary key autoincrement,"
            + KEY_NAME+ " text,"+ KEY_MAIL+ " text,"+ KEY_PASSWORD+ " text"+")")
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        db?.execSQL("drop table if exists " + TABLE_USERS)
        onCreate(db)
    }

    fun insertUserData(name:String, mail:String, password:String){
        val db:SQLiteDatabase=writableDatabase
        val values: ContentValues = ContentValues()
        values.put("name", name)
        values.put("mail", mail)
        values.put("password", password)
        db.insert("user", null, values)
        db.close()
    }

    fun userIsInDB(login_mail: String, login_password: String):Boolean{
        Log.d("INFO!!!!!!!!!!!!!!!",login_mail+login_password)
        val db:SQLiteDatabase=writableDatabase
        val query="SELECT * FROM " + TABLE_USERS + " WHERE "+ KEY_MAIL + " = '$login_mail' AND "+
                KEY_PASSWORD + " = '$login_password'"
        val cursor=db.rawQuery(query, null)
        if (cursor.count>0){
            Log.d("INFO count", cursor.count.toString())
            return false
        }
        else {
            Log.d("INFO", cursor.count.toString())
            return true
        }
        cursor.close()
    }
}
