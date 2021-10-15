package com.example.quizz_tcr

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import com.example.quizz_tcr.DatabaseHandler.Companion.TABLE_USER

class DatabaseHandler (context: Context) : SQLiteOpenHelper(context,DATABASE_NAME,null,DATABASE_VERSION) {

    // create table sql query
    private val CREATE_USER_TABLE = ("CREATE TABLE " + TABLE_USER + "("
            + COLUMN_USER_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," + COLUMN_USER_NAME + " TEXT,"
            + COLUMN_USER_EMAIL + " TEXT," + COLUMN_USER_PASSWORD + " TEXT" + ")")

    // drop table sql query
    private val DROP_USER_TABLE = "DROP TABLE IF EXISTS $TABLE_USER"


    companion object  {
        // Database Version
        private val DATABASE_VERSION = 1

        // Database Name
        private val DATABASE_NAME = "UserManager.db"

        // User table name
        private val TABLE_USER = "user"

        // User Table Columns names
        private val COLUMN_USER_ID = "user_id"
        private val COLUMN_USER_NAME = "user_name"
        private val COLUMN_USER_EMAIL = "user_email"
        private val COLUMN_USER_PASSWORD = "user_password"
    }

    override fun onCreate(db: SQLiteDatabase) {
        db.execSQL(CREATE_USER_TABLE)
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        //Drop User Table if exist
        db.execSQL(DROP_USER_TABLE)

        // Create tables again
        onCreate(db)
    }

    fun addUser(name : String, email:String, password: String) {
        val db = this.writableDatabase

        val values = ContentValues()
        values.put(COLUMN_USER_NAME, name)
        values.put(COLUMN_USER_EMAIL, email)
        values.put(COLUMN_USER_PASSWORD, password)

        // Inserting Row
        db.insert(TABLE_USER, null, values)
        db.close()
    }

    fun deleteUser(id : Int) {

        val db = this.writableDatabase
        // delete user record by id
        db.delete(TABLE_USER, "$COLUMN_USER_ID = ?",
            arrayOf(id.toString()))
        db.close()
    }

    fun updateUser(id: Int, name : String, email:String, password: String) {
        val db = this.writableDatabase

        val values = ContentValues()
        values.put(COLUMN_USER_ID, id)
        values.put(COLUMN_USER_NAME, name)
        values.put(COLUMN_USER_EMAIL, email)
        values.put(COLUMN_USER_PASSWORD, password)

        // updating row
        db.update(TABLE_USER, values, "$COLUMN_USER_ID = ?", arrayOf(id.toString()))
        db.close()
    }

    fun checkUser(username: String): Boolean {

        // array of columns to fetch
        val columns = arrayOf(COLUMN_USER_ID)
        val db = this.readableDatabase

        // selection criteria
        val selection = "$COLUMN_USER_NAME = ?"

        // selection argument
        val selectionArgs = arrayOf(username)

        // query user table with condition

        val cursor = db.query(TABLE_USER,columns,selection,selectionArgs,null,
            null,null)

        val cursorCount = cursor.count
        cursor.close()
        db.close()

        if (cursorCount > 0) {
            return true
        }
        return false
    }

    fun checkUserpass(username: String, password: String): Boolean {

        // array of columns to fetch
        val columns = arrayOf(COLUMN_USER_ID)

        val db = this.readableDatabase

        // selection criteria
        val selection = "$COLUMN_USER_NAME = ? AND $COLUMN_USER_PASSWORD = ?"

        // selection arguments
        val selectionArgs = arrayOf(username, password)

        // query user table with conditions
        val cursor = db.query(TABLE_USER,columns,selection,selectionArgs,null,
            null,null)
        val cursorCount = cursor.count
        cursor.close()
        db.close()

        if (cursorCount > 0)
            return true

        return false
    }

    fun readData() : Cursor?{
        var db = this.readableDatabase
        return db.rawQuery("select * from " + TABLE_USER,null)
    }

}