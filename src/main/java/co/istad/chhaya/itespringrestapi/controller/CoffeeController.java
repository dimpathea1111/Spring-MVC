package co.istad.chhaya.itespringrestapi.controller;

import co.istad.chhaya.itespringrestapi.domain.Coffee;
import co.istad.chhaya.itespringrestapi.dto.CoffeeResponse;
import co.istad.chhaya.itespringrestapi.dto.CreateCoffeeRequest;
import co.istad.chhaya.itespringrestapi.service.CoffeeService;
//import co.istad.chhaya.itespringrestapi.service.impl.CoffeeServiceImpl;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.net.CacheRequest;
import java.util.List;

import static org.springframework.http.HttpStatus.CREATED;

@RestController
@RequestMapping("/api/v1/coffees")
@Slf4j


public class CoffeeController {

    private final CoffeeService coffeeService;

    public CoffeeController(CoffeeService coffeeService) {
        this.coffeeService = coffeeService;
    }


    @ResponseStatus(HttpStatus.CREATED) // customize response status code here
    @PostMapping
    public CoffeeResponse createCoffee(@Valid
            @RequestBody CreateCoffeeRequest createCoffeeRequest
    ) {
        return coffeeService.createCoffee(createCoffeeRequest);
    }


    @GetMapping
    public List<CoffeeResponse> getCoffees() {
        return coffeeService.getCoffees();
    }

    @GetMapping("/{id}")
    public CoffeeResponse getCoffeeById(@PathVariable Integer id) {
        log.info("GET id: {}", id);
        return coffeeService.getCoffeeById(id);
    }

    @GetMapping("/search")
    public List<CoffeeResponse> searchCoffees(
            @RequestParam(required = false, defaultValue = "") String name,
            @RequestParam(required = false, defaultValue = "0") BigDecimal price
    ) {
        log.info("GET search name: {}", name);
        log.info("GET search price: {}", price);
        return coffeeService.searchCoffee(name, price);
    }




}