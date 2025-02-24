package ag.booking.manager.repository;

import ag.booking.manager.entity.BookingUser;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
@Repository
public interface BookingUserRepository extends CrudRepository<BookingUser, Integer> {
    Optional<BookingUser> findByUsername(String username);}
