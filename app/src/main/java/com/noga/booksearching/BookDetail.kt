package com.noga.booksearching

import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.noga.booksearching.databinding.ActivityBookDetailBinding
import com.noga.booksearching.viewmodel.BookDetailViewModel

class BookDetail : AppCompatActivity() {
    lateinit var binding: ActivityBookDetailBinding
    lateinit var bookDetailViewModel: BookDetailViewModel
    lateinit var isbn13: String
    lateinit var toolbar: Toolbar

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityBookDetailBinding.inflate(layoutInflater)
        bookDetailViewModel = ViewModelProvider(this).get(BookDetailViewModel::class.java)
        isbn13 = intent.getStringExtra("isbn13").toString()

        toolbar = binding.actionBar.root
        setSupportActionBar(toolbar)

        supportActionBar?.let{
            it.setDisplayHomeAsUpEnabled(true)
            it.setDisplayShowHomeEnabled(true)
        }

        setContentView(binding.root)


    }

    override fun onStart() {
        super.onStart()
        bookDetailViewModel.setIsbn(isbn13)
        bookDetailViewModel.detailResponse.observe(this, Observer{
            if(it == null)
                return@Observer

            binding.tvTitleBook.text = it.title
            it.image?.let { url ->
                Glide.with(binding.root).load(url).centerCrop().placeholder(R.drawable.ic_baseline_book_24).into(binding.imageView)
            }
        })

    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId === android.R.id.home) {
            finish()
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onDestroy() {
        super.onDestroy()
    }
}