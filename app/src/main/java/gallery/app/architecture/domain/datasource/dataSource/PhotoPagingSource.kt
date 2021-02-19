package gallery.app.architecture.domain.datasource.dataSource

import androidx.paging.PagingSource
import androidx.paging.PagingState
import de.joyn.myapplication.network.dto.Models
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
        val nextPage = params.key ?: 1
        return suspendCoroutine { continuation ->
            restApi.getPhotos(filter,params.loadSize,nextPage).subscribe({
                continuation.resume(LoadResult.Page(it.response,nextPage,it.totalHits + 1))
            },{
                continuation.resume(LoadResult.Error(it))
            })
        }

    }

    override fun getRefreshKey(state: PagingState<Int, Models.PhotoResponse>): Int? {
        return state.anchorPosition
    }
}