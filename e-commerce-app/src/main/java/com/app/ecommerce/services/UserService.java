package com.app.ecommerce.services;

import com.app.ecommerce.models.User;
import com.app.ecommerce.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService extends BaseService<User, Long> {

    @Autowired
    private UserRepository repository;

    @Override
    public User findById(Long idUser) {
        return repository.getById(idUser);
    }

    @Override
    public List<User> findAll() {
        return repository.findAll();
    }

    @Override
    public User save(User user) {
        return repository.save(user);
    }

    @Override
    public void deleteById(Long idUser) {
        repository.deleteById(idUser);
    }

    @Override
    public void delete(User user) {
        repository.delete(user);
    }

}
