package com.kunal_vats.billing_service.controller;

import com.kunal_vats.billing_service.entity.Bill;
import com.kunal_vats.billing_service.enums.Status;
import com.kunal_vats.billing_service.service.BillingService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/bill")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class BillingController {

    private final BillingService billingService;

    @PostMapping("/")
    public ResponseEntity<String> createBill(@RequestBody Bill billBody) {
        billingService.createBill(billBody);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Bill> getBill(@PathVariable("id") Long billId) {
        Bill billInfo = billingService.getBill(billId);
        return ResponseEntity.status(HttpStatus.OK).body(billInfo);
    }

    @GetMapping("/")
    public ResponseEntity<List<Bill>> getAllBills() {
        List<Bill> billInfo = billingService.getAllBills();
        return ResponseEntity.status(HttpStatus.OK).body(billInfo);
    }

    @GetMapping("/status/{status}")
    public ResponseEntity<List<Bill>> getBillsByStatus(@PathVariable("status") Status status) {
        List<Bill> billInfo = billingService.getBillsByStatus(status);
        return ResponseEntity.status(HttpStatus.OK).body(billInfo);
    }

}
