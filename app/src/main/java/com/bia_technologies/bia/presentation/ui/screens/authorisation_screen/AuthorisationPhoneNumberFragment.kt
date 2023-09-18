package com.bia_technologies.bia.presentation.ui.screens.authorisation_screen

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.core.widget.doAfterTextChanged
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.bia_technologies.bia.R
import com.bia_technologies.bia.databinding.FragmentAuthorisationPhoneNumberBinding
import com.google.android.material.bottomnavigation.BottomNavigationView

class AuthorisationPhoneNumberFragment : Fragment() {

    private var _binding: FragmentAuthorisationPhoneNumberBinding? = null
    private val binding: FragmentAuthorisationPhoneNumberBinding
        get() = _binding
            ?: throw RuntimeException("FragmentAuthorisationPhoneNumberBinding == null")


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        (activity as AppCompatActivity).findViewById<BottomNavigationView>(R.id.bottom_nav_menu)?.visibility =
            View.GONE
        _binding = FragmentAuthorisationPhoneNumberBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        var phoneNumber = ""
        binding.maskEditText.doAfterTextChanged {
            binding.nextButton.isEnabled = binding.maskEditText.isDone
            phoneNumber = binding.maskEditText.masked

        }
        binding.nextButton.setOnClickListener {
            val action =
                AuthorisationPhoneNumberFragmentDirections.actionAuthorisationPhoneNumberFragmentToAuthorisationCodeFragment(
                    phoneNumber
                )
            findNavController().navigate(action)
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}