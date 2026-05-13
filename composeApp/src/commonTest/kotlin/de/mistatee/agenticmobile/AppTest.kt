package de.mistatee.agenticmobile

import kotlin.test.Test
import kotlin.test.assertEquals

class AppTest {
    @Test
    fun mapBaseStylePointsToMapLibreDemoTiles() {
        assertEquals("https://demotiles.maplibre.org/style.json", MAP_STYLE_URL)
    }
}
