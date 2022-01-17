package bo.com.dt.challenge.model.repository;

import bo.com.dt.challenge.model.entity.OrderDish;
import bo.com.dt.challenge.model.entity.Restaurant;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
@Qualifier("OrderDishRepository")
public interface OrderDishRepository extends JpaRepository<OrderDish, Long> {
    @Query(value = "select r from OrderDish r where r.id = ?1")
    Optional<OrderDish> getOrderDishById(long id);
}
