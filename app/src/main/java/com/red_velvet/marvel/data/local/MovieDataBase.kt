package com.red_velvet.marvel.data.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.red_velvet.marvel.data.entity.Comics
import com.red_velvet.marvel.data.entity.Events
import com.red_velvet.marvel.data.entity.EventsSearch
import com.red_velvet.marvel.data.entity.SeriesSearch
import com.red_velvet.marvel.data.local.dao.ComicsDao
import com.red_velvet.marvel.data.local.dao.EventsDao
import com.red_velvet.marvel.data.local.dao.SeriesDao


@Database(entities = [
    SeriesSearch::class,
    Events::class,
    EventsSearch::class
    , Comics::class] , version = 1)
abstract  class MovieDataBase : RoomDatabase() {
    abstract fun seriesDao(): SeriesDao
    abstract fun eventsDao(): EventsDao
    abstract fun comicsDao(): ComicsDao
    companion object {
        @Volatile private  var instance: MovieDataBase? =null
        fun getInstanceWithoutContext(): MovieDataBase {
            return  instance !!
        }
        fun getInstance(context: Context): MovieDataBase {
            return  instance ?: synchronized(this){ buildDataBase( context).also { instance =it }}
        }
        const val DATABASE_NAME="marverl"
        private  fun buildDataBase(context: Context): MovieDataBase {
            return  Room.databaseBuilder(context, MovieDataBase::class.java, DATABASE_NAME).build()
        }

    }
}