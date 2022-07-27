package com.noga.booksearching.repository

import androidx.lifecycle.LiveData
import com.noga.booksearching.api.BookRestApi
import com.noga.booksearching.models.BookApiResponse
import kotlinx.coroutines.*
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.Dispatchers.Main
import retrofit2.HttpException

object MainRepository {
    var completableJob: CompletableJob? = null

    fun searchBooks(query: String, page: String? = null): LiveData<BookApiResponse> {
        completableJob = Job()
        return object: LiveData<BookApiResponse>(){
            override fun onActive() {
                super.onActive()

               try {
                   completableJob?.let { job ->
                       CoroutineScope(IO + job).launch {
                           val response = BookRestApi.apiService.searchBooks(query)
                           withContext(Main){
                               value = response
                               job.complete()
                           }
                       }

                   }
               }catch (e: HttpException){
                   println(e.message())
               }
            }
        }

    }

    fun getNewBooks(): LiveData<BookApiResponse>{
        completableJob = Job()
        return object: LiveData<BookApiResponse>(){
            override fun onActive() {
                super.onActive()
                completableJob?.let { job ->
                    CoroutineScope(IO  + job).launch {
                        val response = BookRestApi.apiService.getNewBooks()
                        withContext(Main){
                            value = response
                            job.complete()
                        }
                    }

                }
            }
        }
    }

    fun cancelJobs(){
        completableJob?.cancel()
    }
}