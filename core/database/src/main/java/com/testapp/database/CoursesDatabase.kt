package com.testapp.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.testapp.database.dao.BookmarkDao
import com.testapp.database.model.BookmarkEntity

@Database(entities = [BookmarkEntity::class], version = 1)
abstract class CoursesDatabase : RoomDatabase() {
    abstract fun bookmarkDao(): BookmarkDao
}