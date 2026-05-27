package co.istad.chhaya.itespringrestapi.repository;

import co.istad.chhaya.itespringrestapi.domain.Coffee;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class CoffeeRepository {
    public List<Coffee> beanCoffee() {
        Coffee coffee = new Coffee(1,"Ice Cream", "40%");
        Coffee coffee1 = new Coffee(2, "Expresso", "45%");
        Coffee coffee2 = new Coffee(3, "Macha", "60%");
        return List.of(coffee, coffee1, coffee2);

    }




}