package co.istad.chhaya.itespringrestapi.service.impl;

import co.istad.chhaya.itespringrestapi.domain.Coffee;
import co.istad.chhaya.itespringrestapi.dto.CoffeeResponse;
import co.istad.chhaya.itespringrestapi.repository.CoffeeRepository;
import co.istad.chhaya.itespringrestapi.service.CoffeeService;
import org.springframework.stereotype.Service;

import java.util.List;



@Service
public class CoffeeServiceImpl implements CoffeeService {

    private final CoffeeRepository coffeeRepository;
    public CoffeeServiceImpl(CoffeeRepository coffeeRepository) {
        this.coffeeRepository = coffeeRepository;
    }
    @Override
    public List<CoffeeResponse> getCoffees() {

        List<Coffee> coffees = coffeeRepository.beanCoffee();
        return coffees.stream()
                .filter(coffe-> coffe.getId()>2)
                .map(coffee->new CoffeeResponse(coffee.getName(), coffee.getDescription(),coffee.getPrice()))
                .toList();
    }

    @Override
    public CoffeeResponse getCoffeeById(Integer id) {

        List<Coffee> coffees=coffeeRepository.beanCoffee();
        return coffees.stream()
                .filter(coffee -> coffee.getId().equals(id))
                .findFirst()
                .map(coffee -> new CoffeeResponse(coffee.getName(), coffee.getDescription(), coffee.getPrice()))
                .orElse(null);

    }

    @Override
    public List<CoffeeResponse> searchCoffeeByName(String name) {
        List<Coffee>coffees=coffeeRepository.beanCoffee();
        return coffees.stream()
                .filter(coffee -> coffee.getName().toLowerCase().contains(name.toLowerCase()))
                .map(coffee -> new CoffeeResponse(coffee.getName(),coffee.getDescription(),coffee.getPrice()))
                .toList();

    }
}