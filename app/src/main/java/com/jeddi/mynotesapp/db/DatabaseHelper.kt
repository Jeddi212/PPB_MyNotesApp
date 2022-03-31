package com.jeddi.mynotesapp.db

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import com.jeddi.mynotesapp.db.DatabaseContract.NoteColumns.Companion.TABLE_NAME

/**
 * Menciptakan database dengan tabel yang dibutuhkan
 * dan handle ketika terjadi perubahan skema pada table
 * (terjadi pada metode onUpgrade())
 */
internal class DatabaseHelper(context: Context) :
    SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION){
    companion object {
        private const val DATABASE_NAME = "dbnoteapp"
        private const val DATABASE_VERSION = 1
        private val SQL_CREATE_TABLE_NOTE = "CREATE TABLE $TABLE_NAME" +
        " (${DatabaseContract.NoteColumns._ID} INTEGER PRIMARY KEY AUTOINCREMENT," +
        " ${DatabaseContract.NoteColumns.TITLE} TEXT NOT NULL," +
        " ${DatabaseContract.NoteColumns.DESCRIPTION} TEXT NOT NULL," +
        " ${DatabaseContract.NoteColumns.DATE} TEXT NOT NULL)"
    }

    /**
     * Menciptakan databse dengan tabel yang dibutuhkan
     */
    override fun onCreate(db: SQLiteDatabase) {
        db.execSQL(SQL_CREATE_TABLE_NOTE)
    }

    /**
     * handle ketika terjadi perubahan skema pada table
     * (terjadi pada metode onUpgrade())
     */
    override fun onUpgrade(db: SQLiteDatabase, oldVesion: Int, newVersion: Int) {
        db.execSQL("DROP TABLE IF EXISTS $TABLE_NAME")
        onCreate(db)
    }
}