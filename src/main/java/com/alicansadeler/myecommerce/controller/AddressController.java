package com.alicansadeler.myecommerce.controller;

import com.alicansadeler.myecommerce.dto.response.UserAddressDTO;
import com.alicansadeler.myecommerce.entity.Address;
import com.alicansadeler.myecommerce.entity.User;
import com.alicansadeler.myecommerce.services.service.AddressService;
import com.alicansadeler.myecommerce.services.service.UserService;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/profile")
@Validated
public class AddressController {

    private final AddressService addressService;
    private final UserService userService;

    @Autowired
    public AddressController(AddressService addressService, UserService userService) {
        this.addressService = addressService;
        this.userService = userService;
    }
// id'li adresi getir
    @GetMapping("/address/{id}")
    public Address getAddresses(@PathVariable Long id) {
        return addressService.findById(id);
    }
// seçili user'ın kayıtlı adreslerini getir
    @GetMapping("/address/user/{userId}")
    public List<UserAddressDTO> getAddressesByUser(@PathVariable Long userId) {
        return addressService.findAddressByUser(userId);
    }

// user'a adres ekle
    @PostMapping("/address/{id}")
    @ResponseStatus(HttpStatus.CREATED)
    @Transactional
    public Address saveAddress(@PathVariable Long id, @Valid @RequestBody Address address) {
        User user = userService.findById(id);
        address.setUser(user);
        return addressService.save(address);
    }

    @DeleteMapping("/address/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void deleteAddress(@PathVariable Long id) {
        addressService.delete(id);
    }

    @PutMapping("/address/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Address updateAddress(@PathVariable Long id, @RequestBody Address address) {
        return addressService.update(id, address);
    }

}
