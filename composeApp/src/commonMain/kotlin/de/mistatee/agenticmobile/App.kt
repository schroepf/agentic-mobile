package de.mistatee.agenticmobile

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalInspectionMode
import androidx.compose.material3.Text
import org.jetbrains.compose.ui.tooling.preview.Preview
import org.maplibre.compose.camera.CameraPosition
import org.maplibre.compose.camera.rememberCameraState
import org.maplibre.compose.map.MaplibreMap
import org.maplibre.compose.style.BaseStyle
import org.maplibre.spatialk.geojson.Position

internal const val MAP_STYLE_URL = "https://demotiles.maplibre.org/style.json"

@Composable
fun App() {
    AppContent(showMap = !LocalInspectionMode.current)
}

@Composable
fun AppContent(showMap: Boolean) {
    if (showMap) {
        MaplibreMap(
            modifier = Modifier.fillMaxSize(),
            baseStyle = BaseStyle.Uri(MAP_STYLE_URL),
            cameraState = rememberCameraState(
                firstPosition = CameraPosition(
                    target = Position(longitude = 10.4515, latitude = 51.1657),
                    zoom = 5.0,
                )
            ),
        )
    } else {
        Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
            Text("Agentic Mobile Preview")
        }
    }
}

@Preview
@Composable
fun AppPreview() = AppContent(showMap = false)
