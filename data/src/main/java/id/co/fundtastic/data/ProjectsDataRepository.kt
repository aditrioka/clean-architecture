package id.co.fundtastic.data

import id.aditrioka.domain.model.Project
import id.aditrioka.domain.repository.ProjectsRepository
import id.co.fundtastic.data.mapper.ProjectMapper
import id.co.fundtastic.data.repository.ProjectsCache
import id.co.fundtastic.data.store.ProjectsDataStoreFactory
import io.reactivex.Completable
import io.reactivex.Observable
import io.reactivex.functions.BiFunction
import javax.inject.Inject

class ProjectsDataRepository @Inject constructor(
        private val mapper: ProjectMapper,
        private val cache: ProjectsCache,
        private val factory: ProjectsDataStoreFactory)
    : ProjectsRepository {

    override fun getProjects(): Observable<List<Project>> {
        return Observable.zip(cache.areProjectsCached().toObservable(),
                cache.isProjectsCacheExpired().toObservable(),
                BiFunction<Boolean, Boolean, Pair<Boolean, Boolean>> { areCached, isExpired ->
                    Pair(areCached, isExpired)
                })
                .flatMap {
                    factory.getDataStore(it.first, it.second).getProjects()
                }
                .flatMap { projects ->
                    factory.getCachedDataStore()
                            .saveProjects(projects)
                            .andThen(Observable.just(projects))

                }
                .map {
                    it.map {
                        mapper.mapFromEntity(it)
                    }
                }
    }

    override fun bookmarkProject(projectId: String): Completable {
        return factory.getCachedDataStore().setProjectAsBookmarked(projectId)
    }

    override fun unbookmarkProject(projectId: String): Completable {
        return factory.getCachedDataStore().setProjectAsBookmarked(projectId)
    }

    override fun getBookmarkedProjects(): Observable<List<Project>> {
        return factory.getCachedDataStore().getBookmarkedProjects()
                .map {
                    it.map { mapper.mapFromEntity(it) }
                }
    }
    
}