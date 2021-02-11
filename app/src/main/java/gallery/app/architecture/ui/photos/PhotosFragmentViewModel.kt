package gallery.app.architecture.ui.photos

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import androidx.paging.*
import dagger.hilt.android.lifecycle.HiltViewModel
import gallery.app.architecture.domain.datasource.dataSource.PhotoDataSourceFactory
import de.joyn.myapplication.network.dto.Models
import gallery.app.architecture.common.BaseViewModel
import gallery.app.architecture.domain.datasource.dataSource.PhotoPagingSource
import gallery.app.architecture.network.RestApi
import gallery.app.architecture.repository.Repository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import javax.inject.Inject

private const val PAGE_SIZE = 20
private const val INITIAL_LOAD_SIZE_HINT = 40

@HiltViewModel
class PhotosFragmentViewModel @Inject constructor(
    //private val dataSourceFactory: PhotoDataSourceFactory,
    private val photoPagingSource: PhotoPagingSource,
    private val restApi: RestApi,
    private val repository: Repository
): BaseViewModel() {

    ///var stateLiveData: LiveData<PagedList<Models.PhotoResponse>> = MutableLiveData()
    var cachedFilter: String = ""

    fun setFilter(filter: String) {
        //photoPagingSource.setFilter(if (cachedFilter.isEmpty()) filter else cachedFilter)
        //dataSourceFactory.setFilter(if (cachedFilter.isEmpty()) filter else cachedFilter)
    }

    init {
        viewModelScope.launch {
        }
    }

    /*val popularPhotos = Pager(
        config = PagingConfig(
            pageSize = 20,
            maxSize = 100,
            enablePlaceholders = false
        ),
        pagingSourceFactory = { PhotoPagingSource(restApi) }
    ).liveData.cachedIn(viewModelScope)*/


    fun fetchPhotoCollection() : Flow<PagingData<Models.PhotoResponse>> {
        return Pager(PagingConfig(PAGE_SIZE)) { photoPagingSource }
            .flow.cachedIn(viewModelScope)
            /*.map { pagingData ->
                pagingData
                    // Convert network model to local model
                    .map { movie -> Models of movie }
                    // Also, if required, filter out adult content
                    .run {
                        *//*if (!showExplicit) {
                            filter { movie -> !movie.adult }
                        } else {
                            this
                        }*//*
                    }
            }*/
    }

    init {
        //createLiveData()
    }

    /*fun createLiveData() {
        val pagedListConfig = PagedList.Config.Builder()
            .setEnablePlaceholders(true)
            .setInitialLoadSizeHint(INITIAL_LOAD_SIZE_HINT)
            .setPageSize(PAGE_SIZE)
            .build()
        this.stateLiveData = LivePagedListBuilder(dataSourceFactory, pagedListConfig).build();
    }*/
}