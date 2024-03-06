package com.althaus.dev.project04_cartelera.data.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.althaus.dev.project04_cartelera.data.dao.MovieDao
import com.althaus.dev.project04_cartelera.data.dao.ReservationDao
import com.althaus.dev.project04_cartelera.data.dao.UserDao
import com.althaus.dev.project04_cartelera.data.model.Movie
import com.althaus.dev.project04_cartelera.data.model.Reservation
import com.althaus.dev.project04_cartelera.data.model.User

@Database(entities = [User::class, Movie::class, Reservation::class], version = 1, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {

    abstract fun userDao(): UserDao
    abstract fun movieDao(): MovieDao
    abstract fun reservationDao(): ReservationDao

    companion object {
        // Define la instancia de la base de datos como un Singleton
        @Volatile
        private var INSTANCE: AppDatabase? = null

        fun getDatabase(context: Context): AppDatabase {
            val tempInstance = INSTANCE
            if (tempInstance != null) {
                return tempInstance
            }
            synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    "cartelera"
                )
                    .fallbackToDestructiveMigration()
                    .build()
                INSTANCE = instance
                return instance
            }
        }
    }
}
