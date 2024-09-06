package com.example.jpa.shop.repo;

import com.example.jpa.shop.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepo extends JpaRepository<Order, Long> {}
