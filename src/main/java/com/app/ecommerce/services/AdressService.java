package com.app.ecommerce.services;

import com.app.ecommerce.dtos.AddressDTO;
import com.app.ecommerce.models.Address;
import com.app.ecommerce.repositories.AddressRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class AdressService extends BaseService<AddressDTO, Long> {

    @Autowired
    AddressRepository repository;

    @Override
    public AddressDTO findById(Long aLong) {
        Address address = repository.getById(aLong);
        return modelMapper().map(address, AddressDTO.class);
    }

    @Override
    public List<AddressDTO> findAll() {
        List<AddressDTO> addressDTOS = new ArrayList<>();
        List<Address> addresses = repository.findAll();
        addresses.forEach(address -> {
            addressDTOS.add(modelMapper().map(address, AddressDTO.class));
        });
        return addressDTOS;
    }

    @Override
    public AddressDTO save(AddressDTO addressDTO) {
        Address address = modelMapper().map(addressDTO, Address.class);
        address = repository.save(address);
        addressDTO = modelMapper().map(address, AddressDTO.class);
        return addressDTO;
    }

    @Override
    public void deleteById(Long aLong) {
        repository.deleteById(aLong);
    }

    @Override
    public void delete(AddressDTO addressDTO) {
        Address address = modelMapper().map(addressDTO, Address.class);
        repository.delete(address);
    }

}
