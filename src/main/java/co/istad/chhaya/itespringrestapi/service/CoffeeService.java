package co.istad.chhaya.itespringrestapi.service;

import co.istad.chhaya.itespringrestapi.domain.Coffee;
import co.istad.chhaya.itespringrestapi.dto.CoffeeResponse;
import co.istad.chhaya.itespringrestapi.dto.CreateCoffeeRequest;
import co.istad.chhaya.itespringrestapi.dto.UpdateCoffeeRequest;

import java.math.BigDecimal;
import java.util.List;


public interface CoffeeService {

//     void deleteCoffeeById(Integer id);


//     1. My logic is update information by coffee id
//     2. Expected  result is CoffeeResponse
//     3. Parameter is updateCoffeeRequest
//     4.

     void deleteCoffeeById(Integer id);

     CoffeeResponse updateCoffeeById(Integer id, UpdateCoffeeRequest updateCoffeeRequest);




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