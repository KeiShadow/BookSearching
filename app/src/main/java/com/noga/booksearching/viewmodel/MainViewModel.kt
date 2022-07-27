package com.noga.booksearching.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import com.noga.booksearching.models.BookApiResponse
import com.noga.booksearching.models.ItBook
import com.noga.booksearching.recycleview.BookAdapter
import com.noga.booksearching.repository.MainRepository

class MainViewModel: ViewModel() {


    private val _query: MutableLiveData<String> = MutableLiveData()


    val searchResponse: LiveData<BookApiResponse> = Transformations.switchMap(_query){ query ->
        MainRepository.searchBooks(query)
    }


    fun setQuery(query: String){
        val update = query
        if(update.equals(_query))
            return

        _query.value = update
    }

    fun cancelJobs(){
        MainRepository.cancelJobs()
    }
}
