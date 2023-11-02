package com.promecarus.githubusercompose

import androidx.datastore.core.DataStore
import com.promecarus.githubusercompose.MainApplication.Companion.app
import org.junit.Test
import org.koin.core.annotation.KoinExperimentalAPI
import org.koin.test.KoinTest
import org.koin.test.verify.verify

@OptIn(KoinExperimentalAPI::class)
class MainApplicationTest : KoinTest {
    @Test
    fun `all modules are verified`() {
        app.verify(extraTypes = listOf(DataStore::class))
    }
}
