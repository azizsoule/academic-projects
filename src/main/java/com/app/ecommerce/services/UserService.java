package com.app.ecommerce.services;

import com.app.ecommerce.dtos.UserDTO;
import com.app.ecommerce.models.User;
import com.app.ecommerce.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserService extends BaseService<UserDTO, Long> {

    @Autowired
    private UserRepository repository;

    @Override
    public UserDTO findById(Long idUser) {
        User user = repository.getById(idUser);
        return modelMapper().map(user, UserDTO.class);
    }

    @Override
    public List<UserDTO> findAll() {
        List<User> users = repository.findAll();
        List<UserDTO> userDTOS = new ArrayList<>();
        users.forEach(user -> {
            UserDTO userDTO = modelMapper().map(user, UserDTO.class);
            userDTOS.add(userDTO);
        });
        return userDTOS;
    }

    @Override
    public UserDTO save(UserDTO userDTO) {
        User user = modelMapper().map(userDTO, User.class);
        user = repository.save(user);
        userDTO = modelMapper().map(user, UserDTO.class);
        return userDTO;
    }

    @Override
    public void deleteById(Long idUser) {
        repository.deleteById(idUser);
    }

    @Override
    public void delete(UserDTO userDTO) {
        User user = modelMapper().map(userDTO, User.class);
        repository.delete(user);
    }

}
