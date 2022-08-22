package com.edurda77.geolocation.ui.home

import android.graphics.PointF
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import com.edurda77.geolocation.R
import com.edurda77.geolocation.databinding.FragmentHomeBinding
import com.edurda77.geolocation.entity.MarkModel
import com.yandex.mapkit.Animation
import com.yandex.mapkit.MapKitFactory
import com.yandex.mapkit.geometry.Point
import com.yandex.mapkit.layers.GeoObjectTapEvent
import com.yandex.mapkit.layers.GeoObjectTapListener
import com.yandex.mapkit.layers.ObjectEvent
import com.yandex.mapkit.map.*
import com.yandex.mapkit.map.Map
import com.yandex.mapkit.mapview.MapView
import com.yandex.mapkit.user_location.UserLocationLayer
import com.yandex.mapkit.user_location.UserLocationObjectListener
import com.yandex.mapkit.user_location.UserLocationView
import com.yandex.runtime.image.ImageProvider
import org.koin.androidx.viewmodel.ext.android.viewModel


class HomeFragment : Fragment(), UserLocationObjectListener, GeoObjectTapListener, InputListener {

    private val homeViewModel: HomeViewModel by viewModel()
    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!
    private lateinit var mapView: MapView
    private lateinit var userLocationLayer: UserLocationLayer

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        MapKitFactory.initialize(requireContext())
        _binding = FragmentHomeBinding.inflate(inflater, container, false)


        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        super.onViewCreated(view, savedInstanceState)
        mapView = binding.mapview
        mapView.map.isRotateGesturesEnabled = true
        val mapKit = MapKitFactory.getInstance()
        mapKit.resetLocationManagerToDefault()
        userLocationLayer = mapKit.createUserLocationLayer(mapView.mapWindow)
        userLocationLayer.isVisible = true
        userLocationLayer.isHeadingEnabled = true
        userLocationLayer.setObjectListener(this)
        mapView.map.move(
            CameraPosition(mapView.map.cameraPosition.target,
                12F, 0.0f, 0.0f),
            Animation(Animation.Type.SMOOTH, 1F),
            null)
        mapView.map.addTapListener(this)
        mapView.map.addInputListener(this)
        homeViewModel.getData()
        homeViewModel.markData.observe(viewLifecycleOwner) {
            when (it) {
                is StateHomeFragment.Loading -> {
                    binding.progressbar.isVisible=true
                }
                is StateHomeFragment.Failure -> {
                    binding.progressbar.isVisible=false
                    Toast.makeText(binding.root.context, it.error.toString(), Toast.LENGTH_LONG).show()
                }
                is StateHomeFragment.Success -> {
                    binding.progressbar.isVisible=false
                    showSavedMarks(it.data)
                }
                is StateHomeFragment.Empty -> {}
            }
        }
        //mapView.map.mapObjects.addPlacemark(Point(0.0, 10.0))
    }


    override fun onStop() {
        mapView.onStop()
        MapKitFactory.getInstance().onStop()
        super.onStop()
    }

    override fun onStart() {
        super.onStart()
        MapKitFactory.getInstance().onStart()
        mapView.onStart()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onObjectAdded(userLocationView: UserLocationView) {
        userLocationLayer.setAnchor(
            PointF((mapView.width * 0.5).toFloat(), (mapView.height * 0.5).toFloat()),
            PointF((mapView.width * 0.5).toFloat(), (mapView.height * 0.83).toFloat())
        )

    }

    override fun onObjectRemoved(p0: UserLocationView) {
    }

    override fun onObjectUpdated(p0: UserLocationView, p1: ObjectEvent) {
    }

    override fun onObjectTap(p0: GeoObjectTapEvent): Boolean {
        val selectionMetadata: GeoObjectSelectionMetadata = p0
            .geoObject
            .metadataContainer
            .getItem(GeoObjectSelectionMetadata::class.java)

        mapView.map.selectGeoObject(selectionMetadata.id, selectionMetadata.layerId)
        Toast.makeText(context, selectionMetadata.id, Toast.LENGTH_LONG).show()
        return true
    }

    override fun onMapTap(p0: Map, p1: Point) {
        //mapView.map.mapObjects.addPlacemark(p1)
    }

    override fun onMapLongTap(p0: Map, p1: Point) {
        val mark = mapView.map.mapObjects.addPlacemark(p1)
        val styleMark = IconStyle().setScale(0.1F).setAnchor(PointF(0.5f, 1.0f))
        mark.setIcon(
            ImageProvider.fromResource(requireContext(), R.drawable.ic_location_map), styleMark
        )
        //val styleText = TextStyle().setPlacement(TextStyle.Placement.TOP). setSize(16F)
        //mark.setText("Mark", styleText)
        homeViewModel.addMark(
            MarkModel(
            id=0,
            longitudeMark = p1.longitude,
            latitudeMark = p1.latitude,
            titleMark = "",
            annotationMark = ""
        )
        )
    }

    private fun showSavedMarks(data: List<MarkModel>) {
        data.forEach {
            val styleMark = IconStyle().setScale(0.1F).setAnchor(PointF(0.5f, 1.0f))
            val styleText = TextStyle().setPlacement(TextStyle.Placement.TOP). setSize(16F)
            val mark = mapView.map.mapObjects.addPlacemark(Point(it.latitudeMark, it.longitudeMark))
            mark.setIcon(
                ImageProvider.fromResource(requireContext(), R.drawable.ic_location_map), styleMark
            )
            mark.setText("${it.titleMark}\n${it.annotationMark}", styleText)
        }
    }

}