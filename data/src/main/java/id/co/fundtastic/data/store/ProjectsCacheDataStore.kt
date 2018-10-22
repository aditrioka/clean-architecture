package id.co.fundtastic.data.store

import id.co.fundtastic.data.model.ProjectEntity
import id.co.fundtastic.data.repository.ProjectsCache
import id.co.fundtastic.data.repository.ProjectsDataStore
import io.reactivex.Completable
import io.reactivex.Observable
import javax.inject.Inject

class ProjectsCacheDataStore @Inject constructor(
        private val projectsCache: ProjectsCache)
    : ProjectsDataStore {

    override fun getProjects(): Observable<List<ProjectEntity>> {
        return projectsCache.getProjects()
    }

    override fun saveProjects(projects: List<ProjectEntity>): Completable {
        return projectsCache.saveProjects(projects)
    }

    override fun clearProjects(): Completable {
        return projectsCache.clearProjects()
    }

    override fun getBookmarkedProjects(): Observable<List<ProjectEntity>> {
        return projectsCache.getBookmarkedProjects()
    }

    override fun setProjectAsBookmarked(projectId: String): Completable {
        return projectsCache.setProjectAsBookmarked(projectId)
    }

    override fun setProjectAsNotBookrmaked(projectId: String): Completable {
        return projectsCache.setProjectAsNotBookmarked(projectId)
    }
}