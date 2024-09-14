package com.alicansadeler.myecommerce.services.Impl;

import com.alicansadeler.myecommerce.dto.response.UserAddressDTO;
import com.alicansadeler.myecommerce.entity.Address;
import com.alicansadeler.myecommerce.entity.User;
import com.alicansadeler.myecommerce.exceptions.ApiException;
import com.alicansadeler.myecommerce.repository.AddressRepository;
import com.alicansadeler.myecommerce.services.service.AddressService;
import com.alicansadeler.myecommerce.services.service.UserService;
import com.alicansadeler.myecommerce.validations.Validate;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import java.util.List;



@Service
@Validated
public class AddressServiceImpl implements AddressService {

    private final AddressRepository addressRepository;
    private final UserService userService;
    @Autowired
    public AddressServiceImpl(AddressRepository addressRepository, UserService userService) {
        this.addressRepository = addressRepository;
        this.userService = userService;
    }

    @Override
    @Transactional
    public Address save(Address address) {
        return addressRepository.save(address);
    }

    @Override
    @Transactional
    public void delete(Long id) {
        Validate.validateId(id);
        Address address = findById(id);
        addressRepository.delete(address);
    }

    @Override
    public List<Address> findAll() {
        List<Address> addresses = addressRepository.findAll();
        if (addresses.isEmpty()) {
            throw new ApiException("No address records found to display", HttpStatus.NOT_FOUND);
        }
        return addresses;
    }

    @Override
    public Address findById(Long id) {
        Validate.validateId(id);
        return addressRepository.findById(id)
                .orElseThrow(() -> new ApiException("Address with ID not found. ID: " + id, HttpStatus.NOT_FOUND));
    }

    @Override
    @Transactional
    public Address update(Long id, Address updatedAddress) {
        Address oldAddress = findById(id);
        if (oldAddress == null) {
            throw new ApiException("Address not found. ID: " + id, HttpStatus.NOT_FOUND);
        }
        Validate.validateAddressUpdate(oldAddress, updatedAddress);
        return addressRepository.save(oldAddress);
    }

    @Override
    public List<UserAddressDTO> findAddressByUser(Long id) {
        Validate.validateId(id);

        User user = userService.findById(id);
        if (user == null) {
            throw new ApiException("User not found. ID: " + id, HttpStatus.NOT_FOUND);
        }

        List<UserAddressDTO> userAddressDTOS = addressRepository.findAddressByUser(id);
        if (userAddressDTOS == null || userAddressDTOS.isEmpty()) {
            throw new ApiException("Registered address not found", HttpStatus.NOT_FOUND);
        }

        return userAddressDTOS;
    }

}
