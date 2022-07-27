package com.noga.booksearching

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.widget.SearchView
import androidx.appcompat.widget.Toolbar
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

import com.noga.booksearching.databinding.ActivityMainBinding

import com.noga.booksearching.recycleview.BookAdapter
import com.noga.booksearching.viewmodel.BookDetailViewModel
import com.noga.booksearching.viewmodel.MainViewModel

class MainActivity : AppCompatActivity(), SearchView.OnQueryTextListener {
    lateinit var mainViewModel: MainViewModel
    lateinit var detailViewModel: BookDetailViewModel
    lateinit var toolbar: Toolbar
    var rvAdapter: BookAdapter? = null

    lateinit var binding: ActivityMainBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        // create  layoutManager
        val layoutManager: RecyclerView.LayoutManager = LinearLayoutManager(this)

        toolbar = binding.abActionBarMenu.root
        setSupportActionBar(toolbar)

        binding.rvBookList.layoutManager = layoutManager


        mainViewModel = ViewModelProvider(this).get(MainViewModel::class.java)
        detailViewModel = ViewModelProvider(this).get(BookDetailViewModel::class.java)

        binding.rvBookList.adapter = rvAdapter
        rvAdapter = BookAdapter { item ->
            println("item: $item")
            item.isbn13?.let { isbn13 ->
                val intent = Intent(this, BookDetail::class.java)
                intent.putExtra("isbn13", isbn13)
                startActivity(intent)
            }

        }
        mainViewModel.searchResponse.observe(this, Observer{ response ->

            if(response.error == null) {
                println("Error")
                return@Observer
            }

            response.books?.let {
                rvAdapter?.setBookList(it)
            }

        })

        binding.svSearchBooks.setOnQueryTextListener(this)
        binding.rvBookList.adapter = rvAdapter


    }

    override fun onQueryTextSubmit(query: String?): Boolean {
        query?.let{
            mainViewModel.setQuery(it)
            return true;
        }
        return false
    }

    override fun onQueryTextChange(newText: String?): Boolean {
        return true
    }

    override fun onDestroy() {
        super.onDestroy()
        mainViewModel.cancelJobs()
    }


    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        val menuInflater: MenuInflater = menuInflater
        menuInflater.inflate(R.menu.toolbar_menu, menu)
        return true
    }
}