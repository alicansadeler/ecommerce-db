package com.alicansadeler.myecommerce.services.service;

import com.alicansadeler.myecommerce.dto.response.UserAddressDTO;
import com.alicansadeler.myecommerce.entity.Address;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;

import java.util.List;

public interface AddressService {
    Address save(@Valid Address address);
    void delete(@NotNull Long id);
    List<Address> findAll();
    Address findById(@NotNull Long id);
    Address update(@NotNull Long id, @Valid Address updatedAddress);
    List<UserAddressDTO> findAddressByUser(@NotNull Long id);
}
