package com.bia_technologies.bia.presentation.ui.screens.task_screen

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.bia_technologies.bia.R
import com.bia_technologies.bia.databinding.FragmentAttachDocumentsBinding
import com.google.android.material.bottomnavigation.BottomNavigationView

class AttachDocumentsFragment : Fragment() {

    private var _binding: FragmentAttachDocumentsBinding? = null
    private val binding: FragmentAttachDocumentsBinding
        get() = _binding ?: throw RuntimeException("FragmentAttachDocumentsBinding == null")


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        (activity as AppCompatActivity).findViewById<BottomNavigationView>(R.id.bottom_nav_menu)?.visibility =
            View.GONE
        _binding = FragmentAttachDocumentsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.addPhotos.setOnClickListener {

        }
    }


    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}