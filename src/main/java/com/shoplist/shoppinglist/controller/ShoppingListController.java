package com.shoplist.shoppinglist.controller;

import com.shoplist.shoppinglist.enteties.ShoppingItem;
import com.shoplist.shoppinglist.repository.ShoppingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class ShoppingListController {

    private ShoppingRepository shoppingRepository;

    @Autowired
    public ShoppingListController(ShoppingRepository shoppingRepository) {
        this.shoppingRepository = shoppingRepository;
    }

    @GetMapping
    public String indexPage(Model model) {
        model.addAttribute("items", shoppingRepository.findAll());
        model.addAttribute("item", new ShoppingItem());
        return "index";
    }

    //добавляем новые продукты в список покупок
    @PostMapping  //отмечаем метод аннотацией типа пост запроса
    public String addShoppingItem(ShoppingItem item) {
        shoppingRepository.save(item);
        return "redirect:/";
    }

    @DeleteMapping("/{id}")
    public String deleteShopping(@PathVariable("id") Integer id) {
        shoppingRepository.deleteById(id);
        return "redirect:/";
    }
}
