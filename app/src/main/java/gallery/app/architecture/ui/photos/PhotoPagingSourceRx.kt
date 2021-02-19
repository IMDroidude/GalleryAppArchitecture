package gallery.app.architecture.ui.photos

import android.util.Log
import androidx.paging.PagingState
import androidx.paging.rxjava3.RxPagingSource
import de.joyn.myapplication.network.dto.Models
import gallery.app.architecture.network.RestApi
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.schedulers.Schedulers
import javax.inject.Inject
import javax.inject.Singleton

/*class Test (private val restApi: RestApi): BasePagingSource<Models.PhotoResponse>(){
    override fun getSingle(page: Int, pageSize: Int): Single<List<Models.PhotoResponse>> {
        return restApi.getPhotos("",pageSize,page)
    }
}*/

@Singleton
class PhotoPagingSourceRx @Inject constructor(
    private val restApi: RestApi
): RxPagingSource<Int, Models.PhotoResponse>() {

    private var filter: String = "Flowers"
    private var lastFilter = filter
    fun setFilter(filter: String) {
        this.filter = filter
    }

    override fun loadSingle(params: LoadParams<Int>): Single<LoadResult<Int, Models.PhotoResponse>> {
        val page = if(lastFilter == filter) params.key ?: 1 else 1
        lastFilter = filter

        return restApi.getPhotos(filter,20,page).subscribeOn(Schedulers.io()).map {

            Log.v("pagingLog","page -> $page ) ")
            LoadResult.Page(
                data = it.response,
                prevKey = if (page == 1) null else page - 1,
                nextKey = page + 1
            ) as LoadResult<Int, Models.PhotoResponse>
        }.onErrorReturn {
            LoadResult.Error(it)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, Models.PhotoResponse>): Int? {
        return state.anchorPosition
    }
}