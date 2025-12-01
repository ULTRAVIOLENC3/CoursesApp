package com.testapp.database.di

import android.content.Context
import androidx.room.Room
import com.testapp.database.CoursesDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Provides
    @Singleton
    fun provideDatabase(@ApplicationContext context: Context): CoursesDatabase {
        return Room.databaseBuilder(
            context,
            CoursesDatabase::class.java,
            "courses-db"
        ).build()
    }

    @Provides
    fun provideBookmarkDao(db: CoursesDatabase) = db.bookmarkDao()
}