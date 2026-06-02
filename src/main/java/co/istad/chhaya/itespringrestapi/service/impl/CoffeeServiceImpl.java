

package co.istad.chhaya.itespringrestapi.service.impl;

import co.istad.chhaya.itespringrestapi.domain.Coffee;
import co.istad.chhaya.itespringrestapi.dto.CoffeeResponse;
import co.istad.chhaya.itespringrestapi.dto.CreateCoffeeRequest;
import co.istad.chhaya.itespringrestapi.dto.UpdateCoffeeRequest;
import co.istad.chhaya.itespringrestapi.repository.CoffeeRepository;
import co.istad.chhaya.itespringrestapi.service.CoffeeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.math.BigDecimal;
import java.util.List;
import java.util.Random;

@Slf4j
@Service
public class CoffeeServiceImpl implements CoffeeService {
    private  final CoffeeRepository coffeeRepository;

    public CoffeeServiceImpl (CoffeeRepository coffeeRepository){
        this.coffeeRepository=coffeeRepository;
    }

    private CoffeeResponse saveCoffee(CreateCoffeeRequest request){
        Coffee coffee=new Coffee();

        coffee.setId(new Random().nextInt(999999));
        coffee.setName(request.name());
        coffee.setDescription(request.description());
        coffee.setPrice(request.price());

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
    public void deleteCoffeeById(Integer id){
        Coffee coffeeToDelete=coffeeRepository.getCoffees()
                .stream()
                .filter(coffee -> coffee.getId().equals(id))
                .findFirst()
                .orElseThrow(()-> new ResponseStatusException(
                        HttpStatus.NOT_FOUND,
                        String.format("Coffee ID=%d doesn't exist in database", id)
                ));

        coffeeRepository.getCoffees().remove(coffeeToDelete);
        log.info("Coffee with ID ={} has been delete successfully", id);

    }

    @Override
    public CoffeeResponse updateCoffeeById(Integer id, UpdateCoffeeRequest updateCoffeeRequest) {

        return  coffeeRepository.getCoffees()
                .stream()
                .filter(coffee -> coffee.getId().equals(id))
                .findFirst()
                .map(oldCoffee ->{
                    oldCoffee.setName(updateCoffeeRequest.name());
                    oldCoffee.setDescription(updateCoffeeRequest.description());
                    oldCoffee.setPrice(updateCoffeeRequest.price());
                    return new CoffeeResponse(oldCoffee.getId(), oldCoffee.getName(), oldCoffee.getDescription());
                })
                .orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND,
                        String.format("Coffee ID =%d doesn't exist in database", id)));

    }

    @Override
    public CoffeeResponse createCoffee(CreateCoffeeRequest createCoffeeRequest) {
        return saveCoffee(createCoffeeRequest);
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
                .orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND,
                        String.format("Coffee ID =%d doesn't exist in database", id)));
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
        return saveCoffee(createCoffeeRequest);
    }
}