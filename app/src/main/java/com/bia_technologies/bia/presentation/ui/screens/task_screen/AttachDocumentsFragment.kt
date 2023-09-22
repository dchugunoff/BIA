package com.bia_technologies.bia.presentation.ui.screens.task_screen

import android.Manifest
import android.content.pm.PackageManager
import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
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
            addPhotos()
        }
    }

    private fun addPhotos() {
        val readImagePermission = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU)
            Manifest.permission.READ_MEDIA_IMAGES
        else
            Manifest.permission.READ_EXTERNAL_STORAGE

        val requestPermission = registerForActivityResult(
            ActivityResultContracts.RequestPermission()
        ) { isGranted: Boolean ->
            if (!isGranted) {
                Toast.makeText(
                    requireContext(),
                    getString(R.string.read_permission_denied_string), Toast.LENGTH_SHORT
                ).show()
            } else {
                imageChoose()
            }
        }

        if (ContextCompat.checkSelfPermission(
                requireContext(),
                readImagePermission
            ) == PackageManager.PERMISSION_GRANTED
        ) {
            imageChoose()
        } else {
            requestPermission.launch(readImagePermission)
        }
    }

    private fun imageChoose() {
        // TODO:  
    }


    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}