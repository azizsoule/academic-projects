package com.app.ecommerce.models;

import com.app.ecommerce.models.supers.Person;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Table(name = "admin_user")
@Entity
@NoArgsConstructor
@Getter
@Setter
public class User extends Person implements UserDetails {

    private String username;

    private boolean passwordExpired = false;

    @ManyToOne
    @JoinColumn(name = "id_user_group")
    private UserGroup userGroup;

    @ManyToOne
    @JoinColumn(name = "sex_id_sex")
    private Sex sex;

    public User(String username, String password){
        this.username = username;
        setPassword(password);
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return getGrantedAuthorities(userGroup);
    }

    private List<GrantedAuthority> getGrantedAuthorities(UserGroup group) {
        final List<GrantedAuthority> authorities = new ArrayList<>();
        try{
            for (final String privilege : getPrivileges(group)) {
                authorities.add(new SimpleGrantedAuthority(privilege));
            }
        } catch (Exception e) {
            System.out.println("L'user n'a peut être pas de groupe user");
            authorities.add(new SimpleGrantedAuthority("USER"));
            throw new RuntimeException(e);
        }
        return authorities;
    }

    private List<String> getPrivileges(UserGroup group) {
        List<String> privileges = new ArrayList<>();
        for (Privilege privilege : group.getPrivileges()) {
            privileges.add(privilege.getLabel());
        }
        privileges.add(group.getLabel()+"_GROUP");
        return privileges;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return !isBlocked();
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return !isPasswordExpired();
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}