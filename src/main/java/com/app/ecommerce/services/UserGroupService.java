package com.app.ecommerce.services;

import com.app.ecommerce.dtos.UserGroupDTO;
import com.app.ecommerce.models.UserGroup;
import com.app.ecommerce.repositories.UserGroupRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserGroupService extends BaseService<UserGroupDTO, Long> {

    @Autowired
    UserGroupRepository repository;

    @Override
    public UserGroupDTO findById(Long aLong) {
        UserGroup userGroup = repository.getById(aLong);
        return modelMapper().map(userGroup, UserGroupDTO.class);
    }

    @Override
    public List<UserGroupDTO> findAll() {
        List<UserGroupDTO> userGroupDTOS = new ArrayList<>();
        List<UserGroup> userGroups = repository.findAll();
        userGroups.forEach(userGroup -> {
            userGroupDTOS.add(modelMapper().map(userGroup, UserGroupDTO.class));
        });
        return userGroupDTOS;
    }

    @Override
    public UserGroupDTO save(UserGroupDTO userGroupDTO) {
        UserGroup userGroup = repository.save(modelMapper().map(userGroupDTO, UserGroup.class));
        return modelMapper().map(userGroup, UserGroupDTO.class);
    }

    @Override
    public void deleteById(Long aLong) {
        repository.deleteById(aLong);
    }

    @Override
    public void delete(UserGroupDTO userGroupDTO) {
        repository.delete(modelMapper().map(userGroupDTO, UserGroup.class));
    }

}
