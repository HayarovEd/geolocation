package com.edurda77.geolocation.ui.home

import android.R
import android.graphics.Color
import android.graphics.PointF
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.edurda77.geolocation.databinding.FragmentHomeBinding
import com.yandex.mapkit.MapKitFactory
import com.yandex.mapkit.layers.ObjectEvent
import com.yandex.mapkit.map.IconStyle
import com.yandex.mapkit.map.RotationType
import com.yandex.mapkit.mapview.MapView
import com.yandex.mapkit.user_location.UserLocationLayer
import com.yandex.mapkit.user_location.UserLocationObjectListener
import com.yandex.mapkit.user_location.UserLocationView
import com.yandex.runtime.image.ImageProvider


class HomeFragment : Fragment(), UserLocationObjectListener {

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!
    private lateinit var mapView: MapView
    private lateinit var userLocationLayer: UserLocationLayer

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        /*val homeViewModel =
            ViewModelProvider(this).get(HomeViewModel::class.java)
*/      MapKitFactory.setApiKey("a6d6e6e9-26d6-47e6-9ba9-95d317423e3a")
        MapKitFactory.initialize(requireContext())
        _binding = FragmentHomeBinding.inflate(inflater, container, false)


        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        super.onViewCreated(view, savedInstanceState)
        mapView = binding.mapview
        val mapKit = MapKitFactory.getInstance()
        mapKit.resetLocationManagerToDefault()
        userLocationLayer = mapKit.createUserLocationLayer(mapView.getMapWindow())
        userLocationLayer.isVisible = true
        userLocationLayer.isHeadingEnabled = true
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
        /*userLocationLayer.setAnchor(
            PointF((mapView.width * 0.5).toFloat(), (mapView.height * 0.5).toFloat()),
            PointF((mapView.width * 0.5).toFloat(), (mapView.height * 0.83).toFloat())
        )
        userLocationView.arrow.setIcon(
            ImageProvider.fromResource(
                requireContext(), R.drawable.ic_menu_rotate
            )
        )
        val pinIcon = userLocationView.pin.useCompositeIcon()
        pinIcon.setIcon(
            "icon",
            ImageProvider.fromResource(requireContext(), R.drawable.ic_delete),
            IconStyle().setAnchor(PointF(0f, 0f))
                .setRotationType(RotationType.ROTATE)
                .setZIndex(0f)
                .setScale(1f)
        )
        pinIcon.setIcon(
            "pin",
            ImageProvider.fromResource(requireContext(), R.drawable.ic_menu_upload),
            IconStyle().setAnchor(PointF(0.5f, 0.5f))
                .setRotationType(RotationType.ROTATE)
                .setZIndex(1f)
                .setScale(0.5f)
        )
        userLocationView.accuracyCircle.fillColor = Color.BLUE and -0x66000001*/
    }

    override fun onObjectRemoved(p0: UserLocationView) {
        TODO("Not yet implemented")
    }

    override fun onObjectUpdated(p0: UserLocationView, p1: ObjectEvent) {
        TODO("Not yet implemented")
    }
}