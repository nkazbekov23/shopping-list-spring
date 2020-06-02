package com.shoplist.shoppinglist.enteties;

import javax.persistence.*;

@Entity //определяем что этот класс является сущностью
@Table(name = "shopping")
public class ShoppingItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(nullable = false)// чтобы таблица не было пустым
    private String name;

    public ShoppingItem() {}//конструткор по умолочаниию

    @ManyToOne//множество продуктов будет будет принадлежать одному пользователю
    private User user;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
