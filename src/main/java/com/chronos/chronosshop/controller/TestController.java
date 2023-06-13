package com.chronos.chronosshop.controller;

import com.chronos.chronosshop.auth.Auth;
import com.chronos.chronosshop.controller.user.UserAccountController;
import com.chronos.chronosshop.entity.*;
import com.chronos.chronosshop.entity.dto.CartDto;
import com.chronos.chronosshop.entity.dto.OrderDetailDto;
import com.chronos.chronosshop.entity.dto.OrderDto;
import com.chronos.chronosshop.repository.CategoryRepository;
import com.chronos.chronosshop.service.*;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.parameters.P;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.swing.text.html.Option;
import java.lang.reflect.Array;
import java.sql.Date;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

//@ControllerAdvice
@Controller
@Transactional
public class TestController {

//    CategoryService categoryService;
//
//    ProductService productService;
//
//    ProductVariantService productVariantService;
//
//    UserService userService;
//
//    CartService cartService;
//
//    OrderService orderService;
//
//    OrderDetailService orderDetailService;
//
//    PaymentService paymentService;
//
//    List<Category> categoryList = null;
//
//    private final int PAYMENT_ID_LENGTH = 18;
//
//    private final int ORDER_ID_LENGTH = 18;
//
//    @Autowired
//    Auth auth;
//
//
//
//    @Autowired
//    public TestController(CategoryService categoryService,
//                          ProductService productService,
//                          UserService userService,
//                          CartService cartService,
//                          ProductVariantService productVariantService,
//                          OrderService orderService,
//                          PaymentService paymentService,
//                          OrderDetailService orderDetailService) {
//        this.categoryService = categoryService;
//        this.productService = productService;
//        this.userService = userService;
//        this.cartService = cartService;
//        this.productVariantService = productVariantService;
//        this.orderService = orderService;
//        this.paymentService = paymentService;
//        this.orderDetailService = orderDetailService;
//    }


//    @RequestMapping("/")
//    public String index(Model model) {
//        List<Product> productList = productService.findAll();
//        model.addAttribute("newestProducts", productService.getListNewestProduct(8));
//        model.addAttribute("sportProducts", productService.getListProductContainName("sport"));
//        model.addAttribute("polos", productService.getListProductContainName("polo"));
//        model.addAttribute("jeans", productService.getListProductContainName("Jeans"));
//        return "page/home-page";
//    }

//    @GetMapping({"/{id}"})
//    public String productDetail(@PathVariable("id") Optional<Integer> id, @RequestParam(value = "sku", defaultValue = "") String sku, Model model) {
//        Product p = productService.findById(id.orElse(null));
//        if (sku == null || sku.isEmpty()) {
//            sku = p.getDistinctVariants().get(0).getImage().getProductSku();
//        }
//
//        String[] a = {"a", "b", "c" };
//        String.join(", ", Arrays.asList(a));
//        String.join(",", a);
//        model.addAttribute("product", p);
//        model.addAttribute("variants", productVariantService.getVariantBySku(sku));
//        return "page/product-item";
//    }


//    @PostMapping("/addToCart/{id}")
//    public String addToCart(@PathVariable("id") Optional<Integer> id, @RequestParam("sku") Optional<String> sku, @RequestParam("colorId") Optional<Integer> colorId, @RequestParam("quantity") Optional<Integer> quantity)
//    {
//        Users user = auth.getUserLogin();
//        System.out.println("login ~ " + user.getEmail());
//        if (user != null) {
//            CartDto cartDto = new CartDto(user.getUserId(), colorId.get(), quantity.get());
//            System.out.println(cartDto.toString());
//            Cart cart = new Cart(user, productVariantService.findById(cartDto.getProductColorId()), cartDto.getQuantity());
//            Cart cartExisted = cartService.findCartByProductColorIdAndUserId(cart.getProductVariant().getProductColorId(), cart.getUser().getUserId());
//            if (cartExisted != null) {
//                cartExisted.setQuantity(cartExisted.getQuantity() + cart.getQuantity());
//                cartService.update(cartExisted);
//            } else {
//                cartService.save(cart);
//            }
//        }
//        return "redirect:/" + id.get();
//    }

//    @GetMapping("/order-detail")
//    public String orderDetail(Model model) {
//        Users user = auth.getUserLogin();
//        System.out.println("USER ID ~ " + user.getUserId());
//        return "page/order-detail-page";
//    }
//
//    @PostMapping("/order")
//    @Transactional
//    public String order(@ModelAttribute("orderDto") OrderDto orderDto) {
//        Users user = auth.getUserLogin();
//        System.out.println("RECIPIENT ~ " + orderDto.toString());
//
//        Long totalProdPrice = 0L;
//        for (int i = 0; i < user.getCarts().size(); i ++) {
//            totalProdPrice += (long) user.getCarts().get(i).getQuantity() * user.getCarts().get(i).getProductVariant().getProduct().getPriceNew();
//        }
//
//        LocalDateTime now = LocalDateTime.now();
//        Orders orders = null;
//        try {
//            orders = orderService.createOrderAndPayment(
//                    user.getUserId(),
//                    orderDto.getShipId(),
//                    null,
//                    "Chờ xác nhận",
//                    now,
//                    now,
//                    orderDto.getPaymentMethod(),
//                    totalProdPrice,
//                    totalProdPrice >= 499500 ? 0L : 20000L,
//                    totalProdPrice,
//                    "VND"
//            );
//            if (orders != null) {
//                Orders finalOrders = orders;
//                user.getCarts().forEach(item -> {
//                    orderDetailService.save(new OrderDetail(finalOrders, item.getProductVariant(), item.getProductVariant().getProduct().getPriceNew(), item.getQuantity()));
//                });
//                cartService.deleteCartsByUser_UserId(user.getUserId());
//            }
//        } catch (Exception e) {
//            System.out.println(e.getMessage());
//        }
//
//        System.out.println("ORDER ID ~ " + orders.getOrderId() + "\t" + orders.getUser().getUserId());
//
//
//        return "redirect:/";
//    }
//
//    @ModelAttribute("orderDto")
//    public OrderDto orderDto() {
//        return new OrderDto();
//    }

//    @ModelAttribute("productCart")
//    public Cart cartModel() {
//        return new Cart();
//    }
//
//    @GetMapping("/cart")
//    public String cart() {
//        return "page/cart-page";
//    }
//
//    @PostMapping("/cart/{id}/update-quantity")
//    public String updateQuantity(@PathVariable("id") Optional<Integer> id, @RequestParam("quantity") Optional<String> quantity) {
//        System.out.println("quantity ~ " + quantity);
//        Cart cart = cartService.findById(id.orElse(null));
//        cart.setQuantity(Integer.parseInt(quantity.orElse(cart.getQuantity() + "") + ""));
//        cartService.save(cart);
//        return "redirect:/";
//    }
//
//    @GetMapping("/cart/delete/{cartId}")
//    public String deleteCartItem(@PathVariable("cartId") Optional<Integer> cartId) {
//        System.out.println("cartId ~ " + cartId);
//        cartId.ifPresent(integer -> cartService.delete(integer));
//        return "redirect:/";
//    }

//    @RequestMapping("/products/ao-so-mi")
//    public String products() {
//        return "page/products-page";
//    }

//    @RequestMapping("/sign-up")
//    public String signUp(Model model) {
//        return "page/sign-up-page";
//    }

//    @ModelAttribute("femaleCategories")
//    public List<Category> getFemaleCategory() {
//        categoryList = categoryService.findCategoriesByName("Nữ");
//        return categoryList;
//    }
//
//    @ModelAttribute("maleCategories")
//    public List<Category> getMaleCategory() {
//        categoryList = categoryService.findCategoriesByName("Nam");
//        return categoryList;
//    }
//
//    @ModelAttribute("kidCategories")
//    public List<Category> getKidCategory() {
//        categoryList = categoryService.findCategoriesByName("Trẻ em");
//        return categoryList;
//    }
//
//    @ModelAttribute("user")
//    public Users getUser() {
//        return auth.getUserLogin();
//    }

//    public static String generateNewID(int index) {
//        UUID newID = UUID.randomUUID();
//        return newID.toString().toUpperCase().substring(0, index);
//    }

}
