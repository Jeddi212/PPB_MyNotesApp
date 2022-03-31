package com.jeddi.mynotesapp.db

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.SQLException
import android.database.sqlite.SQLiteDatabase
import com.jeddi.mynotesapp.db.DatabaseContract.NoteColumns.Companion.TABLE_NAME
import com.jeddi.mynotesapp.db.DatabaseContract.NoteColumns.Companion._ID

class NoteHelper(context: Context) {
    companion object {
        private const val DATABASE_TABLE = TABLE_NAME
        private lateinit var dataBaseHelper: DatabaseHelper
        private var INSTANCE: NoteHelper? = null

        fun getInstance(context: Context): NoteHelper =
            INSTANCE ?: synchronized(this) {
                INSTANCE ?: NoteHelper(context)
            }

        private lateinit var database: SQLiteDatabase
    }

    init {
        dataBaseHelper = DatabaseHelper(context)
    }

    /**
     * Metode untuk menginisialisasi databse
     */
    private var INSTANCE: NoteHelper? = null
    fun getInstance(context: Context): NoteHelper = INSTANCE ?: synchronized(this) {
        INSTANCE ?: NoteHelper(context)
    }

    /**
     * Metode untuk membuka koneksi ke dataase
     */
    @Throws(SQLException::class)
    fun open() {
        database = dataBaseHelper.writableDatabase
    }

    /**
     * Metode untuk menutup koneksi ke dataase
     */
    fun close() {
        dataBaseHelper.close()

        if (database.isOpen) {
            database.close()
        }
    }

    /**
     * Metode untuk mengurusi CRUD
     */
    fun queryAll(): Cursor {
        return database.query(
            DATABASE_TABLE,
            null,
            null,
            null,
            null,
            null,
            "$_ID ASC")
    }

    /**
     * Mengambil data dengan id tertentu
     */
    fun queryById(id: String): Cursor {
        return database.query(
            DATABASE_TABLE,
            null,
            "$_ID = ?",
            arrayOf(id),
            null,
            null,
            null,
            null)
    }

    /**
     * Menimpan data
     */
    fun insert(values: ContentValues?): Long {
        return database.insert(DATABASE_TABLE, null, values)
    }

    /**
     * Memperbaharui data
     */
    fun update(id: String, values: ContentValues?): Int {
        return database.update(DATABASE_TABLE, values, "$_ID = ?", arrayOf(id))
    }

    /**
     * Menghapus data
     */
    fun deleteById(id: String): Int {
        return database.delete(DATABASE_TABLE, "$_ID = '$id'", null)
    }
}