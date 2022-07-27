package com.noga.booksearching.recycleview

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.noga.booksearching.R
import com.noga.booksearching.databinding.ListViewBookItemBinding
import com.noga.booksearching.models.ItBook
import kotlinx.android.synthetic.main.activity_main.view.*

class BookAdapter( private val onItemClicked: (ItBook) -> Unit): RecyclerView.Adapter<BookAdapter.BookViewHolder>() {

    lateinit var binding: ListViewBookItemBinding
    private var searchedBooks: List<ItBook>? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BookViewHolder {
        binding = ListViewBookItemBinding.inflate(LayoutInflater.from(parent.context),parent, false)

        return BookViewHolder(binding) {
            if (it != null) {
                searchedBooks?.get(it)?.let { item -> onItemClicked(item) }
            }
        }
    }

    override fun onBindViewHolder(holder: BookViewHolder, position: Int) {
        searchedBooks?.let {
            with(it[position]){

                binding.twBookName.text = this.title
                binding.twPriceTag.text = this.price
                binding.twBookDescr.text = this.subTitle
                this.image?.let { url ->
                    Glide.with(binding.root).load(url).centerCrop().placeholder(R.drawable.ic_baseline_book_24).into(binding.bookImage)
                }
                holder.bind(it[position])

            }
        }
    }

    override fun getItemCount(): Int {
        return searchedBooks?.size ?: 0
    }

    @SuppressLint("NotifyDataSetChanged")
    fun setBookList(searchedBooks: List<ItBook>?){
        this.searchedBooks = searchedBooks
        notifyDataSetChanged()
    }




    class BookViewHolder(binding: ListViewBookItemBinding, onItemClicked: (Int?) -> Unit) : RecyclerView.ViewHolder(binding.listViewItem){
        init {
            itemView.setOnClickListener {
                onItemClicked(adapterPosition)
            }
        }
        fun bind(model: ItBook) {
            //bind data
        }
    }

}