package com.testapp.data.repository.di

import com.testapp.data.repository.BookmarkRepository
import com.testapp.data.repository.BookmarkRepositoryImpl
import com.testapp.data.repository.CourseRepository
import com.testapp.data.repository.CourseRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class DataModule {

    @Binds
    internal abstract fun bindsCourseRepository(
        courseRepository: CourseRepositoryImpl,
    ): CourseRepository

    @Binds
    internal abstract fun bindsBookmarkRepository(
        courseRepository: BookmarkRepositoryImpl,
    ): BookmarkRepository
}