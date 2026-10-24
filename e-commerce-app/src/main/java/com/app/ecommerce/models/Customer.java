package com.app.ecommerce.models;

import com.app.ecommerce.models.supers.Person;
import com.fasterxml.jackson.annotation.JsonIgnore;
import groovyjarjarantlr4.v4.runtime.misc.Array2DHashSet;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

@NoArgsConstructor
@Setter
@Getter
@Entity
public class Customer extends Person implements UserDetails {

    @OneToMany(mappedBy = "customer", cascade = CascadeType.ALL)
    @JsonIgnore
    private Set<Address> addresses = new HashSet<>();

    @OneToMany(mappedBy = "customer")
    @JsonIgnore
    private Set<CustomerPaymentMethod> customerPaymentMethods = new HashSet<>();

    @OneToMany(mappedBy = "customer")
    @JsonIgnore
    private Set<CartItem> cartItems = new HashSet<>();

    @OneToMany(mappedBy = "customer")
    @JsonIgnore
    private Set<Comment> comments = new HashSet<>();

    @OneToMany(mappedBy = "customer", orphanRemoval = true)
    @OrderBy("createdAt DESC")
    @JsonIgnore
    private Set<Order> orders = new HashSet<>();

    @OneToMany(mappedBy = "customer", orphanRemoval = true)
    @JsonIgnore
    private Set<WishItem> wishItems = new HashSet<>();

    @ManyToMany(mappedBy = "customers")
    @JsonIgnore
    private Set<Discount> discounts = new HashSet<>();

    @ManyToOne
    @JoinColumn(name = "customer_type_id")
    @JsonIgnore
    private CustomerType customerType;

    @ManyToOne
    @JsonIgnore
    @JoinColumn(name = "sex_id_sex")
    private Sex sex;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        Collection<GrantedAuthority> authorities = new ArrayList<>();
        authorities.add(new SimpleGrantedAuthority("CUSTOMER"));
        return authorities;
    }

    @Override
    public String getUsername() {
        return "";
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return !this.isBlocked();
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

}
