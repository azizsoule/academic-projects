package com.app.ecommerce.repositories;

import com.app.ecommerce.models.Privilege;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PrivilegeRepository extends JpaRepository<Privilege, Long> {
}
