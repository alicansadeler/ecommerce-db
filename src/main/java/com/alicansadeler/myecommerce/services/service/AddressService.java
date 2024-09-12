package com.alicansadeler.myecommerce.services.service;

import com.alicansadeler.myecommerce.entity.Address;
import java.util.List;

public interface AddressService {
    Address save(Address address);
    void delete(Long id);
    List<Address> findAll();
    Address findById(Long id);
    Address update(Address address);
}
