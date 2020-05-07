package com.shoplist.shoppinglist.repository;

import com.shoplist.shoppinglist.enteties.ShoppingItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ShoppingRepository extends JpaRepository<ShoppingItem, Integer> {

}
