package com.example.jpa.shop.repo;

import com.example.jpa.shop.entity.Item;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ItemRepo extends JpaRepository<Item, Long> {
}
