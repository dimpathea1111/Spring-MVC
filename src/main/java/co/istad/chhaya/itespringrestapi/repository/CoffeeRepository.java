package co.istad.chhaya.itespringrestapi.repository;

import co.istad.chhaya.itespringrestapi.domain.Coffee;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class CoffeeRepository {
    public List<Coffee> beanCoffee() {
        Coffee coffee = new Coffee(1,"Ice Cream", "40%", 2.00);
        Coffee coffee1 = new Coffee(2, "Expresso", "45%", 2.15);
        Coffee coffee2 = new Coffee(3, "Macha", "60%", 4.50);
        Coffee coffee3 = new Coffee(4, "Cappuccino", "20%", 2.00);
        Coffee coffee4 = new Coffee(5, "Flat White", "45%", 3.50);
        Coffee coffee5 = new Coffee(6, "Americano", "120%", 2.50);
        return List.of(coffee, coffee1, coffee2, coffee3, coffee4,coffee5);

    }




}