package gallery.app.architecture.domain.datasource.dataSource

import androidx.paging.PagingSource
import androidx.paging.PagingState
import de.joyn.myapplication.domain.interactor.GetPhotoUseCase
import de.joyn.myapplication.network.dto.Models
import gallery.app.architecture.domain.entity.PhotoModel
import gallery.app.architecture.network.RestApi
import io.reactivex.Single
import timber.log.Timber
import javax.inject.Inject
import kotlin.coroutines.resume
import kotlin.coroutines.suspendCoroutine

class PhotoPagingSource @Inject constructor(
    private val restApi: RestApi
    ) : PagingSource<Int, Models.PhotoResponse>() {

    private var filter: String = "Flowers"
    fun setFilter(filter: String) {
        this.filter = filter
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Models.PhotoResponse> {
        val pageNum = params.key ?: 1
        return fetchResult(pageNum,params.loadSize)
    }

    suspend fun fetchResult(pageNumber:Int,pageSize:Int):LoadResult<Int,Models.PhotoResponse>{
        return suspendCoroutine { continuation ->
            restApi.getPhotos(filter, pageSize, pageNumber).subscribe({ response ->
                Timber.d("response : %s", response)
                continuation.resume(LoadResult.Page(response.response,response.totalHits,response.total))
            }, {
                continuation.resume(LoadResult.Error(it))
            })
            /*getPhotosUseCase(PhotoModel(filter,pageSize,pageNumber)).subscribe({
                continuation.resume(LoadResult.Page(it.response,it.totalHits,it.total))
            },{
                continuation.resume(LoadResult.Error(it))
            })*/
        }
    }

    /*fun getPhotosUseCase(param: PhotoModel): Single<Models.BasePhoto> {
        return Single.create { emitter ->
            restApi.getPhotos(param.query, param.pageSize, param.pageNum).subscribe({ response ->
                Timber.d("response : %s", response)
                emitter.onSuccess(response)
            }, {
                emitter.onError(it)
            })
        }
    }*/
    override fun getRefreshKey(state: PagingState<Int, Models.PhotoResponse>): Int? {
        return state.anchorPosition
    }
}