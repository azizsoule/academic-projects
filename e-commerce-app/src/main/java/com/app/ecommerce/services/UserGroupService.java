package com.app.ecommerce.services;

import com.app.ecommerce.models.UserGroup;
import com.app.ecommerce.repositories.UserGroupRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserGroupService extends BaseService<UserGroup, Long> {

    @Autowired
    UserGroupRepository repository;

    @Override
    public UserGroup findById(Long aLong) {
        return repository.getById(aLong);
    }

    @Override
    public List<UserGroup> findAll() {
        return repository.findAll();
    }

    @Override
    public UserGroup save(UserGroup userGroup) {
        return repository.save(userGroup);
    }

    @Override
    public void deleteById(Long aLong) {
        repository.deleteById(aLong);
    }

    @Override
    public void delete(UserGroup userGroup) {
        repository.delete(userGroup);
    }

}
