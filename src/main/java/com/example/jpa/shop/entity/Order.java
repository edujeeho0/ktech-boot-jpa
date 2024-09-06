package com.example.jpa.shop.entity;

import com.example.jpa.entity.BaseEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.*;

import java.util.List;

@Getter
@Setter
@Entity
@Builder
@Table(name = "customer_order")
@NoArgsConstructor
@AllArgsConstructor
public class Order extends BaseEntity {
    private Integer totalPrice;
    private String address;
    @OneToMany(mappedBy = "order")
    private List<OrderItem> orderItems;
}
