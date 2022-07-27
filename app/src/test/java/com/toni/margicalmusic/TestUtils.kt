package com.toni.margicalmusic

import com.toni.margicalmusic.utils.AppDispatchers
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.test.UnconfinedTestDispatcher

class TestUtiDispatchers : AppDispatchers {
    override fun io(): CoroutineDispatcher = UnconfinedTestDispatcher()

    override fun default(): CoroutineDispatcher = UnconfinedTestDispatcher()

    override fun main(): CoroutineDispatcher = UnconfinedTestDispatcher()
}