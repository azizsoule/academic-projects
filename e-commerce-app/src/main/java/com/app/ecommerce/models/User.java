package com.app.ecommerce.models;

import com.app.ecommerce.models.supers.Person;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

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
public class User extends Person {

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

}