package com.josealfonsomora.mishorasextras.db

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters

@Database(entities = [HoraExtra::class], version = 1)
@TypeConverters(Converters::class)
abstract class MisHorasExtrasDB : RoomDatabase() {
    abstract fun horaExtraDao(): HoraExtraDao
}


