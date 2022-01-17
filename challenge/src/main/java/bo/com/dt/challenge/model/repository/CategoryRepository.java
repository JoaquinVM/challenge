package bo.com.dt.challenge.model.repository;

import bo.com.dt.challenge.constants.State;
import bo.com.dt.challenge.model.entity.Category;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
@Qualifier("CategoryRepository")
public interface CategoryRepository extends JpaRepository<Category, Long>, QuerydslPredicateExecutor<Category> {
    @Query(value = "select c from Category c where c.id = ?1 and c.deletedDate is null")
    Optional<Category> getCategoryById(long id);

    @Query(value = "select case when count(c) > 0 then true else false end from Category c where c.name = ?1 and c.deletedDate is null")
    Boolean isCategoryNameRepeated(String name);

    @Query(value = "select c from Category c where c.state = ?1")
    List<Category> getCategoriesByState(State state);

    @Query(value = "select c from Category c inner join c.dishes d where d.id = ?1")
    List<Category> getCategoriesByDish(long pDishId);
}