package com.testapp.database.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.testapp.model.Course

@Entity(tableName = "bookmarks")
data class BookmarkEntity(
    @PrimaryKey(autoGenerate = false)
    val id: Int,
    val title: String,
    val text: String,
    val price: String,
    val rate: String,
    val startDate: String,
    val hasLike: Boolean,
    val publishDate: String
)

fun BookmarkEntity.asExternalModel() = Course(
    id = id,
    title = title,
    text = text,
    price = price,
    rate = rate,
    startDate = startDate,
    hasLike = hasLike,
    publishDate = publishDate,
)

fun Course.asEntity() = BookmarkEntity(
    id = id,
    title = title,
    text = text,
    price = price,
    rate = rate,
    startDate = startDate,
    hasLike = true,
    publishDate = publishDate,
)