package com.red_velvet.marvel.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.red_velvet.marvel.data.local.entity.CharacterEntity
import com.red_velvet.marvel.data.local.entity.ComicEntity
import com.red_velvet.marvel.data.local.entity.EventEntity
import com.red_velvet.marvel.data.local.entity.SearchQueryEntity
import com.red_velvet.marvel.data.local.entity.SeriesEntity
import io.reactivex.rxjava3.core.Observable

@Dao
interface MarvelDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertComics(comicEntity: List<ComicEntity>)

    @Query("SELECT * FROM COMICS")
    fun getAllComics(): Observable<List<ComicEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertCharacters(characterEntity: List<CharacterEntity>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertEvents(eventEntity: List<EventEntity>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertSeries(seriesEntity: List<SeriesEntity>)

    @Query("SELECT * FROM CHARACTERS WHERE title LIKE :searchTerm LIMIT 20")
    fun getAllCharacters(searchTerm: String?): Observable<List<CharacterEntity>>

    @Query("SELECT * FROM EVENTS WHERE title LIKE :searchTerm LIMIT 20")
    fun getAllEvents(searchTerm: String?): Observable<List<EventEntity>>

    @Query("SELECT * FROM SERIES WHERE title LIKE :searchTerm LIMIT 20")
    fun getAllSeries(searchTerm: String?): Observable<List<SeriesEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertSearchQuery(searchQuery: SearchQueryEntity)

    @Query("SELECT * FROM SEARCH_QUERIES ORDER BY timestamp DESC LIMIT 5")
    fun getAllSearchQueries(): Observable<List<SearchQueryEntity>>

    @Query("DELETE FROM SEARCH_QUERIES WHERE id = :id")
    fun deleteSearchQuery(id: Int)

}