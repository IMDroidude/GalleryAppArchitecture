package gallery.app.architecture.ui.photos

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import androidx.paging.*
import androidx.paging.rxjava3.cachedIn
import androidx.paging.rxjava3.flowable
import dagger.hilt.android.lifecycle.HiltViewModel
import gallery.app.architecture.domain.datasource.dataSource.PhotoDataSourceFactory
import de.joyn.myapplication.network.dto.Models
import gallery.app.architecture.common.BaseViewModel
import gallery.app.architecture.domain.datasource.dataSource.PhotoPagingSource
import gallery.app.architecture.network.RestApi
import gallery.app.architecture.repository.Repository
import io.reactivex.rxjava3.core.Flowable
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import javax.inject.Inject

private const val PAGE_SIZE = 20
private const val INITIAL_LOAD_SIZE_HINT = 40

@HiltViewModel
class PhotosFragmentViewModel @Inject constructor(
    private val photoPagingSourceRx: PhotoPagingSourceRx
): BaseViewModel() {

    private val _trendingPhotos = MutableLiveData<PagingData<Models.PhotoResponse>>()
    val trendingPhotos: LiveData<PagingData<Models.PhotoResponse>>
    get() = _trendingPhotos
    var cachedFilter: String = ""

    fun setFilter(filter: String) {
        photoPagingSourceRx.setFilter(if (cachedFilter.isEmpty()) filter else cachedFilter)
    }

    init {
        viewModelScope.launch {
            getPhotosRx().cachedIn(viewModelScope).subscribe {
                    _trendingPhotos.value = it
            }
        }
    }

    private fun getPhotosRx(): Flowable<PagingData<Models.PhotoResponse>> {
        return Pager(
            config = PagingConfig(
                pageSize = 20,
                enablePlaceholders = false,
                prefetchDistance = 5
            ),
            pagingSourceFactory = { photoPagingSourceRx }
        ).flowable
    }
}