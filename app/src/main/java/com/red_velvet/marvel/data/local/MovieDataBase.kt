package com.red_velvet.marvel.data.local
import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.red_velvet.marvel.data.entity.ComicsEntity
import com.red_velvet.marvel.data.entity.EventsEntity
import com.red_velvet.marvel.data.entity.EventsSearch
import com.red_velvet.marvel.data.entity.SeriesSearch
import javax.inject.Inject
import javax.inject.Singleton


@Database(entities = [
    SeriesSearch::class,
    EventsEntity::class,
    EventsSearch::class,
    Char::class
    , ComicsEntity::class] , version = 1)
abstract class MovieDataBase : RoomDatabase() {

    abstract fun movieDao(): MovieDao

    companion object {

        @Volatile
        private var instance: MovieDataBase? = null

        @Singleton
        @Inject
        fun getInstance(context: Context): MovieDataBase {
            return instance ?: synchronized(this) {
                Room.databaseBuilder(context, MovieDataBase::class.java, DATABASE_NAME).build()
                    .also { instance = it }
            }
        }

        const val DATABASE_NAME = "marvel"
    }
}