package com.example.customer.repository;

import com.example.customer.entity.AddressEntity;
import com.example.customer.entity.CustomerEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AddressRepository extends JpaRepository<AddressEntity, Long> {
    List<AddressEntity> findAllByCustomerEntity(CustomerEntity customerEntity);
}
