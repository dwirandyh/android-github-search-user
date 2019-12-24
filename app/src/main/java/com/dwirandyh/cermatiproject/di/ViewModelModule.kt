package com.dwirandyh.cermatiproject.di

import com.dwirandyh.cermatiproject.data.repository.UserRepository
import com.dwirandyh.cermatiproject.view.main.MainViewModelFactory
import dagger.Module
import dagger.Provides

@Module
class ViewModelModule {

    @Provides
    fun provideMainViewModel(userRepository: UserRepository): MainViewModelFactory {
        return MainViewModelFactory(userRepository)
    }

}