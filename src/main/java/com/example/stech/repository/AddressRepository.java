package com.example.stech.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.stech.model.Address;

public interface AddressRepository extends JpaRepository<Address,Integer> {
}
