package co.istad.chhaya.itespringrestapi.service;

import co.istad.chhaya.itespringrestapi.domain.Coffee;
import co.istad.chhaya.itespringrestapi.dto.CoffeeResponse;

import java.util.List;


public interface CoffeeService {

     List<CoffeeResponse> getCoffees();

     CoffeeResponse getCoffeeById(Integer id);

     List<CoffeeResponse> searchCoffeeByName(String name );
}