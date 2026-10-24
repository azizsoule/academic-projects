package com.app.ecommerce.services;

import com.app.ecommerce.dtos.InventoryDTO;
import com.app.ecommerce.models.Inventory;
import com.app.ecommerce.repositories.InventoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class InventoryService extends BaseService<InventoryDTO, Long> {

    @Autowired
    InventoryRepository repository;

    @Override
    public InventoryDTO findById(Long aLong) {
        Inventory inventory = repository.getById(aLong);
        return modelMapper().map(inventory, InventoryDTO.class);
    }

    @Override
    public List<InventoryDTO> findAll() {
        List<InventoryDTO> inventoryDTOS = new ArrayList<>();
        List<Inventory> inventories = repository.findAll();
        inventories.forEach(inventory -> {
            inventoryDTOS.add(modelMapper().map(inventory, InventoryDTO.class));
        });
        return inventoryDTOS;
    }

    @Override
    public InventoryDTO save(InventoryDTO inventoryDTO) {
        Inventory inventory = modelMapper().map(inventoryDTO, Inventory.class);
        inventory = repository.save(inventory);
        return modelMapper().map(inventory, InventoryDTO.class);
    }

    @Override
    public void deleteById(Long aLong) {
        repository.deleteById(aLong);
    }

    @Override
    public void delete(InventoryDTO inventoryDTO) {
        repository.delete(modelMapper().map(inventoryDTO, Inventory.class));
    }

}
