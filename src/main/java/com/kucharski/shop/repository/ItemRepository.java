package com.kucharski.shop.repository;


import com.kucharski.shop.entity.Item;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface ItemRepository extends JpaRepository<Item, Long> {
    Item findByItemId(String itemId);
    boolean existsByName(String itemName);
    Item findByName(String itemName);
}

