package ir.borjali.animechan

import ir.borjali.animechan.di.*
import org.junit.Test
import org.koin.core.logger.Level
import org.koin.dsl.koinApplication
import org.koin.test.AutoCloseKoinTest
import org.koin.test.check.checkModules


class KoinModuleTest : AutoCloseKoinTest() {
    @Test
    fun testCoreModule() {
        koinApplication {
            printLogger(Level.DEBUG)
           modules(listOf( viewModelModule,
                repositoryModule,
                networkModule,
                databaseModule))

             }.checkModules()
    }
}

