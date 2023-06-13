package com.chronos.chronosshop.controller.user;

import com.chronos.chronosshop.auth.Auth;
import com.chronos.chronosshop.entity.Cart;
import com.chronos.chronosshop.entity.ProductVariant;
import com.chronos.chronosshop.entity.Users;
import com.chronos.chronosshop.entity.dto.CartDto;
import com.chronos.chronosshop.service.CartService;
import com.chronos.chronosshop.service.ProductVariantService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.net.http.HttpRequest;
import java.util.Optional;

@Controller
public class CartController {

    @Autowired
    CartService cartService;

    @Autowired
    ProductVariantService productVariantService;

    @Autowired
    Auth auth;

    @Autowired
    HttpSession session;


    @ModelAttribute("productCart")
    public Cart cartModel() {
        return new Cart();
    }

    @GetMapping("/cart")
    public String cart(HttpServletRequest req) {
        session.setAttribute("uri", req.getRequestURI());
        return "page/cart-page";
    }

    @PostMapping("/cart/{id}/update-quantity")
    public String updateQuantity(@PathVariable("id") Optional<Integer> id, @RequestParam("quantity") Optional<String> quantity, HttpServletRequest req) {
        System.out.println("quantity ~ " + quantity);
        Cart cart = cartService.findById(id.orElse(null));
        cart.setQuantity(Integer.parseInt(quantity.orElse(cart.getQuantity() + "") + ""));
        cartService.save(cart);
        return "redirect:/" + session.getAttribute("uri");
    }

    @GetMapping("/cart/delete/{cartId}")
    public String deleteCartItem(@PathVariable("cartId") Optional<Integer> cartId, HttpServletRequest req) {
        session.setAttribute("uri", req.getRequestURI());
        System.out.println("cartId ~ " + cartId);
        cartId.ifPresent(integer -> cartService.delete(integer));
        return "redirect:" + req.getRequestURI();
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
}
