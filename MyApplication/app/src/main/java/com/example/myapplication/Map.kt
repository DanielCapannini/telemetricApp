package com.example.myapplication

import android.annotation.SuppressLint
import android.content.Context
import android.location.Criteria
import android.location.Location
import android.location.LocationManager
import android.view.ViewGroup
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.viewinterop.AndroidView
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.OnLifecycleEvent
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.MapView
import com.google.android.gms.maps.model.LatLng


@Composable
fun MapView() {
    val context = LocalContext.current
    val mapView = rememberMapViewWithLifecycle()

    AndroidView(
        modifier = Modifier.fillMaxSize(),
        factory = { mapView.apply {
            getMapAsync { googleMap ->
                val location = getLocation(context)
                val latLng = location?.let { it1 -> LatLng(it1.latitude, location.longitude) }
                googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(latLng, 15f))
            }
        } }
    )
}

@SuppressLint("MissingPermission")
private fun getLocation(context: Context): Location? {
    val locationManager = context.getSystemService(Context.LOCATION_SERVICE) as LocationManager
    val locationProvider = locationManager.getBestProvider(Criteria(), false)
    return locationProvider?.let { locationManager.getLastKnownLocation(it) }
}

@Composable
fun rememberMapViewWithLifecycle(): MapView {
    val context = LocalContext.current
    val mapView = remember {
        MapView(context).apply {
            layoutParams = ViewGroup.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.MATCH_PARENT
            )
        }
    }

    val lifecycle = LocalLifecycleOwner.current.lifecycle
    DisposableEffect(mapView) {
        mapView.onCreate(null)
        mapView.onResume()

        lifecycle.addObserver(object : LifecycleObserver {
            @OnLifecycleEvent(Lifecycle.Event.ON_RESUME)
            fun onResume() {
                mapView.onResume()
            }

            @OnLifecycleEvent(Lifecycle.Event.ON_PAUSE)
            fun onPause() {
                mapView.onPause()
            }

            @OnLifecycleEvent(Lifecycle.Event.ON_DESTROY)
            fun onDestroy() {
                mapView.onDestroy()
            }
        })

        onDispose {
            mapView.onPause()
            mapView.onStop()
        }
    }

    return mapView
}