package com.formation.dao;

import com.formation.domain.Cat;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Repository;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public class CatalogDAO implements ICatalogDAO {
    private final List<Cat> items = new ArrayList<>();

    private String name;

    @PostConstruct
    public void initName(){
        name = "My super DAO " + Math.round(Math.random() * 1000000);
    }

    @Override
    public String getCatalogName() {return name;}

    @Override
    public Cat add(Cat newItem){
        Optional<Cat> optional = get(newItem.getName());
        if(optional.isPresent()) {
            return optional.get();
        }
        items.add(newItem);
        return newItem;
    }

    @Override
    public Optional<Cat> get(String name){
        return items.stream()
                .filter(item -> name.equals(item.getName()))
                .findFirst();
    }

    @Override
    public Cat update(Cat newItem) {
        delete(newItem.getName());
        return add(newItem);
    }

    @Override
    public void delete(String name){
        items.removeIf(item -> name.equals(item.getName()));
    }

    @Override
    public Optional<List<Cat>> get() {
        //TODO a debrancher
        /*if(items.isEmpty()) {
            Cat item = new Cat("cheshire", "striped", false);
            add(item);
        }*/
        return Optional.ofNullable(items);
    }
}
