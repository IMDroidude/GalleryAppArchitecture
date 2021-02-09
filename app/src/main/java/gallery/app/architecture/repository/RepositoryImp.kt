package gallery.app.architecture.repository

import de.joyn.myapplication.network.dto.Models
import gallery.app.architecture.domain.entity.PhotoModel
import gallery.app.architecture.network.RestApi
import io.reactivex.Single
import timber.log.Timber
import javax.inject.Inject

class RepositoryImp @Inject constructor(private val restApi: RestApi) : Repository {

    override fun getPhotosUseCase(param: PhotoModel): Single<Models.BasePhoto> {
        return Single.create { emitter ->
            restApi.getPhotos(param.query, param.pageSize, param.pageNum).subscribe({ response ->
                Timber.d("response : %s", response)
                emitter.onSuccess(response)
            }, {
                emitter.onError(it)
            })
        }
    }


}