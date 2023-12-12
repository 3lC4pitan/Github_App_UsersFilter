package at.vipaso.assign.response

import org.junit.Assert
import org.junit.Test


class EventTest {
    @get:Test
    val contentIfNotHandled_withHandledEvent_shouldReturnNull: Unit
        get() {
            val event = Event("Test Event")
            event.getContentIfNotHandled() // Mark event as handled
            Assert.assertNull(event.getContentIfNotHandled())
        }

    @get:Test
    val contentIfNotHandled_withUnHandledEvent_shouldReturnContent: Unit
        get() {
            val event = Event("Test Event")
            Assert.assertEquals("Test Event", event.getContentIfNotHandled())
        } // Add more tests as needed for other functionalities
}

