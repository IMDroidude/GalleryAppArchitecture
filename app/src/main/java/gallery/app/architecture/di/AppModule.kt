package gallery.app.architecture.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent
import de.joyn.myapplication.domain.executer.PostExecutionThread
import de.joyn.myapplication.domain.executer.UseCaseExecutor
import gallery.app.architecture.data.extractor.NetworkJobExecutor
import gallery.app.architecture.domain.executer.UiThreadExecutor
import javax.inject.Singleton

// FIXME: 2/10/21 delete this component 
@Module
@InstallIn(ActivityComponent::class)
abstract class AppModule {

}