package com.kunal_vats.billing_service.service;

import com.kunal_vats.billing_service.entity.Bill;
import com.kunal_vats.billing_service.enums.Status;

import java.util.List;

public interface BillingService {

    void createBill(Bill billInfo);

    Bill getBill(Long id);

    List<Bill> getAllBills();

    List<Bill> getBillsByStatus(Status status);

}
