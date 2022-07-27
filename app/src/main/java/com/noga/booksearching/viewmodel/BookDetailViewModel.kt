package com.noga.booksearching.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import com.noga.booksearching.models.ItBook
import com.noga.booksearching.models.ItBookDetail
import com.noga.booksearching.repository.DetailRepository
import com.noga.booksearching.repository.MainRepository

class BookDetailViewModel: ViewModel() {

    private val _isbn13: MutableLiveData<String> = MutableLiveData()

    val detailResponse: LiveData<ItBookDetail> = Transformations.switchMap(_isbn13){
        DetailRepository.getBookDetail(it)
    }

    fun setIsbn(isbn13: String){
        val update = isbn13
        if(update.equals(_isbn13))
            return

        _isbn13.value = update
    }


    fun cancelJobs(){
        DetailRepository.cancelJobs()
    }
}