package gallery.app.architecture.di

import gallery.app.architecture.prefs.PrefStore
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent
import dagger.hilt.components.SingletonComponent
import de.joyn.myapplication.domain.executer.PostExecutionThread
import de.joyn.myapplication.domain.executer.UseCaseExecutor
import gallery.app.architecture.data.extractor.NetworkJobExecutor
import gallery.app.architecture.domain.executer.UiThreadExecutor
import gallery.app.architecture.prefs.PrefStoreImpl
import gallery.app.architecture.utils.InternetManager
import gallery.app.architecture.utils.InternetManagerImpl
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
interface UtilityModule {

    @Binds fun bindsSomeInterface(impl: PrefStoreImpl): PrefStore
    @Binds fun bindInternetManager(impl:InternetManagerImpl) : InternetManager
}

