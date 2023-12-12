package at.vipaso.assign.api

import org.junit.Assert
import org.junit.Test


class ApiGithubTest {
    @get:Test
    val apiService_shouldNotBeNull: Unit
        get() {
            val apiService = ApiGithub.getApiService()
            Assert.assertNotNull(apiService)
        }
}

