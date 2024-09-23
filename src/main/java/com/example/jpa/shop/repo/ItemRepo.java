package com.example.jpa.shop.repo;

import com.example.jpa.shop.entity.Item;
import jakarta.persistence.LockModeType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface ItemRepo extends JpaRepository<Item, Long> {
    @Lock(LockModeType.PESSIMISTIC_READ)
    @Query("SELECT i FROM Item i WHERE i.id = :id")
    Optional<Item> findItemForShare(
            @Param("id")
            Long id
    );

    @Lock(LockModeType.PESSIMISTIC_WRITE)
    @Query("SELECT i FROM Item i WHERE i.id = :id")
    Optional<Item> findItemForUpdate(
            @Param("id")
            Long id
    );

    @Override
    @Lock(LockModeType.PESSIMISTIC_WRITE)
    Optional<Item> findById(Long id);
}
