package com.josealfonsomora.mishorasextras.db

import android.content.Context
import androidx.room.Room
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent
import dagger.hilt.android.qualifiers.ApplicationContext

@Module
@InstallIn(ActivityComponent::class)
object DbModule {
    @Provides
    fun provideDatabase(@ApplicationContext context: Context): MisHorasExtrasDB =
        Room.databaseBuilder(
            context,
            MisHorasExtrasDB::class.java, "database.db"
        ).build()

    @Provides
    fun providesHorasExtrasDao(db: MisHorasExtrasDB):HoraExtraDao = db.horaExtraDao()
}
