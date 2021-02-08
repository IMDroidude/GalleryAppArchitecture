package gallery.app.architecture.di

import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent

@Module
@InstallIn(ActivityComponent::class)
class AppModule {

    /*@Provides
    fun providePrefContext(@ApplicationContext context: Context): PrefStoreImpl {
        return PrefStoreImpl(context)
    }*/
}