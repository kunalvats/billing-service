package com.kunal_vats.billing_service.service.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.kunal_vats.billing_service.entity.Bill;
import com.kunal_vats.billing_service.enums.Status;
import com.kunal_vats.billing_service.repository.BillingRepository;
import com.kunal_vats.billing_service.service.BillingService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import static com.kunal_vats.billing_service.constants.Constants.CustomerServiceURI;

@Slf4j
@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class BillingServiceImpl implements BillingService {

    private final RestTemplate restTemplate;
    private final BillingRepository billingRepository;

    @Override
    public void createBill(Bill billInfo) {
        if(!customerExists(billInfo.getCustomerId())) {
            log.error("Customer not found");
            throw new NoSuchElementException(String.format("Customer does not exist for customer id: %s", billInfo.getCustomerId()));
        }
        billingRepository.save(billInfo);

    }

    @Override
    public Bill getBill(Long id) {
        Optional<Bill> optionalBill = billingRepository.findById(id);
        if (optionalBill.isEmpty()) {
            log.error("Not Found");
            throw new NoSuchElementException(String.format("Bill not found for Id: %s", id));
        }
        return optionalBill.get();
    }

    @Override
    public List<Bill> getAllBills() {
         return (List<Bill>) billingRepository.findAll();
    }

    @Override
    public List<Bill> getBillsByStatus(Status status) {
        return (List<Bill>) billingRepository.findAllByStatus(status);
    }

    public boolean customerExists(Long customerId) {
        try{
            ResponseEntity<Object> responseEntity = restTemplate.exchange(String.format(CustomerServiceURI, customerId),
                    HttpMethod.GET,
                    null,
                    new ParameterizedTypeReference<Object>() {
                    });
            return responseEntity.getStatusCode() == HttpStatus.OK;
        } catch (Exception e){
            throw new NoSuchElementException(String.format("Customer not found for Id: %s", customerId));
        }
    }
}
