package bo.com.dt.challenge.model.repository;

import bo.com.dt.challenge.model.entity.Category;
import bo.com.dt.challenge.model.entity.Dish;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
@Qualifier("DishRepository")
public interface DishRepository extends JpaRepository<Dish,Long>, QuerydslPredicateExecutor<Dish> {
    @Query(value = "select r from Dish r where r.id = ?1 and r.deletedDate is null")
    Optional<Dish> getDishById(long id);

    @Query(value = "select case when count(c) > 0 then true else false end from Dish c where c.name = ?1 and c.deletedDate is null")
    Boolean isDishNameRepeated(String name);

    @Query(value = "select d from Dish d inner join d.categories c where c.id = ?1")
    List<Dish> getDishesByCategory(long pCategoryId);
}