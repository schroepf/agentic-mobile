package de.mistatee.agenticmobile

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithText
import androidx.test.ext.junit.runners.AndroidJUnit4
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class AppUiTest {
    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun appRendersContent() {
        composeTestRule.setContent {
            AppContent(showMap = false)
        }

        composeTestRule.onNodeWithText("Agentic Mobile Preview").assertIsDisplayed()
    }
}
