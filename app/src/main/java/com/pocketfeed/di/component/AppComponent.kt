package com.pocketfeed.di.component

import android.app.Application
import com.pocketfeed.di.module.AppModule
import com.pocketfeed.di.module.FragmentBuildersModule
import com.pocketfeed.di.module.NetworkModule
import com.embibe.app.di.module.ViewModelModule
import com.pocketfeed.application.AndroidApplication
import com.pocketfeed.di.module.ActivityBuildersModule
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjector
import dagger.android.support.AndroidSupportInjectionModule

@Component(
    modules = [AndroidSupportInjectionModule::class, ActivityBuildersModule::class,
        FragmentBuildersModule::class, ViewModelModule::class, AppModule::class, NetworkModule::class]
)
interface AppComponent : AndroidInjector<AndroidApplication> {
    @Component.Builder
    interface Builder {

        @BindsInstance
        fun application(application: Application): Builder

        fun build(): AppComponent
    }
}