package com.kucharski.shop.service;

import com.kucharski.shop.data.ExpenseData;
import com.kucharski.shop.data.ItemData;
import com.kucharski.shop.data.OrderData;
import com.kucharski.shop.data.UserData;
import com.kucharski.shop.entity.Expense;
import com.kucharski.shop.entity.Item;
import com.kucharski.shop.entity.Order;
import com.kucharski.shop.entity.User;
import org.springframework.stereotype.Service;

import java.time.ZoneOffset;
import java.util.Date;
import java.util.List;

@Service
public class ToDataService {

    public List<OrderData> getOrders(List<Order> orders){
        return orders.stream().map(this::convert).toList();
    }

    public List<UserData> getUsers(List <User> users) {
        return users.stream().map(this::convert).toList();
    }

    public List<ItemData> getItems(List<Item> items){
        return items.stream().map(this::convert).toList();
    }
    public List<ExpenseData> getExpenses(List<Expense> expenses){
        return expenses.stream().map(this::convert).toList();
    }
    public OrderData convert(Order order){
        OrderData orderData = new OrderData();
        orderData.setOrderId(order.getOrderId());
        orderData.setAmount(order.getAmount());
        orderData.setPurchasePrice(order.getPurchasePrice());
        orderData.setTotalPurchaseValue(order.getTotalPurchaseValue());
        orderData.setSellPrice(order.getSellPrice());
        orderData.setRevenue(order.getRevenue());
        orderData.setIncome(order.getIncome());
        orderData.setUser(convert(order.getUser()));
        orderData.setItem(convert(order.getItem()));
        orderData.setOrderDateTime(Date.from(order.getOrderDateTime().toInstant(ZoneOffset.UTC)));
        orderData.setSettled(order.isSettled());
        return orderData;
    }
    public ExpenseData convert(Expense expense){
        ExpenseData expenseData = new ExpenseData();
        expenseData.setExpenseId(expense.getExpenseId());
        expenseData.setAmount(expense.getAmount());
        expenseData.setExpensePrice(expense.getExpensePrice());
        expenseData.setTotalExpenseValue(expense.getTotalExpenseValue());
        expenseData.setUser(convert(expense.getUser()));
        expenseData.setItem(expense.getItem());
        expenseData.setExpenseDateTime(Date.from(expense.getExpenseDateTime().toInstant(ZoneOffset.UTC)));
        return expenseData;
    }
    public ItemData convert(Item data){
        ItemData itemData = new ItemData();
        itemData.setItemId(data.getItemId());
        itemData.setName(data.getName());
        return itemData;
    }
    public UserData convert(User data){
        UserData userData = new UserData();
        userData.setUserId(data.getUserId());
        userData.setName(data.getName());
        return userData;
    }

}

