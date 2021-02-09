package gallery.app.architecture.repository

import de.joyn.myapplication.network.dto.Models
import gallery.app.architecture.domain.entity.PhotoModel
import io.reactivex.Single

interface Repository {
    fun getPhotosUseCase(param: PhotoModel): Single<Models.BasePhoto>
}