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
import java.lang.reflect.Array;
import java.util.Arrays;
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
        model.addAttribute("newestProducts", productService.getListNewestProduct(8));
        model.addAttribute("sportProducts", productService.getListProductContainName("sport"));
        model.addAttribute("polos", productService.getListProductContainName("polo"));
        model.addAttribute("jeans", productService.getListProductContainName("Jeans"));
        return "page/home-page";
    }

    @GetMapping({"/{id}"})
    public String productDetail(@PathVariable("id") Optional<Integer> id, @RequestParam(value = "sku", defaultValue = "") String sku, Model model) {
        Product p = productService.findById(id.orElse(null));
        if (sku == null || sku.isEmpty()) {
            sku = p.getDistinctVariants().get(0).getImage().getProductSku();
        }

        String[] a = {"a", "b", "c" };
        String.join(", ", Arrays.asList(a));
        String.join(",", a);
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
            Cart cartExisted = cartService.findCartByProductColorIdAndUserId(cart.getProductVariant().getProductColorId(), cart.getUser().getUserId());
            if (cartExisted != null) {
                cartExisted.setQuantity(cartExisted.getQuantity() + cart.getQuantity());
                cartService.update(cartExisted);
            } else {
                cartService.save(cart);
            }
        }
        return "redirect:/" + id.get();
    }

    @GetMapping("/order-detail")
    public String orderDetail(Model model) {
        Users user = auth.getUserLogin();
        System.out.println("USER ID ~ " + user.getUserId());
        return "page/order-detail-page";
    }

    @ModelAttribute("/productCart")
    public Cart cartModel() {
        return new Cart();
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
    }

    @GetMapping("/cart/delete/{cartId}")
    public String deleteCartItem(@PathVariable("cartId") Optional<Integer> cartId) {
        System.out.println("cartId ~ " + cartId);
        cartId.ifPresent(integer -> cartService.delete(integer));
        return "redirect:/";
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
