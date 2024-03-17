package com.althaus.dev.project04_cartelera.data.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.althaus.dev.project04_cartelera.data.model.Reservation

@Dao
interface ReservationDao {
    @Insert
    suspend fun insertReservation(reservation: Reservation)

    @Query("SELECT * FROM reservations")
    suspend fun getReservationsByUserId(): List<Reservation>
}
