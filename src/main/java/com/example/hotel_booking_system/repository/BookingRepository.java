// package com.example.hotel_booking_system.repository;

// import java.time.LocalDate;
// import java.util.List;

// import org.springframework.data.jpa.repository.JpaRepository;
// import org.springframework.data.jpa.repository.Query;
// import org.springframework.data.repository.query.Param;

// import com.example.hotel_booking_system.models.Booking;
// import com.example.hotel_booking_system.models.Room;

// public interface BookingRepository extends JpaRepository<Booking, Integer>{

//      List<Booking> findByCustomerId(int customerId);

//     List<Booking> findByRoomId(int roomId);

//     List<Booking> findByArrivalDateBetween(LocalDate startDate, LocalDate endDate);

//     @Query("SELECT b FROM Booking b WHERE b.customer.id = :customerId AND b.arrivalDate BETWEEN :startDate AND :endDate")
//     List<Booking> findBookingsByCustomerAndDateRange(@Param("customerId") int customerId, @Param("startDate") LocalDate startDate, @Param("endDate") LocalDate endDate);

//     @Query("SELECT r FROM Room r WHERE r.id = (SELECT b.room.id FROM Booking b GROUP BY b.room.id ORDER BY COUNT(b) DESC LIMIT 1)")
//     Room findMostBookedRoom();


// }
