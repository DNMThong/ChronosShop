package com.chronos.chronosshop.controller;

import com.chronos.chronosshop.entity.Cart;
import com.chronos.chronosshop.entity.Category;
import com.chronos.chronosshop.entity.Product;
import com.chronos.chronosshop.entity.Users;
import com.chronos.chronosshop.repository.CategoryRepository;
import com.chronos.chronosshop.service.CartService;
import com.chronos.chronosshop.service.CategoryService;
import com.chronos.chronosshop.service.ProductService;
import com.chronos.chronosshop.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequestMapping("test")
public class TestController {

    CategoryService categoryService;

    ProductService productService;

    UserService userService;

    CartService cartService;

    List<Category> categoryList = null;

    @Autowired
    public TestController(CategoryService categoryService,
                          ProductService productService,
                          UserService userService,
                          CartService cartService) {
        this.categoryService = categoryService;
        this.productService = productService;
        this.userService = userService;
        this.cartService = cartService;
    }


    @RequestMapping("")
    public String index(Model model) {
        List<Product> productList = productService.findAll();
//        System.out.println(productList);
        model.addAttribute("newestProducts", productService.getListNewestProduct(8));
        model.addAttribute("sportProducts", productService.getListProductContainName("sport"));
        model.addAttribute("polos", productService.getListProductContainName("polo"));
        model.addAttribute("jeans", productService.getListProductContainName("Jeans"));
//        System.out.println(productList.get(0).getProductVariants() == null || productList.get(0).getProductVariants().size() <= 0 ? "Null" : productList.get(0).getProductVariants().get(0).getImage1());
        return "page/home-page";
    }

    @RequestMapping("productItem")
    public String productItem() {
        return "page/product-item";
    }


    @RequestMapping("cart")
    public String cart() {return "page/cart-page";}

    @RequestMapping({"account", "account/account"})
    public String account() {return "page/account-page";}

    @RequestMapping("account/watched")
    public String accountWatched() {return "page/account-watched-page";}

    @RequestMapping("account/my-order")
    public String accountMyOrdered() {return "page/account-myOrder-page";}


    @RequestMapping("account/forgot-password")
    public String accountForgotPassword() {return "page/account-forgotPassword-page";}

    @RequestMapping("account/location-list")
    public String accountLocationList() {return "page/account-locationList-page";}

    @RequestMapping("account/favorite")
    public String accountFavorite() {return "page/account-favorite-page";}

    @RequestMapping("products/ao-so-mi")
    public String products() {return "page/products-page";}

    @RequestMapping("sign-up")
    public String signUp(Model model) {return "page/sign-up-page";}

    @ModelAttribute("femaleCategories")
    public List<Category> getFemaleCategory() {
        categoryList = categoryService.findCategoriesByName("Nữ");
        return categoryList;
    }

    @ModelAttribute("maleCategories")
    public List<Category> getMaleCategory() {
        categoryList = categoryService.findCategoriesByName("Nam");
        return categoryList;
    }

    @ModelAttribute("kidCategories")
    public List<Category> getKidCategory() {
        categoryList = categoryService.findCategoriesByName("Trẻ em");
        return categoryList;
    }

    @ModelAttribute("user")
    public Users getUser() {
        System.out.println(userService.getUserByEmail("tranhamy@gmail.com").getEmail());
        return userService.getUserByEmail("tranhamy@gmail.com");
    }

    @RequestMapping("addcategory")
    public String addcategory() {
        return "admin/addcategory";
    }
    @RequestMapping("addbrand")
    public String addbrand() {
        return "admin/addbrand";
    }
    @RequestMapping("add-sales")
    public String addsales() {
        return "admin/add-sales";
    }
    @RequestMapping("addproduct")
    public String addproduct() {
        return "admin/addproduct";
    }
    @RequestMapping("addcustomer")
    public String addcustomer() {
        return "admin/addcustomer";
    }
    @RequestMapping("adduser")
    public String adduser() {
        return "admin/adduser";
    }
    @RequestMapping("brandlist")
    public String brandlist() {
        return "admin/brandlist";
    }

    @RequestMapping("activities")
    public String activities() {
        return "admin/activities";
    }
    @RequestMapping("productlist")
    public String productlist() {
        return "admin/productlist";
    }
    @RequestMapping("categorylist")
    public String categorylist() {
        return "admin/categorylist";
    }

}
