package ru.kulsha.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import ru.kulsha.model.Product;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

//    Optional<Product> findByName(String name);
//    @Query("select p from Product p where p.price < 2000")
//    List<Product> findPriceIsUnder();
//
//    @Query("select p.price from Product p where p.title=:title")
//    Integer hqlGetPriceByTitle (String title);
//
//    @Query(value = "select price from products where title= :title", nativeQuery = true)
//    Integer nativeSqlGetPriceByTitle (String title);

    //Если бы у продуктов был дополнительный атрибут, то неленивая загрузка
    //@Query("select p from Product p join fetch p.attribute where p.id=:id")

}





