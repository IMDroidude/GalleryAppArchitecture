package gallery.app.architecture.data.extractor

import de.joyn.myapplication.domain.executer.UseCaseExecutor
import io.reactivex.Scheduler
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

// FIXME: 2/9/21 added inject to resolve
class NetworkJobExecutor ///@Inject constructor()
    : UseCaseExecutor {
    override val scheduler: Scheduler
        get() = Schedulers.io()
}