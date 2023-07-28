package com.example.SpringBootCacheIntegrationWithRedis.repository;

import com.example.SpringBootCacheIntegrationWithRedis.model.EmployeeDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmployeeDetailsRepository extends JpaRepository<EmployeeDetails,Long> {
}
