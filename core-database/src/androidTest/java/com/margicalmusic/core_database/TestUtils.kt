package com.margicalmusic.core_database

import com.margicalmusic.core_utils.AppDispatchers
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.*
import org.junit.rules.TestWatcher
import org.junit.runner.Description

class TestUtiDispatchers : AppDispatchers {
    override fun io(): CoroutineDispatcher = UnconfinedTestDispatcher()

    override fun default(): CoroutineDispatcher = UnconfinedTestDispatcher()

    override fun main(): CoroutineDispatcher = UnconfinedTestDispatcher()
}

@OptIn(ExperimentalCoroutinesApi::class)
class CustomCoroutineRule( val dispatcher: TestDispatcher = UnconfinedTestDispatcher() ) : TestWatcher( ) {

    override fun starting(description: Description) {
        super.starting(description)
        Dispatchers.setMain( dispatcher = dispatcher )
    }

    override fun finished(description: Description) {
        super.finished(description)
        Dispatchers.resetMain()
    }
}