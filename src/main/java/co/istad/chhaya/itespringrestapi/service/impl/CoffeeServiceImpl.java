package co.istad.chhaya.itespringrestapi.service.impl;//



import co.istad.chhaya.itespringrestapi.domain.Coffee;
import co.istad.chhaya.itespringrestapi.dto.CoffeeResponse;
import co.istad.chhaya.itespringrestapi.dto.CreateCoffeeRequest;
import co.istad.chhaya.itespringrestapi.repository.CoffeeRepository;
import co.istad.chhaya.itespringrestapi.service.CoffeeService;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Random;

@Service
public class CoffeeServiceImpl implements CoffeeService {
    private  final CoffeeRepository coffeeRepository;

    public CoffeeServiceImpl (CoffeeRepository coffeeRepository){
        this.coffeeRepository=coffeeRepository;
    }

    @Override
    public CoffeeResponse createCoffee(CreateCoffeeRequest createCoffeeRequest) {
        Coffee coffee=new Coffee();
        coffee.setId(new Random().nextInt(9999));
        coffee.setId(new Random().nextInt(999999)); // System Generated Data
        coffee.setName(createCoffeeRequest.name());
        coffee.setDescription(createCoffeeRequest.description());
        coffee.setPrice(createCoffeeRequest.price());

        boolean isExisting = coffeeRepository.getCoffees()
                .stream()
                .anyMatch(c -> c.getId().equals(coffee.getId()));

        if (isExisting) {
            throw new RuntimeException("Coffee ID already exists");
        }

        coffeeRepository.getCoffees().add(coffee);
        return new CoffeeResponse(coffee.getId(), coffee.getName(), coffee.getDescription());

    }



    @Override
    public List<CoffeeResponse> getCoffees() {
        return coffeeRepository.getCoffees().stream()
                .map(coffee -> new CoffeeResponse(coffee.getId(),coffee.getName(), coffee.getDescription()))
                .toList();

    }

    @Override
    public CoffeeResponse getCoffeeById(Integer id) {
        return coffeeRepository.getCoffees()
                .stream()
                .filter(coffee -> coffee.getId().equals(id))
                .map(coffee -> new CoffeeResponse(coffee.getId(), coffee.getName(), coffee.getDescription()))
                .findFirst()
                .orElseThrow();
    }

    @Override
    public List<CoffeeResponse> searchCoffee(String name, BigDecimal price) {
        return coffeeRepository.getCoffees()
                .stream()
                .filter(coffee ->
                        coffee.getName().toLowerCase().contains(name.toLowerCase()) ||
                                coffee.getPrice().equals(price)
                )
                .map(coffee -> new CoffeeResponse(coffee.getId(), coffee.getName(), coffee.getDescription()))
                .toList();
    }

    @Override
    public CoffeeResponse addCoffee(CreateCoffeeRequest createCoffeeRequest) {

        Coffee coffee = new Coffee();

        coffee.setId(new Random().nextInt(999999));

        coffee.setName(createCoffeeRequest.name());
        coffee.setDescription(createCoffeeRequest.description());

        coffee.setPrice(createCoffeeRequest.price());

        boolean isExisting = coffeeRepository.getCoffees()
                .stream()
                .anyMatch(c -> c.getId().equals(coffee.getId()));

        if (isExisting) {
            throw new RuntimeException("Coffee ID already exists");
        }

        coffeeRepository.getCoffees().add(coffee);

        return new CoffeeResponse(
                coffee.getId(),
                coffee.getName(),
                coffee.getDescription()
        );
    }


}
