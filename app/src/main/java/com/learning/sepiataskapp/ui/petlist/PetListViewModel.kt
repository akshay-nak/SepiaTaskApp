package com.learning.sepiataskapp.ui.petlist

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.learning.sepiataskapp.data.model.Pet
import com.learning.sepiataskapp.data.repository.PetRepository
import kotlinx.coroutines.launch

class PetListViewModel : ViewModel() {

    private val _petListLiveData = MutableLiveData<ArrayList<Pet>>()
    val petListLiveData: LiveData<ArrayList<Pet>>
        get() = _petListLiveData

    private val _configLiveData = MutableLiveData<Boolean>()
    val configLiveData: LiveData<Boolean>
        get() = _configLiveData

    private val repository = PetRepository()


    fun getPetList() {
        viewModelScope.launch {
            val response = repository.fetchPetList()
            _petListLiveData.postValue(response.pets)
        }
    }

    fun getConfig() {
        viewModelScope.launch {
            val response = repository.allowAppAccess()
            _configLiveData.postValue(response)
        }
    }
}