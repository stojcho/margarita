package com.stoycho.margarita.service;

import com.stoycho.margarita.exception.BadRequestException;
import com.stoycho.margarita.model.DashboardOrder;
import com.stoycho.margarita.model.DashboardProduct;
import com.stoycho.margarita.repository.IOrderRepository;
import com.stoycho.margarita.serviceInterface.IDashboardService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@AllArgsConstructor
@Service
public class DashboardService implements IDashboardService {
    private final IOrderRepository iOrderRepository;

    @Override
    public List<DashboardProduct> getDashboardDataProducts(){
        List<DashboardProduct> dashboardProducts=iOrderRepository.getAllProductOrdersForGraph();
        if(dashboardProducts.isEmpty()){
            throw new BadRequestException("There are no orders found in the database");
        }
        bubbleSort(dashboardProducts);


        return dashboardProducts.stream().limit(8).collect(Collectors.toList());
    }
    @Override
    public List<DashboardOrder> getDashboardDataOrders(){
        List<DashboardOrder> dashboardOrders=iOrderRepository.getOrdersByDateForGraph();
        if(dashboardOrders.isEmpty()){
            throw new BadRequestException("There are no orders found in the database");
        }
        while (dashboardOrders.size()>13) {
            dashboardOrders.remove(0);
        }
        return dashboardOrders;
    }
    void bubbleSort(List<DashboardProduct> arr)
    {
        int n = arr.size();
        for (int i = 0; i < n-1; i++)
            for (int j = 0; j < n-i-1; j++)
                if (arr.get(j).getQuantity() < arr.get(j+1).getQuantity())
                {
                    DashboardProduct temp = arr.get(j);
                    arr.set(j,arr.get(j+1));
                    arr.set(j+1,temp);
                }
    }
}
