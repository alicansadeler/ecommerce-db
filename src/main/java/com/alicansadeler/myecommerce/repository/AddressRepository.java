package com.alicansadeler.myecommerce.repository;

import com.alicansadeler.myecommerce.dto.response.UserAddressDTO;
import com.alicansadeler.myecommerce.entity.Address;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;


public interface AddressRepository extends JpaRepository<Address, Long> {
    @Query("SELECT new com.alicansadeler.myecommerce.dto.response.UserAddressDTO(ad.title, ad.addressText, us.firstName, us.lastName) " +
            "FROM Address ad JOIN ad.user us WHERE us.id = :id")
    List<UserAddressDTO> findAddressByUser(@Param("id") Long id);


}
