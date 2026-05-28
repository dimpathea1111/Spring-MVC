package co.istad.chhaya.itespringrestapi.controller;

import co.istad.chhaya.itespringrestapi.domain.Coffee;
import co.istad.chhaya.itespringrestapi.dto.CoffeeResponse;
import co.istad.chhaya.itespringrestapi.service.CoffeeService;
import co.istad.chhaya.itespringrestapi.service.impl.CoffeeServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/coffees")
@Slf4j
public class CoffeeController {

    private final CoffeeService coffeeService;
    public CoffeeController(CoffeeService coffeeService) {
        this.coffeeService = coffeeService ;
    }

    @GetMapping
    public List<CoffeeResponse> getCoffee() {
        return coffeeService.getCoffees();
    }

    @GetMapping("/{id}")
    public  CoffeeResponse getCoffeeById(@PathVariable Integer id){

        log.info("GET id:{}", id);
//        coffeeService.getCoffees().stream()
//                .filter(coffeeResponse -> )

        return coffeeService.getCoffeeById(id);
    }

    @GetMapping("/search")
    public List<CoffeeResponse> searchCoffeByName(
            @RequestParam(required = false, defaultValue ="" )String name,
            @RequestParam(required = false, defaultValue ="0" )Double price
    ){
        log.info("Get Search Name: {}", name);
        log.info("Get Search Prie: {}", price);
//        return null;
        return coffeeService.searchCoffeeByName(name);

    }

}

