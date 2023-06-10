package com.chronos.chronosshop.controller;

import com.chronos.chronosshop.auth.Auth;
import com.chronos.chronosshop.entity.*;
import com.chronos.chronosshop.entity.dto.CartDto;
import com.chronos.chronosshop.repository.CategoryRepository;
import com.chronos.chronosshop.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.swing.text.html.Option;
import java.util.List;
import java.util.Optional;

@Controller
public class TestController {

    CategoryService categoryService;

    ProductService productService;

    ProductVariantService productVariantService;

    UserService userService;

    CartService cartService;

    List<Category> categoryList = null;

    @Autowired
    Auth auth;

    @Autowired
    public TestController(CategoryService categoryService,
                          ProductService productService,
                          UserService userService,
                          CartService cartService,
                          ProductVariantService productVariantService) {
        this.categoryService = categoryService;
        this.productService = productService;
        this.userService = userService;
        this.cartService = cartService;
        this.productVariantService = productVariantService;
    }


    @RequestMapping("/")
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

    @GetMapping({"/{id}"})
    public String productDetail(@PathVariable("id") Optional<Integer> id, @RequestParam(value = "sku", defaultValue = "") String sku, Model model) {
        Product p = productService.findById(id.orElse(null));
        if (sku == null || sku.isEmpty()) {
            sku = p.getDistinctVariants().get(0).getImage().getProductSku();
        }
        model.addAttribute("product", p);
        model.addAttribute("variants", productVariantService.getVariantBySku(sku));
        return "page/product-item";
    }


    @PostMapping("/addToCart/{id}")
    public String addToCart(@PathVariable("id") Optional<Integer> id, @RequestParam("sku") Optional<String> sku, @RequestParam("colorId") Optional<Integer> colorId, @RequestParam("quantity") Optional<Integer> quantity)
    {
        Users user = auth.getUserLogin();
        System.out.println("login ~ " + user.getEmail());
        if (user != null) {
            CartDto cartDto = new CartDto(user.getUserId(), colorId.get(), quantity.get());
            System.out.println(cartDto.toString());
            Cart cart = new Cart(user, productVariantService.findById(cartDto.getProductColorId()), cartDto.getQuantity());
            cartService.save(cart);
//            productCart.ifPresent(cart -> cartService.save(cart));
        }
        return "redirect:/" + id.get();
    }

    @ModelAttribute("/productCart")
    public Cart cartModel() {
        return new Cart();
    }


    @GetMapping("/login")
    public String login(Model model) {
        return "page/login-page";
    }

    @GetMapping("/cart")
    public String cart() {
        return "page/cart-page";
    }

    @PostMapping("/cart/{id}/update-quantity")
    public String updateQuantity(@PathVariable("id") Optional<Integer> id, @RequestParam("quantity") Optional<String> quantity) {
        System.out.println("quantity ~ " + quantity);
        Cart cart = cartService.findById(id.orElse(null));
        cart.setQuantity(Integer.parseInt(quantity.orElse(cart.getQuantity() + "") + ""));
        cartService.save(cart);
        return "redirect:/";

//        Cart cart = cartService.findById(id.orElse(null));
//        cart.setQuantity(Integer.parseInt(quantity.orElse(cart.getQuantity() + "") + ""));
//        cartService.save(cart);
//        System.out.println("quantity ~ " + quantity);
//
//        ModelAndView modelAndView = new ModelAndView("component/cart/cart-content");
//        modelAndView.addObject("cartItem", cart); // Thêm các đối tượng cần thiết cho fragment vào ModelAndView
//
//        return modelAndView;
    }

    @GetMapping("/cart/delete/{cartId}")
    public String deleteCartItem(@PathVariable("cartId") Optional<Integer> cartId) {
        System.out.println("cartId ~ " + cartId);
        cartId.ifPresent(integer -> cartService.delete(integer));
        return "redirect:/test";
    }

    @RequestMapping({"/account", "/account/account"})
    public String account() {
        return "page/account-page";
    }

    @RequestMapping("/account/watched")
    public String accountWatched() {
        return "page/account-watched-page";
    }

    @RequestMapping("/account/my-order")
    public String accountMyOrdered() {
        return "page/account-myOrder-page";
    }


    @RequestMapping("/account/change-password")
    public String accountForgotPassword() {
        return "page/account-forgotPassword-page";
    }

    @RequestMapping("/account/location-list")
    public String accountLocationList() {
        return "page/account-locationList-page";
    }

    @RequestMapping("/account/favorite")
    public String accountFavorite() {
        return "page/account-favorite-page";
    }

    @RequestMapping("/products/ao-so-mi")
    public String products() {
        return "page/products-page";
    }

    @RequestMapping("/sign-up")
    public String signUp(Model model) {
        return "page/sign-up-page";
    }

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
        return auth.getUserLogin();
    }


}
