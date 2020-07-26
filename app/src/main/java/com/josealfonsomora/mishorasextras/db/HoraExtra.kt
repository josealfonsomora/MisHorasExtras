package com.josealfonsomora.mishorasextras.db

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.math.BigDecimal

@Entity
class HoraExtra(
    @PrimaryKey(autoGenerate = true) val uid: Int? = null,
    val date: Long,
    val hours: Int,
    val price: BigDecimal
)
