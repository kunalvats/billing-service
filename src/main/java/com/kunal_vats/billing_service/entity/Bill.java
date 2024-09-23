package com.kunal_vats.billing_service.entity;

import com.kunal_vats.billing_service.enums.Status;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

@Data
@Entity
public class Bill {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    Long billId;
    Long customerId;
    Long amount;
    Status status;

}
