package com.bia_technologies.bia.presentation.ui.screens.task_screen

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.bia_technologies.bia.databinding.FragmentTaskBinding
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class TaskFragment : Fragment() {

    private var _binding: FragmentTaskBinding? = null
    private val binding: FragmentTaskBinding
        get() = _binding ?: throw RuntimeException("FragmentTaskBinding == null")

    private val viewModel: TaskScreenViewModel by viewModels()

    @Inject
    lateinit var adapter: TaskAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentTaskBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        bindAdapter()
    }


    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    private fun bindAdapter() {
        viewModel.taskList.observe(viewLifecycleOwner) {
            binding.taskRecyclerView.adapter = adapter
            adapter.submitList(it)
        }
    }
}