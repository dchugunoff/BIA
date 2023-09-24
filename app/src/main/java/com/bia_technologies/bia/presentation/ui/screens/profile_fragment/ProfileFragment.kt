package com.bia_technologies.bia.presentation.ui.screens.profile_fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import coil.load
import coil.transform.CircleCropTransformation
import com.bia_technologies.bia.R
import com.bia_technologies.bia.databinding.FragmentProfileBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ProfileFragment : Fragment() {

    private var _binding: FragmentProfileBinding? = null
    private val binding: FragmentProfileBinding
        get() = _binding ?: throw RuntimeException("FragmentProfileBinding == null")

    private val viewModel: ProfileScreenViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentProfileBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        bindProfile()

    }

    private fun bindProfile() {
        viewModel.user.observe(viewLifecycleOwner) {
            with(binding) {
                userName.text = it.name
                userPhoto.load(it.userPhoto) {
                    transformations(CircleCropTransformation())
                }
                userProfession.text = it.profession
                personnelNumber.text = it.personnelNumber.toString()
                numberPhone.text = it.phoneNumber
                citizenship.text = it.citizenship
                carNumber.text = it.carRegistrationNumber
                typeCar.text = it.typeCar
            }
        }
        binding.logout.setOnClickListener {
            viewModel.logout()
            findNavController().navigate(R.id.action_profileFragment_to_authorisationPhoneNumberFragment)
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}