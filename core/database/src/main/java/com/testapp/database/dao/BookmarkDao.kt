package com.testapp.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.testapp.database.model.BookmarkEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface BookmarkDao {
    @Query("SELECT * FROM bookmarks")
    fun getAllBookmarks(): Flow<List<BookmarkEntity>>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertBookmark(course: BookmarkEntity): Long

    @Query("DELETE FROM bookmarks WHERE id = :courseId")
    suspend fun deleteBookmarkById(courseId: Int)
}