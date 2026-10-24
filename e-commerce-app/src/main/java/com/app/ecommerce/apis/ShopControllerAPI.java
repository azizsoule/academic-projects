package com.app.ecommerce.apis;

import com.app.ecommerce.models.CartItem;
import com.app.ecommerce.models.WishItem;
import com.app.ecommerce.services.CartItemService;
import com.app.ecommerce.services.CustomerService;
import com.app.ecommerce.services.WishItemService;
import com.app.ecommerce.utils.Route;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(Route.API_ENDPOINT)
public class ShopControllerAPI {

    @Autowired
    CartItemService cartItemService;

    @Autowired
    WishItemService wishItemService;

    @Autowired
    CustomerService customerService;

    @PostMapping("/cart/add")
    public ResponseEntity<?> addToCart(@RequestBody CartItem cartItem) {
        cartItemService.addToCart(cartItem);
        List<CartItem> items = new ArrayList<>(customerService.findById(cartItem.getCustomer().getId()).getCartItems());
        return new ResponseEntity<List<CartItem>>(items, HttpStatus.OK);
    }

    @PostMapping("/cart/update/{id}")
    public ResponseEntity<?> updateCart(@PathVariable Long id, @RequestBody CartItem ci) {
        CartItem cartItemDB = cartItemService.findById(id);
        cartItemDB.setQuantity(ci.getQuantity());
        cartItemDB = cartItemService.update(cartItemDB);
        List<CartItem> items = new ArrayList<>(cartItemDB.getCustomer().getCartItems());
        return new ResponseEntity<List<CartItem>>(items, HttpStatus.OK);
    }

    @GetMapping("/cart/remove/{id}")
    public ResponseEntity<?> removeFromCart(@PathVariable Long id) {
        CartItem cartItem = cartItemService.findById(id);
        cartItemService.deleteById(id);
        return new ResponseEntity<List<CartItem>>(cartItemService.findAllByCustomer(cartItem.getCustomer()), HttpStatus.OK);
    }

    @PostMapping("/wishlist/add")
    public ResponseEntity<?> addToWishList(@RequestBody WishItem wishItem) {
        wishItem = wishItemService.add(wishItem);
        return new ResponseEntity<List<WishItem>>(wishItemService.findAllByCustomer(wishItem.getCustomer()), HttpStatus.OK);
    }

    @GetMapping("/wishlist/remove/{id}")
    public ResponseEntity<?> removeFromWishList(@PathVariable Long id) {
        WishItem wishItem = wishItemService.findById(id);
        wishItemService.deleteById(id);
        return new ResponseEntity<List<WishItem>>(wishItemService.findAllByCustomer(wishItem.getCustomer()), HttpStatus.OK);
    }

}
