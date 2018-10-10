package id.aditrioka.domain.interactor.bookmark

import com.nhaarman.mockito_kotlin.any
import com.nhaarman.mockito_kotlin.whenever
import id.aditrioka.domain.executor.PostExecutionThread
import id.aditrioka.domain.repository.ProjectsRepository
import id.aditrioka.domain.test.ProjectDataFactory
import io.reactivex.Completable
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.MockitoAnnotations
import java.lang.IllegalArgumentException

class UnbookmarkProjectTest {

    private lateinit var unbookmarkProject: UnbookmarkProject
    @Mock lateinit var projectsRepository: ProjectsRepository
    @Mock lateinit var postExecutionThread: PostExecutionThread

    @Before
    fun setup() {
        MockitoAnnotations.initMocks(this)
        unbookmarkProject = UnbookmarkProject(projectsRepository, postExecutionThread)
    }

    @Test
    fun unbookmarkProjectCompletes() {
        stubUnbookmarkProject(Completable.complete())
        val testObserver = unbookmarkProject.buildUseCaseCompletable(
                UnbookmarkProject.Params.forProject(ProjectDataFactory.randomUuid())).test()
        testObserver.assertComplete()

    }

    @Test(expected = IllegalArgumentException::class)
    fun unbookmarkProjectThrowsException() {
        unbookmarkProject.buildUseCaseCompletable().test()
    }

    private fun stubUnbookmarkProject(completable: Completable) {
        whenever(projectsRepository.unbookmarkProject(any()))
                .thenReturn(completable)
    }
}