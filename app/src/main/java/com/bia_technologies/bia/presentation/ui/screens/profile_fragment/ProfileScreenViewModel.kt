package com.bia_technologies.bia.presentation.ui.screens.profile_fragment

import android.content.SharedPreferences
import androidx.core.content.edit
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.bia_technologies.bia.R
import com.bia_technologies.bia.app.PHONE_NUMBER_SHARED_PREF
import com.bia_technologies.bia.domain.models.UserModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class ProfileScreenViewModel @Inject constructor(
    private val sharedPreferences: SharedPreferences
) : ViewModel() {

    private val _user = MutableLiveData<UserModel>()
    val user: LiveData<UserModel> = _user

    private fun getUserPhoneNum(): String {
        return sharedPreferences.getString(PHONE_NUMBER_SHARED_PREF, "") ?: ""
    }

    fun logout() {
        sharedPreferences.edit {
            remove(PHONE_NUMBER_SHARED_PREF)
        }
    }

    init {
        _user.value = UserModel(
            name = "Петров Иван Алексеевич",
            phoneNumber = getUserPhoneNum(),
            userPhoto = R.drawable.ava,
            profession = "Водитель",
            personnelNumber = 1111,
            citizenship = "РФ",
            typeCar = "Грузовая",
            carRegistrationNumber = "А 000 АА 199"
        )
    }
}