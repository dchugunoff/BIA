package com.bia_technologies.bia.presentation.ui.screens.task_screen

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.viewModelScope
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.bia_technologies.bia.R
import com.bia_technologies.bia.databinding.FragmentDetailTaskBinding
import com.google.android.material.bottomnavigation.BottomNavigationView
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class DetailTaskFragment : Fragment() {

    private var _binding: FragmentDetailTaskBinding? = null
    private val binding: FragmentDetailTaskBinding
        get() = _binding ?: throw RuntimeException("FragmentDetailTaskBinding == null")

    private val viewModel: TaskScreenViewModel by viewModels()

    private val args: DetailTaskFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        (activity as AppCompatActivity).findViewById<BottomNavigationView>(R.id.bottom_nav_menu)?.visibility =
            View.GONE
        _binding = FragmentDetailTaskBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        taskBind()
        navigateButtonsBind()
    }

    private fun taskBind() {
        val taskId = args.id
        viewModel.viewModelScope.launch {
            val task = viewModel.getTaskById(taskId)
            with(binding) {
                type.text = task?.type
                city.text = task?.city
                orderDate.text = task?.orderDate
                timeOrder.text = task?.arrivalTime
                startOrderPoint.text = task?.startingPoint
                endOrderPoint.text = task?.endPoint
                typeBody.text = task?.carBodyType
                orderDetail.text = task?.orderDetails
                paymentDetail.text = task?.paymentOptions
                contactPhoneNumber.text = task?.contactPhone
                contactName.text = task?.contactFullName
            }
        }
    }

    private fun navigateButtonsBind() {
        with(binding) {
            incidentButton.setOnClickListener {
                val action = DetailTaskFragmentDirections.actionDetailTaskFragmentToIncidentsFragment()
                findNavController().navigate(action)
            }
            attachDocumentsButton.setOnClickListener {
                val action = DetailTaskFragmentDirections.actionDetailTaskFragmentToAttachDocumentsFragment()
                findNavController().navigate(action)
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }


}