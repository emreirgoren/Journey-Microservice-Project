package com.patika.basket_service.repository;

import com.patika.basket_service.model.Basket;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface BasketRepository extends JpaRepository<Basket,Long> {

    Basket findByEmail(String email);


}
