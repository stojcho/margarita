package com.stoycho.margarita.repository;

import com.stoycho.margarita.enums.OrderStatus;
import com.stoycho.margarita.model.DashboardOrder;
import com.stoycho.margarita.model.DashboardProduct;
import com.stoycho.margarita.model.Order;
import org.jetbrains.annotations.NotNull;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import java.util.*;

@Repository
public interface IOrderRepository extends JpaRepository<Order, Long> {
    @NotNull Page<Order> findAll(@NotNull Pageable pageable);
    List<Order> findAllByStatus_Equals(OrderStatus status);

    @Transactional
    @Query("select new com.stoycho.margarita.model.DashboardProduct(product.name,sum(items.quantity)) FROM Order as o inner join o.items items inner join items.product product group by product.name ")
    List<DashboardProduct> getAllProductOrdersForGraph();

    @Transactional
    @Query("select new com.stoycho.margarita.model.DashboardOrder(o.date,count(o.order_id)) FROM Order as o group by o.date ")
    List<DashboardOrder> getOrdersByDateForGraph();

}
