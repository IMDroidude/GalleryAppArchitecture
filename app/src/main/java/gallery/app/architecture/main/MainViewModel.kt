package gallery.app.architecture.main

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import gallery.app.architecture.prefs.PrefStore
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    val prefStore: PrefStore
    ): ViewModel() {

}