package com.bia_technologies.bia.domain.di

import com.bia_technologies.bia.data.mappers.OrderMapper
import com.bia_technologies.bia.data.repositories.OrderRepositoryImpl
import com.bia_technologies.bia.domain.repositories.OrderRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    fun provideOrderRepository(): OrderRepository {
        return OrderRepositoryImpl(OrderMapper())
    }
}