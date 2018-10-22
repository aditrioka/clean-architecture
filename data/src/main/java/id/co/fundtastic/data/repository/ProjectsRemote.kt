package id.co.fundtastic.data.repository

import id.co.fundtastic.data.model.ProjectEntity
import io.reactivex.Observable

interface ProjectsRemote {

    fun getProjects(): Observable<List<ProjectEntity>>
    
}