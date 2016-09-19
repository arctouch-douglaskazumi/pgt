package com.dk.pgt

import org.junit.Before
import org.mockito.MockitoAnnotations

/**
 * Created by douglaskazumi on 9/16/16.
 */
open class BaseTest {
    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
    }
}