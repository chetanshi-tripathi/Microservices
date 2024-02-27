package com.example.address.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.example.address.entity.Address;
import com.example.address.repository.AddressRepository;

@Service
public class AddressService {

	@Autowired
	AddressRepository addrepo;

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public ResponseEntity<Address> getAddressByAddressId(Long addressId) {

		Optional<Address> obj1 = addrepo.findById(addressId);

		if (obj1.isPresent()) {
			return ResponseEntity.status(HttpStatus.OK).body(obj1.get());
		}

		else {
			return new ResponseEntity("Record not found", HttpStatus.NOT_FOUND);
		}
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public ResponseEntity<Address> addAddress(Address address) {
		// TODO Auto-generated method stub
		Address obj1 = addrepo.findByPersonIdAndPersonName(address.getPersonId(), address.getPersonName());
		if (obj1 != null) {
			return new ResponseEntity("Details already exist", HttpStatus.ALREADY_REPORTED);
		}

		else {
			Address obj2 = addrepo.saveAndFlush(address);

			return ResponseEntity.status(HttpStatus.CREATED).body(obj2);
		}

	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public ResponseEntity<String> updateAddress(Long addressId, Address address) {

		Address obj3 = addrepo.findById(addressId).get();

		if (obj3.getAddressId() == addressId) {
			obj3.setLine1(address.getLine1());
			obj3.setLine2(address.getLine2());
			obj3.setCity(address.getCity());
			obj3.setState(address.getState());
			obj3.setPinCode(address.getPinCode());
			addrepo.saveAndFlush(obj3);
			return ResponseEntity.status(HttpStatus.OK).body("Record successfully updated");
		}

		else {
			return new ResponseEntity("Please verify the employeeId", HttpStatus.NOT_FOUND);
		}
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public ResponseEntity<String> deleteAddress(Long addressId) {
		Optional<Address> obj1 = addrepo.findById(addressId);

		if (obj1.isPresent()) {
			addrepo.deleteById(addressId);
			;
			return ResponseEntity.status(HttpStatus.OK).body("Record successfully deleted");
		} else {
			return new ResponseEntity("Record not found", HttpStatus.NOT_FOUND);
		}
	}

	public List<Address> getAllAddress() {
		return addrepo.findAll();
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public ResponseEntity<Address> getAddressByPerson(String personId, String personName) {
		Address obj1 = addrepo.findByPersonIdAndPersonName(personId, personName);

		if (obj1.getPersonId().toString().equals(personId)) {
			return ResponseEntity.status(HttpStatus.OK).body(obj1);
		}

		else {
			return new ResponseEntity("Record not found", HttpStatus.NOT_FOUND);
		}
	}

}
