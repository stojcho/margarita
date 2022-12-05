package com.stoycho.margarita.controller;

import com.stoycho.margarita.model.DashboardOrder;
import com.stoycho.margarita.model.DashboardProduct;
import com.stoycho.margarita.serviceInterface.IDashboardService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@RestController
@AllArgsConstructor
@RequestMapping("/api/dashboard")
public class DashboardController {
    private final IDashboardService iDashboardService;
    @GetMapping("/product")
    public ResponseEntity<List<DashboardProduct>> getDataGorProductGraph() {
        List<DashboardProduct> dashboardProducts = iDashboardService.getDashboardDataProducts();
            return ResponseEntity.ok().body(dashboardProducts);
    }
    @GetMapping("/order")
    public ResponseEntity<List<DashboardOrder>> getDataGorOrderGraph() {
        List<DashboardOrder> dashboardOrders = iDashboardService.getDashboardDataOrders();
        return ResponseEntity.ok().body(dashboardOrders);
    }
}
