package com.bia_technologies.bia.presentation.ui.screens.authorisation_screen

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.widget.doAfterTextChanged
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.bia_technologies.bia.databinding.FragmentAuthorisationPhoneNumberBinding

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