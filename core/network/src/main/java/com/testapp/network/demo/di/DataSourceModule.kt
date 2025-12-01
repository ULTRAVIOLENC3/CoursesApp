package com.testapp.network.demo.di

import com.testapp.network.NetworkDataSource
import com.testapp.network.demo.DemoCoursesAppNetworkDataSource
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
@Module
@InstallIn(SingletonComponent::class)
interface DataSourceModule {
    @Binds
    abstract fun bindNetworkDataSource(
        impl: DemoCoursesAppNetworkDataSource
    ): NetworkDataSource
}