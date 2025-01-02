package com.sari.firstapp.feature.map

import android.content.Context
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import org.osmdroid.config.Configuration
import org.osmdroid.util.GeoPoint
import org.osmdroid.views.MapView
import org.osmdroid.views.overlay.FolderOverlay
import org.osmdroid.views.overlay.Polygon


@Composable
fun MapScreen() {
    val gyms = remember { mutableStateOf<List<Element>>(emptyList()) }
    val overpassService = remember { createOverpassService() }


    LaunchedEffect(Unit) {
        val query = """
            [out:json];
            node
              ["leisure"="sports_centre"]
              (around:2000,41.0082,28.9784); 
            out;
        """.trimIndent()
        val response = overpassService.getGyms(query)
        gyms.value = response.elements
    }


    AndroidView(
        modifier = Modifier
            .fillMaxWidth()
            .height(200.dp),
        factory = { context ->
            Configuration.getInstance().load(
                context,
                context.getSharedPreferences("OSMDroidPrefs", Context.MODE_PRIVATE)
            )

            val mapView = MapView(context).apply {
                setMultiTouchControls(true)
                controller.setZoom(15.0)
                controller.setCenter(GeoPoint(41.0082, 28.9784))
            }


            val folderOverlay = FolderOverlay()
            gyms.value.forEach { gym ->
                val circle = Polygon(mapView).apply {
                    fillPaint.color = android.graphics.Color.argb(100, 255, 0, 0)
                    outlinePaint.color = android.graphics.Color.RED
                    outlinePaint.strokeWidth = 3f
                    points = Polygon.pointsAsCircle(
                        GeoPoint(gym.lat, gym.lon), 200.0
                    )
                }
                folderOverlay.add(circle)
            }
            mapView.overlays.add(folderOverlay)

            mapView
        }
    )
}

