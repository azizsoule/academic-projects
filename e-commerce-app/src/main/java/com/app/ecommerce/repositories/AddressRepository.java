package com.app.ecommerce.repositories;

import com.app.ecommerce.models.Address;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AddressRepository extends JpaRepository<Address, Long> {

}