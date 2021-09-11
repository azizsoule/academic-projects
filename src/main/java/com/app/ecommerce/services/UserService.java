package com.app.ecommerce.services;

import com.app.ecommerce.models.User;
import com.app.ecommerce.repositories.UserRepository;
import com.app.ecommerce.utils.Constants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("userDetailsService")
public class UserService extends BaseService<User, Long> implements UserDetailsService {

    @Autowired
    private UserRepository repository;

    private final BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder(Constants.ENCRYPTION_STRENGTH);

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
    public User register(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return save(user);
    }


    @Override
    public void deleteById(Long idUser) {
        repository.deleteById(idUser);
    }

    @Override
    public void delete(User user) {
        repository.delete(user);
    }
    public Long count(){
        return repository.count();
    }

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        try {
            final User user = repository.findByUsernameOrEmail(s,s);
            if (user == null) {
                throw new UsernameNotFoundException("E-mail ou mot de passe incorrect !");
            }
            return user;
        } catch (final Exception e) {
            throw new RuntimeException(e);
        }
    }
}
