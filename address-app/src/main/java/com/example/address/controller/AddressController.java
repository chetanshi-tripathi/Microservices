package com.example.address.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.address.entity.Address;
import com.example.address.service.AddressService;

@RestController
@RequestMapping("/address")
public class AddressController {

	@Autowired
	AddressService addserv;

	@GetMapping("/{personId}/{personName}")
	public ResponseEntity<Address> getAddressByPerson(@PathVariable("personId") String personId,
			@PathVariable("personName") String personName) {
		return addserv.getAddressByPerson(personId, personName);
	}

	@GetMapping("/{addressId}")
	public ResponseEntity<Address> getAddressByAddressId(@PathVariable Long addressId) {
		return addserv.getAddressByAddressId(addressId);
	}

	@PostMapping("/add-address")
	public ResponseEntity<Address> addAddress(@RequestBody Address address) {
		return addserv.addAddress(address);
	}

	@PutMapping("/{addressId}")
	public ResponseEntity<String> updateAddress(@PathVariable Long addressId, @RequestBody Address address) {
		return addserv.updateAddress(addressId, address);
	}

	@DeleteMapping("/{addressId}")
	public ResponseEntity<String> deleteAddress(@PathVariable Long addressId) {
		return addserv.deleteAddress(addressId);
	}

	@GetMapping("/all-address")
	public List<Address> getAllAddress() {
		return addserv.getAllAddress();
	}
}
