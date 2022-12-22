package ru.nhmt.wappaleksandrov

import android.Manifest
import android.app.Activity
import android.content.Intent
import android.location.Geocoder
import android.net.Uri
import android.os.Bundle
import android.provider.Settings
import android.view.View
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.content.ContextCompat
import androidx.core.graphics.drawable.toBitmap
import coil.load
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import org.osmdroid.bonuspack.routing.OSRMRoadManager
import org.osmdroid.bonuspack.routing.RoadManager
import org.osmdroid.tileprovider.tilesource.TileSourceFactory
import org.osmdroid.util.GeoPoint
import org.osmdroid.views.CustomZoomButtonsController
import org.osmdroid.views.MapView
import org.osmdroid.views.overlay.Marker
import org.osmdroid.views.overlay.gestures.RotationGestureOverlay
import org.osmdroid.views.overlay.mylocation.GpsMyLocationProvider
import org.osmdroid.views.overlay.mylocation.MyLocationNewOverlay
import ru.nhmt.wappaleksandrov.databinding.ActivityMapBinding
import java.util.*

class MapActivity : Activity() {

    private lateinit var binding: ActivityMapBinding
    private val startPoint: GeoPoint = GeoPoint(56.125358440266844, 47.50335534138424);
    private lateinit var map: MapView
    private lateinit var locationOverlay: MyLocationNewOverlay


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMapBinding.inflate(layoutInflater)
        setContentView(binding.root)
        map = binding.mapView

        org.osmdroid.config.Configuration.getInstance().userAgentValue = packageName
        map.setMultiTouchControls(true)
        map.overlays.add(RotationGestureOverlay(map))

        map.zoomController.setVisibility(CustomZoomButtonsController.Visibility.NEVER)

        map.controller.setZoom(15.0)
        map.controller.setCenter(startPoint);

requestPermissions(arrayOf(Manifest.permission.ACCESS_FINE_LOCATION), 1)
    getLocation()
        setMarker("Собор Владимира", "https://avatars.mds.yandex.net/get-altay/790902/2a0000016287bd02dc1f3f6178c0a3421fa6/XXXL", GeoPoint(56.107538, 47.481875) )
        setMarker("Часованя", "https://avatars.mds.yandex.net/get-altay/367512/2a0000015b3b4bedd99fd663ff46e3817f10/XXXL", GeoPoint(56.123934, 47.487181) )
    }

    private fun getLocation() {
//        startActiviity(Intent(Settings.ACTION_MANAGE_OVERLAY_PERMISSION, Uri.parse("package:$packageName")))

        locationOverlay = MyLocationNewOverlay(GpsMyLocationProvider(this), map)
        locationOverlay.enableMyLocation()
        locationOverlay.enableFollowLocation()

        val imageDraw = ContextCompat.getDrawable(this, R.drawable.ic_pin_you)!!.toBitmap()
        locationOverlay.setDirectionAnchor(Marker.ANCHOR_CENTER, Marker.ANCHOR_CENTER)
        locationOverlay.setPersonAnchor(Marker.ANCHOR_CENTER, Marker.ANCHOR_CENTER)
        locationOverlay.setPersonIcon(imageDraw)
        locationOverlay.setDirectionIcon(imageDraw)

        map.overlays.add(locationOverlay)
    }

    private fun setMarker(name: String, image: String, geoPoint: GeoPoint) {
        val marker = Marker(map)
        marker.position = geoPoint
        marker.title = name
        marker.icon = ContextCompat.getDrawable(this, R.drawable.ic_baseline_star_24)
        marker.setAnchor(Marker.ANCHOR_CENTER, Marker.ANCHOR_BOTTOM);

        marker.setOnMarkerClickListener { marker, mapView ->
            binding.place.visibility = View.VISIBLE
            binding.imagePlace.load(image)
            return@setOnMarkerClickListener true
        }
        map.overlays.add(marker)
        map.invalidate()
    }
}