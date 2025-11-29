package com.testapp.model

data class CourseItem(
    val id: Int = 0,
    val rate: String,
    val price: String,
    val startDate: String,
    val title: String,
    val text: String,
    val hasLike: Boolean
)
