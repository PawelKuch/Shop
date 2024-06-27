package com.kucharski.shop.repository;


import com.kucharski.shop.data.ExpenseDataQuery;
import com.kucharski.shop.entity.Order;
import com.kucharski.shop.statistics.GeneralStatistics;
import com.kucharski.shop.statistics.UserOrdersStatistics;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;


public interface OrderRepository extends JpaRepository<Order, Long> {
    Order findByOrderId(String orderId);

    @Query("SELECT new com.kucharski.shop.statistics.UserOrdersStatistics(o.user.userId, o.user.name, SUM(o.totalPurchaseValue)," +
            "SUM(o.revenue) ,SUM(o.income), COUNT(o) )" +
            "FROM Order o " +
            "JOIN User u ON o.user = u " +
            "GROUP BY u")
    List<UserOrdersStatistics> getUserOrdersStatistics();

    @Query("SELECT new com.kucharski.shop.statistics.GeneralStatistics(SUM(o.totalPurchaseValue), SUM(o.income), SUM(o.revenue), " +
            "COUNT(o)) " +
            "FROM Order o")
    GeneralStatistics getGeneralStatistics();

}

