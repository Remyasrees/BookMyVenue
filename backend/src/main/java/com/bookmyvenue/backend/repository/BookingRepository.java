package com.bookmyvenue.backend.repository;

import com.bookmyvenue.backend.entity.Booking;
import com.bookmyvenue.backend.enums.BookingStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookingRepository
        extends JpaRepository<Booking, Long>,
        JpaSpecificationExecutor<Booking> {

    List<Booking> findByUserUserId(Long userId);

    List<Booking> findByVenueOwnerUserUserId(
            Long ownerId);

    List<Booking> findByBookingStatus(
            BookingStatus status);
}
