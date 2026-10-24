package com.app.ecommerce.repositories;

import com.app.ecommerce.models.UserGroup;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserGroupRepository extends JpaRepository<UserGroup, Long> {
}