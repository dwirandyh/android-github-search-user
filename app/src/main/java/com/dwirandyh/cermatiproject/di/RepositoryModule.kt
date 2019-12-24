package com.dwirandyh.cermatiproject.di

import com.dwirandyh.cermatiproject.data.repository.UserRepository
import com.dwirandyh.cermatiproject.data.repository.UserRepositoryImpl
import dagger.Binds
import dagger.Module
import javax.inject.Singleton

@Module
abstract class RepositoryModule {

    // Binds agar menggunakan implementasi dari interface secara otomatis
    @Binds
    @Singleton
    abstract fun bindUserRepository(impl: UserRepositoryImpl): UserRepository

}