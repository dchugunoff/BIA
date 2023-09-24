package com.bia_technologies.bia.presentation.ui.screens.authorisation_screen

import android.os.Bundle
import android.text.SpannableString
import android.text.style.UnderlineSpan
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.edit
import androidx.core.widget.doAfterTextChanged
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.preference.PreferenceManager
import com.bia_technologies.bia.R
import com.bia_technologies.bia.app.PHONE_NUMBER_SHARED_PREF
import com.bia_technologies.bia.databinding.FragmentAuthorisationCodeBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class AuthorisationCodeFragment : Fragment() {

    private val viewModel: AuthorisationViewModel by viewModels()

    private var _binding: FragmentAuthorisationCodeBinding? = null
    private val binding: FragmentAuthorisationCodeBinding
        get() = _binding ?: throw RuntimeException("FragmentAuthorisationCodeBinding == null")

    private val args: AuthorisationCodeFragmentArgs by navArgs()
    private lateinit var phoneNumber: String

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentAuthorisationCodeBinding.inflate(inflater, container, false)
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.codeSentTitle.text =
            String.format(getString(R.string.code_sent_string), args.phoneNumber)
        binding.maskEditText.doAfterTextChanged {
            binding.nextButton.isEnabled = binding.maskEditText.isDone
        }
        onClickListeners()
        observeTimer()
    }


    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    private fun onClickListeners() {
        binding.backIconButton.setOnClickListener {
            findNavController().popBackStack()
        }
        binding.nextButton.setOnClickListener {
            findNavController().navigate(R.id.action_authorisationCodeFragment_to_taskFragment)
            addPhoneNumber()
        }
    }


    private fun addPhoneNumber() {
        phoneNumber = args.phoneNumber
        val sharedPreferences = PreferenceManager.getDefaultSharedPreferences(requireContext())
        sharedPreferences.edit {
            putString(PHONE_NUMBER_SHARED_PREF, phoneNumber)
        }
    }

    private fun observeTimer() {
        viewModel.timer.observe(viewLifecycleOwner) {
            binding.resendCode.text = it
        }
        viewModel.startTimer()
        viewModel.isCancel.observe(viewLifecycleOwner) {
            if (it) {
                with(binding.resendCode) {
                    setTextColor(requireContext().getColor(R.color.red))
                    val resendCodeString = resources.getString(R.string.resend_code_string)
                    val underlinedString = SpannableString(resendCodeString)
                    underlinedString.setSpan(UnderlineSpan(), 0, resendCodeString.length, 0)
                    text = underlinedString
                    setOnClickListener {
                        viewModel.startTimer()
                    }
                }
            }
        }
    }
}