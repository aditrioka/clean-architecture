package id.aditrioka.domain.interactor.browse

import id.aditrioka.domain.executor.PostExecutionThread
import id.aditrioka.domain.interactor.ObservableUseCase
import id.aditrioka.domain.model.Project
import id.aditrioka.domain.repository.ProjectsRepository
import io.reactivex.Observable
import javax.inject.Inject

class GetProjects @Inject constructor(
        private val projectsRepository: ProjectsRepository,
        postExecutionThread: PostExecutionThread)
    : ObservableUseCase<List<Project>, Nothing>(postExecutionThread) {

    override fun buildUseCaseObservable(params: Nothing?): Observable<List<Project>> {
        return projectsRepository.getProjects()
    }
}