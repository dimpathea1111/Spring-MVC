package co.istad.chhaya.itespringrestapi.service;

import co.istad.chhaya.itespringrestapi.domain.Coffee;
import co.istad.chhaya.itespringrestapi.dto.CoffeeResponse;
import co.istad.chhaya.itespringrestapi.dto.CreateCoffeeRequest;

import java.math.BigDecimal;
import java.util.List;


public interface CoffeeService {

//     1. Expected result (result type: void , object, collection, int, ....)
//     2. your logic: add single Coffee
//     3. Parameter is used for client submission

     CoffeeResponse createCoffee(CreateCoffeeRequest createCoffeeRequest);


     List<CoffeeResponse> getCoffees();

     CoffeeResponse getCoffeeById(Integer id);

     List<CoffeeResponse> searchCoffee(String name, BigDecimal price );

     CoffeeResponse addCoffee(CreateCoffeeRequest createCoffeeRequest);

//     List<CoffeeResponse> searchCoffeeByName(String name , BigDecimal price);


}