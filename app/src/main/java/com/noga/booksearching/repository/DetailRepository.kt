package com.noga.booksearching.repository

import androidx.lifecycle.LiveData
import com.noga.booksearching.api.BookRestApi
import com.noga.booksearching.models.ItBookDetail
import kotlinx.coroutines.*

object DetailRepository {
    var completableJob: CompletableJob? = null

    fun getBookDetail(query: String): LiveData<ItBookDetail> {
        completableJob = Job()
        return object: LiveData<ItBookDetail>(){
            override fun onActive() {
                super.onActive()
                completableJob?.let { job ->
                    CoroutineScope(Dispatchers.IO + job).launch {
                        val response = BookRestApi.apiService.getBookDetail(query)
                        withContext(Dispatchers.Main){
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