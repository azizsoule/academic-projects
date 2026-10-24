package com.app.ecommerce.utils;

public class  Route {

    public static final String API_ENDPOINT = "/api";

    public static final String INDEX = "index";

    public static final String ABOUT_US = "about-us";

    public static final String BANNER_EFFECT = "banner-effect";

    public static final String CART = "cart";

    public static final String SUB_CATEGORY = "sub-category";

    public static final String CHECKOUT = "checkout";

    public static final String CONTACT = "contact";

    public static final String FAQ = "faq";

    public static final String GIFT_VOUCHER = "gift-voucher";

    public static final String LOGIN = "login";

    public static final String MY_ACCOUNT = "my-account";

    public static final String ORDER_HISTORY = "order-history";

    public static final String ORDER_INFORMATION = "order-information";

    public static final String PRODUCT = "product";

    public static final String QUICK_VIEW = "quickview";

    public static final String REGISTER = "register";

    public static final String CHECKOUT_CONFIRM = "checkout-confirm";

    public static final String SITEMAP = "sitemap";

    public static final String TYPOGRAPHY = "typography";

    public static final String WISHLIST = "wishlist";

    public static final String LOGOUT = "logout";

    public static String redirectTo(String route) {
        return "redirect:/"+route;
    }

}
