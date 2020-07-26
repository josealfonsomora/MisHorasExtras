package com.josealfonsomora.mishorasextras.db

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface HoraExtraDao {

    @Query("SELECT * FROM horaextra")
    fun getAll(): LiveData<List<HoraExtra>>

    @Insert
    suspend fun add(horaExtra: HoraExtra)
}
