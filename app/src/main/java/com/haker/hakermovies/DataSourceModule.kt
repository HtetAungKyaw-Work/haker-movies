package com.haker.hakermovies

import com.haker.hakermovies.data.source.local.LocalDataSourceImpl
import com.haker.hakermovies.data.source.remote.RemoteDataSourceImpl
import com.haker.hakermovies.domain.source.DataSource
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class DataSourceModule {

    @Binds
    abstract fun provideRemoteDataSource(remoteDataSourceImpl: RemoteDataSourceImpl): DataSource.Remote

    @Binds
    abstract fun provideLocalDataSource(localDataSourceImpl: LocalDataSourceImpl): DataSource.Local
}