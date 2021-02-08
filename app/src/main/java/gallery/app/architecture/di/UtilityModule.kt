package gallery.app.architecture.di

import gallery.app.architecture.prefs.PrefStore
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent
import dagger.hilt.components.SingletonComponent
import gallery.app.architecture.prefs.PrefStoreImpl
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
interface UtilityModule {

    @Binds fun bindsSomeInterface(impl: PrefStoreImpl): PrefStore
}
/*
abstract class UtilityModule {

    @Binds
    @Singleton
    abstract fun provideSharedPrefs(prefStoreImpl: PrefStoreImpl): PrefStore
}*/
