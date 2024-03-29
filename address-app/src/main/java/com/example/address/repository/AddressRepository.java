package com.example.address.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.address.entity.Address;

@Repository
public interface AddressRepository extends JpaRepository<Address, Long> {

	Address findByPersonIdAndPersonName(String personId, String personName);

}
