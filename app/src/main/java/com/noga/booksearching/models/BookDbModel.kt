package com.noga.booksearching.models

import androidx.room.*

@Entity
data class FavoriteBooks(
    @PrimaryKey val isbn13: String,
    @ColumnInfo( name ="title") val title: String?,
    @ColumnInfo( name = "imageUrl") val imageUrl: String?,
    @ColumnInfo(name="description") val description: String?
)

@Dao
interface BookDbDao{
    @Query("SELECT * from FavoriteBooks") fun getAll(): List<FavoriteBooks>

    @Query("SELECT * FROM FavoriteBooks WHERE isbn13 LIKE :isbn")
    fun searchByIsbn13(isbn: String): List<FavoriteBooks>
}