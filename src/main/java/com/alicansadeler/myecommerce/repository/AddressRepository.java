package com.alicansadeler.myecommerce.repository;

import com.alicansadeler.myecommerce.entity.Address;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AddressRepository extends JpaRepository<Address, Long> {

}
