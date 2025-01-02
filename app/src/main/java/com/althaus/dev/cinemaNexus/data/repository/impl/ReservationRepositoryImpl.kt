//package com.althaus.dev.project04_cartelera.data.repository.impl
//
//import com.althaus.dev.project04_cartelera.data.dao.ReservationDao
//import com.althaus.dev.project04_cartelera.data.model.Reservation
//import com.althaus.dev.project04_cartelera.data.repository.ReservationRepository
//
//class ReservationRepositoryImpl(val reservationDao: ReservationDao) :
//    ReservationRepository {
//
//    override suspend fun makeReservation(reservation: Reservation) {
//        reservationDao.insertReservation(reservation)
//    }
//
//    override suspend fun getUserReservations(): List<Reservation> {
//        return reservationDao.getReservationsByUserId()
//    }
//}
