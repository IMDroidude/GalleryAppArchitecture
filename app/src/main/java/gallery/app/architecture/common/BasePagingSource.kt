package gallery.app.architecture.common

import androidx.paging.PagingState
import androidx.paging.rxjava3.RxPagingSource
import io.reactivex.rxjava3.core.Single

abstract class BasePagingSource<T : Any> :
    RxPagingSource<Int, T>() {

    override fun loadSingle(params: LoadParams<Int>): Single<LoadResult<Int, T>> {
        val position = params.key ?: 1
        return getSingle(position, params.loadSize)
            .map {
                LoadResult.Page(
                    data = it,
                    prevKey = if (position == 1) null else position - 1,
                    nextKey = if (it.isEmpty()) null else position + 1
                ) as LoadResult<Int, T>
            }
            .onErrorReturn {
                LoadResult.Error(it)
            }
    }

    override fun getRefreshKey(state: PagingState<Int, T>): Int? {
        return state.anchorPosition
    }

    abstract fun getSingle(page: Int, pageSize: Int): Single<List<T>>
}