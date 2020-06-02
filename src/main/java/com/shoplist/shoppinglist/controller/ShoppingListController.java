package com.shoplist.shoppinglist.controller;

import com.shoplist.shoppinglist.enteties.ShoppingItem;
import com.shoplist.shoppinglist.enteties.User;
import com.shoplist.shoppinglist.repository.ShoppingRepository;
import com.shoplist.shoppinglist.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.security.Principal;

@Controller
public class ShoppingListController {

    private ShoppingRepository shoppingRepository;

    private static final Logger logger = LoggerFactory.getLogger(ShoppingListController.class);

    private final UserRepository userRepository;

    @Autowired
    public ShoppingListController(ShoppingRepository shoppingRepository, UserRepository userRepository) {
        this.shoppingRepository = shoppingRepository;
        this.userRepository = userRepository;
    }

    @GetMapping
    public String indexPage(Model model, Principal principal) {
        model.addAttribute("items", shoppingRepository.findByUserUsername(principal.getName()));
        logger.info("User name {} ", principal.getName());
        model.addAttribute("item", new ShoppingItem());
        return "index";
    }

    //добавляем новые продукты в список покупок
    @PostMapping  //отмечаем метод аннотацией типа пост запроса
    public String addShoppingItem(ShoppingItem item, Principal principal) {
        logger.info("User name {} ", principal.getName());
        User user = userRepository.findByUsername(principal.getName()).get();
        item.setUser(user);
        shoppingRepository.save(item);
        return "redirect:/";
    }

    @DeleteMapping("/{id}")
    public String deleteShopping(@PathVariable("id") Integer id) {
        shoppingRepository.deleteById(id);
        return "redirect:/";
    }
}
