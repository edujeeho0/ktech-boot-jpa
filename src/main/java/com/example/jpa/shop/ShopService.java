package com.example.jpa.shop;

import com.example.jpa.shop.entity.Item;
import com.example.jpa.shop.entity.Order;
import com.example.jpa.shop.entity.OrderItem;
import com.example.jpa.shop.repo.ItemRepo;
import com.example.jpa.shop.repo.OrderItemRepo;
import com.example.jpa.shop.repo.OrderRepo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
public class ShopService {
    private final Long itemId;

    private final ItemRepo itemRepo;
    private final OrderRepo orderRepo;
    private final OrderItemRepo orderItemRepo;

    public ShopService(ItemRepo itemRepo, OrderRepo orderRepo, OrderItemRepo orderItemRepo) {
        this.itemRepo = itemRepo;
        this.orderRepo = orderRepo;
        this.orderItemRepo = orderItemRepo;

        this.itemId = this.itemRepo.save(Item.builder()
                .name("mouse")
                .price(10000)
                .stock(115)
                .build()).getId();
    }

    @Transactional
    public void createOrder() {
        // 0. 주문정보를 생성한다.
        Order newOrder = orderRepo.save(Order.builder()
                .totalPrice(0)
                .address("seoul")
                .build());

        // 1. 구매하려는 물품 정보를 조회한다.
        Item item = itemRepo.findById(itemId)
                .orElseThrow();

        // 2. 해당 물품의 재고가 구매 수량보다 많음을 확인한다.
        // 10개씩 주문함을 가정
        if (!(item.getStock() >= 10))
            // 10개 미만시 예외
            throw new IllegalStateException();

        // 3. 물품 주문 정보를 넣어준다.
        OrderItem orderItem = orderItemRepo.save(OrderItem.builder()
                .order(newOrder)
                .item(item)
                .count(10)
                .build());

        // 4. 구매한 갯수만큼 stock을 차감한다.
        item.setStock(item.getStock() - 10);
        item = itemRepo.save(item);
        if (item.getStock() < 0)
            throw new IllegalStateException();
    }

    @Transactional
    public void decreaseStockOpt() {
        Item item = itemRepo.findById(itemId)
                .orElseThrow();
        item.setStock(item.getStock() - 10);
        itemRepo.save(item);
    }

    @Transactional
    public void checkItems() {
        Item item = itemRepo.findById(itemId)
                .orElseThrow();
        log.info(item.getStock().toString());
    }

}
