package com.demo.studentapp.feign;

import org.springframework.cloud.openfeign.FeignClient;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.demo.studentapp.response.Address;

@FeignClient(name = "ADDRESS-APP")
public interface AddressProxy {

	@GetMapping("/address/{personId}/{personName}")
	public ResponseEntity<Address> getAddressByPerson(@PathVariable("personId") String personId,
			@PathVariable("personName") String personName);

	@GetMapping("/address/{addressId}")
	public ResponseEntity<Address> getAddressByAddressId(@PathVariable Long addressId);

//	@PostMapping("/address")
//	public ResponseEntity<Address> addAddress(@RequestBody Address address );
//	
//	
//	@PutMapping("/address/{addressId}")
//	public ResponseEntity<String> updateAddress(@PathVariable Long addressId, @RequestBody  Address address);
//	
//	@DeleteMapping("/address/{addressId}")
//	public ResponseEntity<String> deleteAddress(@PathVariable Long addressId);
//	
//	@GetMapping("/address/all-employees")
//	public List<Address> getAllAddress();
}
