package com.example.SpringBootCacheIntegrationWithRedis.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;

@Entity
@Table(name = "employee_details")
@Getter
@Setter
public class EmployeeDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "emp_name")
    private String employeeName;

    @Column(name = "dept_name")
    private String deptName;

    @Column(name = "emp_type")
    private String employmentType;

    @Column(name = "salary")
    private Long salary;

    @Column(name = "is_active")
    private boolean isActive=true;

    @Column(name = "deleted_flag")
    private boolean deletedFlag=false;

    @CreationTimestamp
    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @UpdateTimestamp
    @Column(name = "modified_at")
    private LocalDateTime modifiedAt;
}
