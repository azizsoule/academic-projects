package com.app.ecommerce.controllers;

import com.app.ecommerce.exceptions.CustomerEmailAlreadyExistException;
import com.app.ecommerce.models.*;
import com.app.ecommerce.services.*;
import com.app.ecommerce.utils.Route;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;
import java.util.Random;

@Controller
@RequestMapping("/")
public class CustomerController extends BaseController {

    @Autowired
    SexService sexService;

    @Autowired
    CustomerService customerService;

    @Autowired
    CityService cityService;

    @Autowired
    OrderService orderService;

    @Autowired
    PaymentMethodService paymentMethodService;

    @Autowired
    CartItemService cartItemService;

    @Autowired
    WishItemService wishItemService;

    @Autowired
    PaymentDetailService paymentDetailService;

    @Autowired
    OrderStateService orderStateService;

    @Autowired
    AddressService addressService;

    @GetMapping(Route.REGISTER)
    String register(Model model) {
        model.addAttribute("customer", new Customer());
        model.addAttribute("address", new Address());
        model.addAttribute("sexes", sexService.findAll());
        model.addAttribute("cities", cityService.findAll());
        return Route.REGISTER;
    }

    @PostMapping(Route.REGISTER)
    String register(Customer customer, Address address, RedirectAttributes redirectAttributes) {
        try {
            customerService.register(customer, address);
            redirectAttributes.addFlashAttribute("success", "Succès - Inscription réussie. Vous pouvez vous connecter à votre compte en utilisant votre email et votre mot de passe !");
        } catch (CustomerEmailAlreadyExistException exception) {
            redirectAttributes.addFlashAttribute("failure", "Erreur - L'adresse e-mail que vous avez entré est déja utilisée !");
        } catch (Exception exception) {
            redirectAttributes.addFlashAttribute("failure", "Erreur - L'inscription a échoué car une erreur est survenue. Veuillez réessayer svp !");
        }
        return Route.redirectTo(Route.REGISTER);
    }

    @GetMapping(Route.LOGIN)
    String login() {
        return Route.LOGIN;
    }

    @GetMapping(Route.CART)
    String cart(@AuthenticationPrincipal Customer customer, Model model) {
        customer = customerService.findById(customer.getId());

        var total = new Object() {
            public float subTotal = 0;
            public float discountTotal = 0;
        };

        cartItemService.findAllByCustomer(customer).forEach(cartItem -> {
            Article article = cartItem.getArticle();
            total.subTotal = total.subTotal + (cartItem.getQuantity() * article.getPrice());
            article.getDiscounts().forEach(discount -> {
                float discountAmount = 0;
                if (discount.isPercentage()) {
                    discountAmount = (article.getPrice() * discount.getDiscountPercent()) / 100;
                } else {
                    discountAmount = discount.getDiscountAmount();
                }
                total.discountTotal = total.discountTotal + discountAmount;
            });
        });

        model.addAttribute("total", total);
        model.addAttribute("cartItems", customer.getCartItems());
        model.addAttribute("qto",new CartItem());
        return Route.CART;
    }

    @GetMapping(Route.CART+"/remove/{cartItemId}")
    public String removeCartItemFromCart(@PathVariable String cartItemId) {
        cartItemService.deleteById(Long.parseLong(cartItemId));
        return Route.redirectTo(Route.CART);
    }

    @PostMapping(Route.CART+"/update/{id}")
    public String updateCartItemFromCart(@PathVariable Long id, CartItem ci) {
        CartItem cartItem = cartItemService.findById(id);
        cartItem.setQuantity(ci.getQuantity());
        cartItemService.update(cartItem);
        return Route.redirectTo(Route.CART);
    }

    @GetMapping(Route.CHECKOUT)
    public String checkout(@AuthenticationPrincipal Customer customer, Model model) {
        customer = customerService.findById(customer.getId());

        var total = new Object() {
            public float subTotal = 0;
            public float discountTotal = 0;
        };

        customer.getCartItems().forEach(cartItem -> {
            Article article = cartItem.getArticle();
            total.subTotal = total.subTotal + (cartItem.getQuantity() * article.getPrice());
            article.getDiscounts().forEach(discount -> {
                float discountAmount = 0;
                if (discount.isPercentage()) {
                    discountAmount = (article.getPrice() * discount.getDiscountPercent()) / 100;
                } else {
                    discountAmount = discount.getDiscountAmount();
                }
                total.discountTotal = total.discountTotal + discountAmount;
            });
        });

        Order order = new Order();

        customer.getAddresses().forEach(address -> {
            if (address.getDef()) {
                order.setAddress(new Address());
                order.getAddress().setIdAddress(address.getIdAddress());
            }
        });

        model.addAttribute("total", total);
        model.addAttribute("customer", customer);
        model.addAttribute("paymentMethods", paymentMethodService.findAll());
        model.addAttribute("order", order);
        model.addAttribute("pd", new PaymentDetail());
        return Route.CHECKOUT;
    }

    @GetMapping(Route.CHECKOUT_CONFIRM+"/{id}")
    public String checkoutConfirm(@PathVariable Long id, Model model) {
        model.addAttribute("code", id);
        return Route.CHECKOUT_CONFIRM;
    }

    @PostMapping(Route.CHECKOUT)
    public String addOrder(@AuthenticationPrincipal Customer customer, Order order, PaymentDetail pd, Model model) {
        customer = customerService.findById(customer.getId());

        var total = new Object() {
            public float subTotal = 0;
            public float discountTotal = 0;
        };

        customer.getCartItems().forEach(cartItem -> {
            Article article = cartItem.getArticle();
            total.subTotal = total.subTotal + (cartItem.getQuantity() * article.getPrice());
            article.getDiscounts().forEach(discount -> {
                float discountAmount = 0;
                if (discount.isPercentage()) {
                    discountAmount = (article.getPrice() * discount.getDiscountPercent()) / 100;
                } else {
                    discountAmount = discount.getDiscountAmount();
                }
                total.discountTotal = total.discountTotal + discountAmount;
            });
        });

        order.setTotal(total.subTotal - total.discountTotal);
        order.setCustomer(customer);
        customer.getCartItems().forEach(cartItem -> {
            OrderItem orderItem = new OrderItem();
            orderItem.setOrder(order);
            orderItem.setQuantity(cartItem.getQuantity());
            orderItem.setArticle(cartItem.getArticle());
            order.getOrderItems().add(orderItem);
        });
        OrderState orderState = new OrderState();
        orderState.setCodeOrderState("PENDING");
        order.setOrderState(orderState);
        pd.setAmount(order.getTotal());
        //pd.setReference("REF-XAZ");
        pd.setStatus("SUCCESS");
        pd = paymentDetailService.save(pd);
        order.setPaymentDetail(pd);
        Order o = orderService.save(order);
        customer.getCartItems().forEach(cartItem -> {
            cartItemService.delete(cartItem);
        });
        return Route.redirectTo(Route.CHECKOUT_CONFIRM+"/"+o.getIdOrder());
    }

    @PostMapping(Route.CHECKOUT+"/update")
    public String updateCartItemFromCheckout(CartItem cartItem) {
        CartItem cartItem1 = cartItemService.findById(cartItem.getIdItem());
        cartItem1.setQuantity(cartItem.getQuantity());
        cartItemService.update(cartItem1);
        return Route.redirectTo(Route.CHECKOUT);
    }

    @GetMapping(Route.CHECKOUT+"/remove/{cartItemId}")
    public String removeCartItemFromCheckout(@PathVariable String cartItemId) {
        cartItemService.deleteById(Long.parseLong(cartItemId));
        return Route.redirectTo(Route.CHECKOUT);
    }

    @GetMapping(Route.WISHLIST)
    public String wishlist(@AuthenticationPrincipal Customer customer, Model model) {
        customer = customerService.findById(customer.getId());
        model.addAttribute( "wishlist",wishItemService.findAllByCustomer(customer));
        return Route.WISHLIST;
    }

    @GetMapping(Route.WISHLIST+"/remove/{id}")
    public String removeWishItem(@PathVariable Long id) {
        wishItemService.deleteById(id);
        return Route.redirectTo(Route.WISHLIST);
    }

    @GetMapping(Route.WISHLIST+"/add/to/cart/{id}")
    public String addWishItemToCart(@PathVariable Long id) {
        WishItem wishItem = wishItemService.findById(id);
        CartItem cartItem = new CartItem();
        cartItem.setQuantity(1);
        cartItem.setCustomer(wishItem.getCustomer());
        cartItem.setArticle(wishItem.getArticle());
        wishItemService.delete(wishItem);
        cartItemService.addToCart(cartItem);
        return Route.redirectTo(Route.CART);
    }

    @GetMapping(Route.ORDER_HISTORY)
    String orderHistory(@AuthenticationPrincipal Customer customer, Model model) {
        customer = customerService.findById(customer.getId());
        model.addAttribute("orders", customer.getOrders());
        return Route.ORDER_HISTORY;
    }

    @GetMapping(Route.ORDER_INFORMATION+"/{id}")
    String orderInformation(@PathVariable String id, Model model) {
        Order order = orderService.findById(Long.parseLong(id));
        model.addAttribute("order", order);
        return Route.ORDER_INFORMATION;
    }

    @GetMapping(Route.ORDER_HISTORY+"/cancel/{id}")
    String cancelOrder(@PathVariable Long id) {
        Order order = orderService.findById(id);
        OrderState canceledOrderState = orderStateService.findById("CANCELED");
        order.setOrderState(canceledOrderState);
        orderService.update(order);
        return Route.redirectTo(Route.ORDER_HISTORY);
    }

    @GetMapping(Route.MY_ACCOUNT)
    public String myAccount(@AuthenticationPrincipal Customer customer, Model model) {
        customer = customerService.findById(customer.getId());
        Address address = new Address();
        address.setFirstName(customer.getFirstName());
        address.setLastName(customer.getLastName());
        List<City> cities = cityService.findAll();
        model.addAttribute("address", address);
        model.addAttribute("cities", cities);
        model.addAttribute("customer", customer);
        return Route.MY_ACCOUNT;
    }

    @PostMapping(Route.MY_ACCOUNT+"/customer/update")
    public String updateCustomer(@AuthenticationPrincipal Customer currentCustomer, Customer customer) {
        currentCustomer = customerService.findById(currentCustomer.getId());
        currentCustomer.setFirstName(customer.getFirstName());
        currentCustomer.setLastName(customer.getLastName());
        currentCustomer.setPhoneNumber(customer.getPhoneNumber());
        customerService.update(currentCustomer);
        return Route.redirectTo(Route.MY_ACCOUNT);
    }

    @PostMapping(Route.MY_ACCOUNT+"/address/add")
    public String addNewAddress(@AuthenticationPrincipal Customer customer, Address address) {
        address.setCustomer(customer);
        addressService.save(address);
        return Route.redirectTo(Route.MY_ACCOUNT);
    }

}
