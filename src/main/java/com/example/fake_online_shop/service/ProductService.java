package com.example.fake_online_shop.service;

import com.example.fake_online_shop.entity.Product;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

@Component
public class ProductService {

    public List<Product> sortByPrice(String sort,List<Product> list){
        if(sort.equals("asc")){
            list.sort(Comparator.comparingDouble(Product::getPrice));
        } else if (sort.equals("desc")){
            list.sort(Comparator.comparingDouble(Product::getPrice));
            Collections.reverse(list);
        }
        return list;
    }

}
