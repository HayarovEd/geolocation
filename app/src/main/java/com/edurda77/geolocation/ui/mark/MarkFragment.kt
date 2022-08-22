package com.edurda77.geolocation.ui.mark

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.edurda77.geolocation.databinding.FragmentMarkBinding
import com.edurda77.geolocation.entity.MarkModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class MarkFragment : Fragment() {

    private val dashboardViewModel: MarkViewModel by viewModel()
    private var _binding: FragmentMarkBinding? = null
    private val binding get() = _binding!!
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentMarkBinding.inflate(inflater, container, false)
        return binding.root
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val mark = arguments?.getSerializable("amount") as MarkModel
        binding.titleEdit.setText(mark.titleMark)
        binding.annotationEdit.setText(mark.annotationMark)
        binding.saveButton.setOnClickListener {
            val title  = binding.titleEdit.text.toString()
            val annotation = binding.annotationEdit.text.toString()
            dashboardViewModel.updateMark(mark.id, title, annotation)
        }
    }

}