package bo.com.dt.challenge.model.repository;

import bo.com.dt.challenge.model.entity.Order;
import bo.com.dt.challenge.model.entity.Restaurant;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
@Qualifier("OrderRepository")
public interface OrderRepository extends JpaRepository<Order, Long> {
    @Query(value = "select o from Order o where o.id = ?1 and o.deletedDate is null")
    Optional<Order> getOrdersById(long id);

}
