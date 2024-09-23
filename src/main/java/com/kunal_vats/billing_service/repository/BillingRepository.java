package com.kunal_vats.billing_service.repository;

import com.kunal_vats.billing_service.entity.Bill;
import com.kunal_vats.billing_service.enums.Status;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BillingRepository extends CrudRepository<Bill, Long> {

    List<Bill> findAllByStatus(Status status);

}
