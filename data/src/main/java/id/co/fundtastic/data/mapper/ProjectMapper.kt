package id.co.fundtastic.data.mapper

import id.aditrioka.domain.model.Project
import id.co.fundtastic.data.model.ProjectEntity
import javax.inject.Inject

class ProjectMapper @Inject constructor(): EntityMapper<ProjectEntity, Project> {

    override fun mapFromEntity(entity: ProjectEntity): Project {
        return Project(entity.id, entity.name, entity.fullName, entity.starCount,
                entity.dateCreated, entity.ownerName, entity.ownerAvatar, entity.isBookmarked)
    }

    override fun mapToEntity(domain: Project): ProjectEntity {
        return ProjectEntity(domain.id, domain.name, domain.fullName, domain.starCount,
                domain.dateCreated, domain.ownerName, domain.ownerAvatar, domain.isBookmarked)
    }
}