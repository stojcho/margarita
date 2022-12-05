package com.stoycho.margarita.serviceInterface;

import com.stoycho.margarita.model.DashboardOrder;
import com.stoycho.margarita.model.DashboardProduct;

import java.util.List;

public interface IDashboardService {

    List<DashboardProduct> getDashboardDataProducts();

    List<DashboardOrder> getDashboardDataOrders();
}
