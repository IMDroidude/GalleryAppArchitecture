package de.joyn.myapplication.domain.interactor

import de.joyn.myapplication.domain.executer.PostExecutionThread
import de.joyn.myapplication.domain.executer.UseCaseExecutor
import de.joyn.myapplication.domain.interactor.base.SingleUseCase
import de.joyn.myapplication.network.dto.Models
import gallery.app.architecture.domain.entity.PhotoModel
import gallery.app.architecture.repository.Repository
import io.reactivex.Single
import javax.inject.Inject

class GetPhotoUseCase @Inject
constructor(
    useCaseExecutor: UseCaseExecutor,
    postExecutionThread: PostExecutionThread,
    repository: Repository
) : SingleUseCase<Models.BasePhoto, PhotoModel>(useCaseExecutor, postExecutionThread, repository) {
    override fun interact(params: PhotoModel): Single<Models.BasePhoto> {
        return repository.getPhotosUseCase(params)
    }
}