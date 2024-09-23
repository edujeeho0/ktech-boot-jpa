package com.example.jpa.shop;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

@RestController
@RequestMapping("shop")
@RequiredArgsConstructor
public class ShopController {
    private final ShopService service;
    @GetMapping("test")
    public String test() {
        service.createOrder();
        return "done";
    }

    @GetMapping("opt-lock")
    public String optLock() {
        ExecutorService executorService
                = Executors.newFixedThreadPool(10);
        List<Future<?>> futures = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            futures.add(executorService.submit(
                    () -> service.decreaseStockOpt()
            ));
        }

        for (int i = 0; i < 10; i++) {
            try {
                futures.get(i).get();
            } catch (ExecutionException | InterruptedException ignored) {}
        }

        service.checkItems();

        return "done";
    }

    @GetMapping("pes-lock-1")
    public String pesLock() {
        ExecutorService executorService
                = Executors.newFixedThreadPool(10);
        List<Future<?>> futures = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            futures.add(executorService.submit(
                    () -> service.decreaseStockShare()
            ));
        }

        for (int i = 0; i < 10; i++) {
            try {
                futures.get(i).get();
            } catch (ExecutionException | InterruptedException ignored) {}
        }

        service.checkItems();

        return "done";
    }
}
