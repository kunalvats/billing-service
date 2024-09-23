package com.kunal_vats.billing_service.service;

import com.kunal_vats.billing_service.entity.Bill;
import com.kunal_vats.billing_service.enums.Status;
import com.kunal_vats.billing_service.repository.BillingRepository;
import com.kunal_vats.billing_service.service.impl.BillingServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
        import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;

class BillingServiceImplTests {

    @Mock
    private RestTemplate restTemplate;

    @Mock
    private BillingRepository billingRepository;

    @InjectMocks
    private BillingServiceImpl billingService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void createBill_CustomerExists_SavesBill() {
        Bill bill = new Bill();
        bill.setCustomerId(1L);

        when(restTemplate.exchange(anyString(), eq(HttpMethod.GET), isNull(), any(ParameterizedTypeReference.class)))
                .thenReturn(new ResponseEntity<>(HttpStatus.OK));
        when(billingRepository.save(any(Bill.class))).thenReturn(bill);

        billingService.createBill(bill);

        verify(billingRepository, times(1)).save(bill);
    }

    @Test
    void createBill_CustomerDoesNotExist_ThrowsException() {
        Bill bill = new Bill();
        bill.setCustomerId(1L);

        when(restTemplate.exchange(anyString(), eq(HttpMethod.GET), isNull(), any(ParameterizedTypeReference.class)))
                .thenThrow(new NoSuchElementException());

        assertThrows(NoSuchElementException.class, () -> billingService.createBill(bill));
    }

    @Test
    void getBill_BillExists_ReturnsBill() {
        Bill bill = new Bill();
        when(billingRepository.findById(anyLong())).thenReturn(Optional.of(bill));

        Bill result = billingService.getBill(1L);

        assertEquals(bill, result);
    }

    @Test
    void getBill_BillDoesNotExist_ThrowsException() {
        when(billingRepository.findById(anyLong())).thenReturn(Optional.empty());

        assertThrows(NoSuchElementException.class, () -> billingService.getBill(1L));
    }

    @Test
    void getAllBills_ReturnsListOfBills() {
        List<Bill> bills = Arrays.asList(new Bill(), new Bill());
        when(billingRepository.findAll()).thenReturn(bills);

        List<Bill> result = billingService.getAllBills();

        assertEquals(bills, result);
    }

    @Test
    void getBillsByStatus_ReturnsListOfBills() {
        List<Bill> bills = Arrays.asList(new Bill(), new Bill());
        when(billingRepository.findAllByStatus(any(Status.class))).thenReturn(bills);

        List<Bill> result = billingService.getBillsByStatus(Status.PAID);

        assertEquals(bills, result);
    }

    @Test
    void customerExists_CustomerExists_ReturnsTrue() {
        when(restTemplate.exchange(anyString(), eq(HttpMethod.GET), isNull(), any(ParameterizedTypeReference.class)))
                .thenReturn(new ResponseEntity<>(HttpStatus.OK));

        boolean result = billingService.customerExists(1L);

        assertTrue(result);
    }

    @Test
    void customerExists_CustomerDoesNotExist_ThrowsException() {
        when(restTemplate.exchange(anyString(), eq(HttpMethod.GET), isNull(), any(ParameterizedTypeReference.class)))
                .thenThrow(new NoSuchElementException());

        assertThrows(NoSuchElementException.class, () -> billingService.customerExists(1L));
    }
}