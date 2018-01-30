package com.formation.dao;

import com.formation.domain.Cat;

import java.util.List;
import java.util.Optional;

public interface ICatalogDAO {
    String getCatalogName();

    Cat add(Cat newItem);

    Optional<Cat> get(String name);

    Cat update(Cat newItem);

    void delete(String name);

    Optional<List<Cat>> get();
}
