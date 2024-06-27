package com.kucharski.shop.service;

import com.kucharski.shop.data.ExpenseData;
import com.kucharski.shop.entity.Expense;
import com.kucharski.shop.entity.User;
import com.kucharski.shop.repository.ExpenseRepository;
import com.kucharski.shop.repository.ItemRepository;
import com.kucharski.shop.repository.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Service
public class ExpensesBaseService {
    ItemRepository itemRepository;
    UserRepository userRepository;
    ExpenseRepository expenseRepository;
    ToDataService toDataService;
    public ExpensesBaseService(ItemRepository itemRepository, UserRepository userRepository,
                               ExpenseRepository expenseRepository, ToDataService toDataService){
        this.expenseRepository = expenseRepository;
        this.toDataService = toDataService;
        this.userRepository = userRepository;
        this.itemRepository = itemRepository;
    }
    @Transactional
    public void addExpense(String userId, String item, int amount, double purchasePrice){
        User user = userRepository.findByUserId(userId);
        if (user == null) throw new RuntimeException();

        Expense expense = new Expense();
        expense.setUser(user);
        expense.setAmount(amount);
        expense.setExpensePrice(purchasePrice);
        expense.setTotalExpenseValue(amount*purchasePrice);
        expense.setExpenseId(UUID.randomUUID().toString());
        expense.setItem(item);
        expense.setExpenseDateTime(LocalDateTime.now());
        expenseRepository.save(expense);
    }

    @Transactional
    public List<ExpenseData> getExpenses(){
        return toDataService.getExpenses(expenseRepository.findAll());
    }
}

