package de.mistatee.agenticmobile

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExtendedFloatingActionButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Text
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage
import kotlinx.coroutines.launch
import org.maplibre.compose.camera.CameraPosition
import org.maplibre.compose.camera.rememberCameraState
import org.maplibre.compose.map.MaplibreMap
import org.maplibre.compose.style.BaseStyle
import org.maplibre.spatialk.geojson.Position

private data class MapStyleOption(
    val name: String,
    val styleUrl: String,
    val previewImageUrl: String,
)

private val mapStyles = listOf(
    MapStyleOption(
        name = "Demotiles",
        styleUrl = "https://demotiles.maplibre.org/style.json",
        previewImageUrl = "https://github.com/user-attachments/assets/ad7b8ab2-0db8-466d-a6b9-0200f7ff88f6",
    ),
    MapStyleOption(
        name = "Bright",
        styleUrl = "https://tiles.openfreemap.org/styles/bright",
        previewImageUrl = "https://github.com/user-attachments/assets/ee8bba3f-1f77-4321-ad6c-ea237b73e1e0",
    ),
    MapStyleOption(
        name = "Dark",
        styleUrl = "https://tiles.openfreemap.org/styles/dark",
        previewImageUrl = "https://github.com/user-attachments/assets/ef6ac12e-8acd-463f-8d2e-9fbf6bb40fdf",
    ),
    MapStyleOption(
        name = "Fiord",
        styleUrl = "https://tiles.openfreemap.org/styles/fiord",
        previewImageUrl = "https://github.com/user-attachments/assets/a4f375b3-ba8d-41ee-934e-53dab37061fd",
    ),
    MapStyleOption(
        name = "Liberty",
        styleUrl = "https://tiles.openfreemap.org/styles/liberty",
        previewImageUrl = "https://github.com/user-attachments/assets/45588bfc-7945-4aea-bba0-13371b884e43",
    ),
    MapStyleOption(
        name = "Positron",
        styleUrl = "https://tiles.openfreemap.org/styles/positron",
        previewImageUrl = "https://github.com/user-attachments/assets/d9ef196f-1112-4f0a-a200-f5db157e50f4",
    ),
)

@Composable
fun App() {
    var selectedStyle by remember { mutableStateOf(mapStyles.first()) }
    var isStyleSheetVisible by remember { mutableStateOf(false) }
    val styleSheetState = rememberModalBottomSheetState(skipPartiallyExpanded = true)
    val coroutineScope = rememberCoroutineScope()

    Box(modifier = Modifier.fillMaxSize()) {
        MaplibreMap(
            modifier = Modifier.fillMaxSize(),
            baseStyle = BaseStyle.Uri(selectedStyle.styleUrl),
            cameraState = rememberCameraState(
                firstPosition = CameraPosition(
                    target = Position(longitude = 10.4515, latitude = 51.1657),
                    zoom = 5.0,
                )
            ),
        )

        ExtendedFloatingActionButton(
            modifier = Modifier
                .align(Alignment.BottomEnd)
                .padding(16.dp),
            onClick = { isStyleSheetVisible = true },
            text = { Text("Layers") },
        )
    }

    if (isStyleSheetVisible) {
        ModalBottomSheet(
            onDismissRequest = { isStyleSheetVisible = false },
            sheetState = styleSheetState,
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp),
                verticalArrangement = Arrangement.spacedBy(12.dp),
            ) {
                Text(
                    text = "Map style",
                    style = MaterialTheme.typography.titleMedium,
                )

                mapStyles.chunked(2).forEach { rowStyles ->
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.spacedBy(12.dp),
                    ) {
                        rowStyles.forEach { style ->
                            val selected = style.styleUrl == selectedStyle.styleUrl
                            Card(
                                modifier = Modifier
                                    .weight(1f)
                                    .clickable {
                                        selectedStyle = style
                                        coroutineScope.launch {
                                            styleSheetState.hide()
                                            isStyleSheetVisible = false
                                        }
                                    },
                                border = BorderStroke(
                                    width = if (selected) 2.dp else 1.dp,
                                    color = if (selected) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.outline,
                                ),
                                colors = CardDefaults.cardColors(
                                    containerColor = MaterialTheme.colorScheme.surface,
                                ),
                            ) {
                                Column(
                                    modifier = Modifier.padding(8.dp),
                                    verticalArrangement = Arrangement.spacedBy(8.dp),
                                ) {
                                    AsyncImage(
                                        model = style.previewImageUrl,
                                        contentDescription = "${style.name} style preview",
                                        modifier = Modifier
                                            .fillMaxWidth()
                                            .aspectRatio(1.2f)
                                            .clip(RoundedCornerShape(8.dp)),
                                        contentScale = ContentScale.Crop,
                                    )
                                    Text(
                                        text = style.name,
                                        style = MaterialTheme.typography.bodyMedium,
                                    )
                                }
                            }
                        }
                        if (rowStyles.size == 1) {
                            Spacer(modifier = Modifier.weight(1f))
                        }
                    }
                }
            }
            Spacer(modifier = Modifier.height(24.dp))
        }
    }
}
