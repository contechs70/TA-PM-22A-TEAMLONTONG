package com.uti.manajemen_panen.Class

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

data class Tanaman(
    val nama: String,
    val kategori: String,
    val waktuPanen: String,
    val modalBibitPerKg: Int,
    val hargaJualPerKg: Int
)

class DatabaseHelper(context: Context) : SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {

    companion object {
        const val DATABASE_NAME = "tanaman.db"
        const val DATABASE_VERSION = 1

        const val TABLE_NAME = "Tanaman"
        const val COLUMN_ID = "id"
        const val COLUMN_NAME = "nama"
        const val COLUMN_CATEGORY = "kategori"
        const val COLUMN_HARVEST_TIME = "waktuPanen"
        const val COLUMN_SEED_COST = "modalBibitPerKg"
        const val COLUMN_SELL_PRICE = "hargaJualPerKg"
    }

    private val SQL_CREATE_ENTRIES =
        "CREATE TABLE $TABLE_NAME (" +
                "$COLUMN_ID INTEGER PRIMARY KEY," +
                "$COLUMN_NAME TEXT," +
                "$COLUMN_CATEGORY TEXT," +
                "$COLUMN_HARVEST_TIME TEXT," +
                "$COLUMN_SEED_COST INTEGER," +
                "$COLUMN_SELL_PRICE INTEGER)"

    private val SQL_DELETE_ENTRIES = "DROP TABLE IF EXISTS $TABLE_NAME"

    override fun onCreate(db: SQLiteDatabase) {
        db.execSQL(SQL_CREATE_ENTRIES)
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        db.execSQL(SQL_DELETE_ENTRIES)
        onCreate(db)
    }

    fun insertData(tanaman: Tanaman) {
        val db = writableDatabase
        val values = ContentValues().apply {
            put(COLUMN_NAME, tanaman.nama)
            put(COLUMN_CATEGORY, tanaman.kategori)
            put(COLUMN_HARVEST_TIME, tanaman.waktuPanen)
            put(COLUMN_SEED_COST, tanaman.modalBibitPerKg)
            put(COLUMN_SELL_PRICE, tanaman.hargaJualPerKg)
        }
        db.insert(TABLE_NAME, null, values)
    }

    fun getTanamanByName(nama: String): Tanaman? {
        val db = readableDatabase
        val cursor = db.query(
            TABLE_NAME,
            arrayOf(COLUMN_NAME, COLUMN_CATEGORY, COLUMN_HARVEST_TIME, COLUMN_SEED_COST, COLUMN_SELL_PRICE),
            "$COLUMN_NAME = ?",
            arrayOf(nama),
            null,
            null,
            null
        )

        cursor?.use {
            if (it.moveToFirst()) {
                return Tanaman(
                    it.getString(it.getColumnIndexOrThrow(COLUMN_NAME)),
                    it.getString(it.getColumnIndexOrThrow(COLUMN_CATEGORY)),
                    it.getString(it.getColumnIndexOrThrow(COLUMN_HARVEST_TIME)),
                    it.getInt(it.getColumnIndexOrThrow(COLUMN_SEED_COST)),
                    it.getInt(it.getColumnIndexOrThrow(COLUMN_SELL_PRICE))
                )
            }
        }
        return null
    }
}

