package com.virtusademoapp.presentation.splash

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.ajs.virtusademoapp.data.UserData
import com.ajs.virtusademoapp.presentation.State
import com.ajs.virtusademoapp.utils.Utils
import com.ajs.virtusademoapp.domain.SampleUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.flow
import javax.inject.Inject
@HiltViewModel
class UserDataViewModel @Inject constructor(private val sampleUseCase: SampleUseCase) :
    ViewModel() {

    private var _usersList = MutableLiveData<List<UserData>>()

    val usersList: LiveData<List<UserData>>
        get() = _usersList

    fun updateUsersList(newList: List<UserData>) {
        _usersList.value = newList
    }
    fun getUserDataResponse() = flow {
        try {
            emit(State.DataState(sampleUseCase()))
        } catch (e: Exception) {
            e.printStackTrace()
            emit(Utils.resolveError(e))
        }
    }

}