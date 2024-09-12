package com.alicansadeler.myecommerce.services.Impl;

import com.alicansadeler.myecommerce.entity.Address;
import com.alicansadeler.myecommerce.repository.AddressRepository;
import com.alicansadeler.myecommerce.services.service.AddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class AddressServiceImpl implements AddressService {

    private final AddressRepository addressRepository;

    @Autowired
    public AddressServiceImpl(AddressRepository addressRepository) {
        this.addressRepository = addressRepository;
    }


    @Override
    public Address save(Address address) {
        return addressRepository.save(address);
    }

    @Override
    public void delete(Long id) {

    }

    @Override
    public List<Address> findAll() {
        return List.of();
    }

    @Override
    public Address findById(Long id) {
        return null;
    }

    @Override
    public Address update(Address address) {
        return null;
    }
}
