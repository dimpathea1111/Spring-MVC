package co.istad.chhaya.itespringrestapi.controller;

import co.istad.chhaya.itespringrestapi.domain.Coffee;
import co.istad.chhaya.itespringrestapi.dto.CoffeeResponse;
import co.istad.chhaya.itespringrestapi.service.CoffeeService;
import co.istad.chhaya.itespringrestapi.service.impl.CoffeeServiceImpl;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/coffees")
public class CoffeeController {

    private final CoffeeService coffeeService;
    public CoffeeController(CoffeeService coffeeService) {
        this.coffeeService = coffeeService ;
    }

    @GetMapping
    public List<CoffeeResponse> getCoffee() {
        return coffeeService.getCoffees();
    }

}

