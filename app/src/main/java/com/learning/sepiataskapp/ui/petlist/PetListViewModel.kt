package com.learning.sepiataskapp.ui.petlist

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.learning.sepiataskapp.data.model.Pet
import com.learning.sepiataskapp.data.repository.PetRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class PetListViewModel : ViewModel() {

    private val _petListLiveData = MutableLiveData<ArrayList<Pet>>()
    val petListLiveData: LiveData<ArrayList<Pet>>
        get() = _petListLiveData

    private val repository = PetRepository()


    fun getPetList() {
        //using Dispatchers.IO as we will be performing network or database operation to fetch the data
        viewModelScope.launch(Dispatchers.IO) {
            val response = repository.fetchPetList()
            _petListLiveData.postValue(response.pets)
        }
    }
}