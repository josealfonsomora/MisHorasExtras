package com.josealfonsomora.mishorasextras.ui

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.ViewModel
import com.josealfonsomora.mishorasextras.db.HoraExtraDao

class HorasExtrasViewModel @ViewModelInject constructor(
    private val horaExtraDao: HoraExtraDao
) : ViewModel() {

    fun getHorasExtras() = horaExtraDao.getAll()
}
