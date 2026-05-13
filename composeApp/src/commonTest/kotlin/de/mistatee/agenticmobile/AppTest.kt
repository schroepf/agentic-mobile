package de.mistatee.agenticmobile

import androidx.compose.ui.test.ExperimentalTestApi
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.runComposeUiTest
import kotlin.test.Test
import kotlin.test.assertEquals

class AppTest {
    @Test
    fun mapBaseStylePointsToMapLibreDemoTiles() {
        assertEquals("https://demotiles.maplibre.org/style.json", MAP_STYLE_URL)
    }

    @OptIn(ExperimentalTestApi::class)
    @Test
    fun previewContentIsShownWhenMapIsDisabled() = runComposeUiTest {
        setContent {
            AppContent(showMap = false)
        }

        onNodeWithText("Agentic Mobile Preview").assertIsDisplayed()
    }
}
