package bryce.sideproject.dao;

import bryce.sideproject.model.Booking;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;

@Component
public interface BookingDao extends JpaRepository<Booking, Integer> {

    @Transactional
    @Modifying
    @Query("update Booking b set b.date=:date WHERE b.bid=:bid")
    void updateTiming(@Param("bid") int bid, @Param("date") String date);
}
