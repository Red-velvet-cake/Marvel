package com.red_velvet.marvel.data.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.red_velvet.marvel.data.Comics

@Database(entities = [Comics::class] , version = 1)
abstract  class ComicsDataBase :RoomDatabase() {
    abstract fun comicsDao(): ComicsDao

    companion object {
        @Volatile private  var instance:ComicsDataBase? =null
        fun getInstanceWithoutContext():ComicsDataBase {
            return  instance !!
        }
        fun getInstance(context:Context):ComicsDataBase {
            return  instance?: synchronized(this){buildDataBase( context).also { instance=it }}
        }
        const val DATABASE_NAME="marverl"
       private  fun buildDataBase(context:Context):ComicsDataBase{
            return  Room.databaseBuilder(context,ComicsDataBase::class.java,DATABASE_NAME).build()
        }

    }
}