package bo.com.dt.challenge.model.repository;

import bo.com.dt.challenge.model.entity.Restaurant;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
@Qualifier("RestaurantRepository")
public interface RestaurantRepository extends JpaRepository<Restaurant, Long> { //El segundo parametro Long es de nuestro id de la clase restaurant
    @Query(value = "select r from Restaurant r where r.id = ?1 and r.deletedDate is null")
    Optional<Restaurant> getRestaurantById(long id);

    @Query(value = "select case when count(r) > 0 then true else false end from Restaurant r where r.name = ?1 and r.deletedDate is null")
    Boolean isRestaurantNameRepeated(String name);
}