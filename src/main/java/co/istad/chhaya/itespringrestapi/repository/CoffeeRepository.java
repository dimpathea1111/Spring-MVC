package co.istad.chhaya.itespringrestapi.repository;

import co.istad.chhaya.itespringrestapi.domain.Coffee;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Repository
public class CoffeeRepository {

    private  final  List<Coffee> coffees;

    public CoffeeRepository() {
        this.coffees = new ArrayList<>();
        Coffee coffee = new Coffee(1, "Expresso", "50%", BigDecimal.valueOf(5000));
        Coffee coffee2 = new Coffee(2, "Americano", "50%", BigDecimal.valueOf(4500));
        Coffee coffee3 = new Coffee(3, "Ice Americano", "100%", BigDecimal.valueOf(4000));
        Coffee coffee4 = new Coffee(4, "Macha", "100%", BigDecimal.valueOf(4000));
        Coffee coffee5 = new Coffee(5, "Ice Cream", "100%", BigDecimal.valueOf(4000));

        coffees.add(coffee);
        coffees.add(coffee2);
        coffees.add(coffee3);
        coffees.add(coffee4);
        coffees.add(coffee5);
    }

    public List<Coffee> getCoffees() {
        return coffees;
    }







}