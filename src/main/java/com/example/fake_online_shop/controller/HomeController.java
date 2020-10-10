package com.example.fake_online_shop.controller;

import com.example.fake_online_shop.entity.Product;
import com.example.fake_online_shop.entity.Search;
import com.example.fake_online_shop.repository.ProductRepository;
import com.example.fake_online_shop.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import javax.validation.Valid;
import java.util.*;

@Controller
@SessionAttributes({"savedProduct","list","products"})
public class HomeController {

    @Autowired
    private ProductService productService;

    @Autowired
    private ProductRepository productRepository;

    @ModelAttribute("list")
    List<Product> productList(){
        return new ArrayList<>();
    }

    @ModelAttribute("products")
    List<Product> products(){
        return productRepository.findAll();
    }


    @RequestMapping("/home")
    public String home(@ModelAttribute("products") List<Product> productList, Model model){
        model.addAttribute("search",new Search());
        return "home";
    }

    @RequestMapping("/addProduct/{name}")
    public String addProduct(@PathVariable("name")String name,@ModelAttribute("list")List<Product>list,Model model){
        Product product = productRepository.findByName(name);
        model.addAttribute("savedProduct",product);
        list.add(product);
        return "redirect:/home";
    }

    @RequestMapping("/shoppingCart")
    public String shoppingCart(@ModelAttribute("list")List<Product>list){
    return "shoppingCart";
    }

    @RequestMapping("/deleteProduct/{name}")
    public String deleteProduct(@PathVariable("name")String name,@ModelAttribute("list")List<Product> list){
       Optional<Product> product = list.stream().filter(prod -> prod.getName().equals(name)).findFirst();
       product.ifPresent(list::remove);
       return "redirect:/shoppingCart";
    }

    @RequestMapping("/searchProduct")
    public String searchProduct(@Valid @ModelAttribute("search")Search search, BindingResult bindingResult, Model model){
        List<Product> list;
        if(search.getType()!=null){
            if(bindingResult.hasErrors()) {
                list = productRepository.findAllByType(search.getType());
            } else {
                list = productRepository.findAllByTypeAndNameLike(search.getType(),"%"+search.getSearchByName()+"%");
            }
            model.addAttribute("products", productService.sortByPrice(search.getSort(),list));
            return "home";
        }
        if(bindingResult.hasErrors()){
            list = productRepository.findAll();
            model.addAttribute("products",productService.sortByPrice(search.getSort(),list));
            return "home";
        }
        list = productRepository.findAllByNameLike("%"+search.getSearchByName().toLowerCase()+"%");
        model.addAttribute("products",productService.sortByPrice(search.getSort(),list));
        return "home";
    }


}
