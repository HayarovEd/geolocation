package com.edurda77.geolocation.ui.dashboard

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.os.bundleOf
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.edurda77.geolocation.R
import com.edurda77.geolocation.databinding.FragmentDashboardBinding
import com.edurda77.geolocation.entity.MarkModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class DashboardFragment : Fragment(), MarksAdapter.MarkClickDeleteInterface {
    private val dashboardViewModel: DashboardViewModel by viewModel()
    private var _binding: FragmentDashboardBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentDashboardBinding.inflate(inflater, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        dashboardViewModel.getData()
        dashboardViewModel.markData.observe(viewLifecycleOwner) {
            when (it) {
                is StateDashboardFragment.Loading -> {
                    binding.progressbarList.isVisible = true
                    binding.recyclerviewList.isVisible = false
                }
                is StateDashboardFragment.Failure -> {
                    binding.progressbarList.isVisible = false
                    binding.recyclerviewList.isVisible = false
                    Toast.makeText(binding.root.context, it.error.toString(), Toast.LENGTH_LONG)
                        .show()
                }
                is StateDashboardFragment.Success -> {
                    binding.progressbarList.isVisible = false
                    binding.recyclerviewList.isVisible = true
                    initRecyclerView(it.data)
                }
                is StateDashboardFragment.Empty -> {}
            }
        }
    }

    private fun initRecyclerView(data: List<MarkModel>) {
        val recyclerView: RecyclerView = binding.recyclerviewList
        recyclerView.layoutManager = LinearLayoutManager(
            requireContext(), LinearLayoutManager
                .VERTICAL, false
        )
        val stateClickListener: MarksAdapter.OnStateClickListener =
            object : MarksAdapter.OnStateClickListener {
                override fun onStateClick(markModel: MarkModel, position: Int) {
                    val bundle = bundleOf("amount" to markModel)
                    view?.findNavController()?.navigate(R.id.action_navigation_dashboard_to_markFragment, bundle)
                }
            }
        recyclerView.adapter = MarksAdapter(data, stateClickListener, this)
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onDeleteIconClick(markModel: MarkModel) {
        dashboardViewModel.deleteMarkFromDb(markModel.id)
        dashboardViewModel.getData()
    }
}