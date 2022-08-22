package com.edurda77.geolocation.ui.mark

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.edurda77.geolocation.R
import com.edurda77.geolocation.databinding.FragmentHomeBinding
import com.edurda77.geolocation.databinding.FragmentMarkBinding

class MarkFragment : Fragment() {


    //private lateinit var viewModel: MarkViewModel
    private var _binding: FragmentMarkBinding? = null
    private val binding get() = _binding!!
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentMarkBinding.inflate(inflater, container, false)
        return binding.root
    }


}