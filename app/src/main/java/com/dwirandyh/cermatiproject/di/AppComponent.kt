package com.dwirandyh.cermatiproject.di

import android.content.Context
import com.dwirandyh.cermatiproject.view.main.MainActivity
import dagger.BindsInstance
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [ApiModule::class, RepositoryModule::class, ViewModelModule::class])
interface AppComponent {
    fun inject(app: MainActivity)

    @Component.Builder
    interface Builder {
        @BindsInstance
        fun withContext(appContext: Context): Builder

        fun build(): AppComponent
    }
}