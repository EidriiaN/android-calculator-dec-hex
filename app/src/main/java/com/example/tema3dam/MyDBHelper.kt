package com.example.tema3dam

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.provider.BaseColumns
import android.widget.Toast
import androidx.fragment.app.Fragment

val DATABASE_NAME = "MyDB"
val TABLE_NAME = "Tabel"
val COL_DATA = "Data"
val COL_ID = "ID"

// In cazul de fata contextul in care se folosete clasa myDBHelper este un fragment
class MyDBHelper(var context: Fragment): SQLiteOpenHelper(context.requireContext(),DATABASE_NAME,null,1) {


    override fun onCreate(db: SQLiteDatabase?) {

        val createTable = "CREATE TABLE " + TABLE_NAME + " ("+
                COL_ID +" INTEGER PRIMARY KEY AUTOINCREMENT, "+
                COL_DATA + " VARCHAR(256))";
        db?.execSQL(createTable)

    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {

    }
    fun insertData(data: String){

        val db = this.writableDatabase
        var cv = ContentValues()

        cv.put(COL_DATA, data)
        var result = db.insert(TABLE_NAME,null, cv)

        if(result == -1.toLong())
            Toast.makeText(context.requireContext(), "Error !",Toast.LENGTH_SHORT).show()
        else
            Toast.makeText(context.requireContext(), "Succes !",Toast.LENGTH_SHORT).show()
    }

    fun readData() : MutableList<String>{

        var list : MutableList<String> = ArrayList()

        var db = this.readableDatabase
        val query = "SELECT * FROM "+ TABLE_NAME
        val result = db.rawQuery(query,null)

        if(result.moveToFirst()){
            do {
                var myData = result.getString(result.getColumnIndexOrThrow(COL_DATA))
                list.add(myData)
            }while (result.moveToNext())
        }

        result.close()
        db.close()
        return  list
    }

    fun clearDatabase() {
        val db = this.writableDatabase
        db.delete(TABLE_NAME, null, null)
        db.close()
    }
}