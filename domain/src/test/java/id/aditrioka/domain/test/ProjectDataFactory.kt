package id.aditrioka.domain.test

import id.aditrioka.domain.model.Project
import java.util.*

object ProjectDataFactory {

    fun randomUuid(): String {
        return UUID.randomUUID().toString()
    }

    fun randomBoolean(): Boolean {
        return Math.random() < 0.5
    }

    fun makeProject(): Project {
        return Project(randomUuid(), randomUuid(), randomUuid(), randomUuid(),
                randomUuid(), randomUuid(), randomUuid(), randomBoolean())
    }

    fun makeProjectList(count: Int): List<Project> {
        val project = mutableListOf<Project>()
        repeat(count) {
            project.add(makeProject())
        }
        return project
    }
}