package com.example.convidados.viewModel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.convidados.model.GuestModel
import com.example.convidados.repository.GuestRepository

class GuestsViewModel(application: Application) : AndroidViewModel(application) {

    private val repository = GuestRepository(application.applicationContext)

    private val listGuests = MutableLiveData<List<GuestModel>>()
    val guests: LiveData<List<GuestModel>> = listGuests

    fun getAll(){
        listGuests.value = repository.getAll()
    }

    fun getAbsent(){
        listGuests.value = repository.getAbsents()
    }

    fun getPresent(){
        listGuests.value = repository.getPresents()
    }

    fun delete(id: Int){
        repository.delete(id)
    }
}