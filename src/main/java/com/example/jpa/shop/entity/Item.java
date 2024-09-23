package com.example.jpa.shop.entity;

import com.example.jpa.entity.BaseEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Version;
import lombok.*;

import java.util.List;

@Getter
@Setter
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Item extends BaseEntity {
    private String name;
    private Integer price;
    private Integer stock;

    @Version
    private Long version;

    @OneToMany(mappedBy = "item")
    private List<OrderItem> orders;
}
