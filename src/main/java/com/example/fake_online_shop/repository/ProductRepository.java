package com.example.fake_online_shop.repository;

import com.example.fake_online_shop.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Integer> {

    Product findByName(String name);

    List<Product> findAllByName(String name);

   List<Product> findAllByNameLike(String s);

    List<Product> findAllByType(String type);

    List<Product> findAllByTypeAndNameLike(String type, String name);
}
