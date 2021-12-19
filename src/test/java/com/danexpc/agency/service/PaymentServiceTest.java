package com.danexpc.agency.service;

import com.danexpc.agency.dao.DaoSingletonFactory;
import com.danexpc.agency.dao.PaymentDao;
import com.danexpc.agency.dao.impl.DaoSingletonFactoryImpl;
import com.danexpc.agency.dto.request.PaymentRequestDto;
import com.danexpc.agency.dto.response.PaymentResponseDto;
import com.danexpc.agency.entity.PaymentModel;
import com.danexpc.agency.helpers.Pagination;
import org.junit.jupiter.api.Test;
import org.mockito.MockedStatic;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.time.Instant;
import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.mockStatic;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class PaymentServiceTest {

    DaoSingletonFactory factory = mock(DaoSingletonFactory.class);
    PaymentDao paymentDao = mock(PaymentDao.class);

    @Test
    void testGetPayment() {
        Integer id = 1;
        Integer orderId = 2;
        BigDecimal payment = BigDecimal.valueOf(1000);
        LocalDateTime date = LocalDateTime.now();
        String currencyType = "uah";
        Double exchangeRate = 1.0;

        try (MockedStatic<DaoSingletonFactoryImpl> mocked = mockStatic(DaoSingletonFactoryImpl.class)) {
            mocked.when(DaoSingletonFactoryImpl::getInstance).thenReturn(factory);

            when(factory.getPaymentDao()).thenReturn(
                    paymentDao
            );

            PaymentService paymentService = new PaymentService();

            PaymentModel model = new PaymentModel(
                    id, orderId, payment, date, currencyType, exchangeRate
            );

            when(paymentDao.findById(anyInt())).thenReturn(
                    model
            );

            PaymentResponseDto expectedDto = PaymentResponseDto.fromModel(model);


            PaymentResponseDto actualDto = paymentService.getPaymentById(id);

            assertEquals(expectedDto, actualDto);
        }
    }

    @Test
    void testGetPayments() {
        List<PaymentModel> expected = List.of(new PaymentModel(), new PaymentModel());

        try (MockedStatic<DaoSingletonFactoryImpl> mocked = mockStatic(DaoSingletonFactoryImpl.class)) {
            mocked.when(DaoSingletonFactoryImpl::getInstance).thenReturn(factory);

            when(factory.getPaymentDao()).thenReturn(
                    paymentDao
            );

            PaymentService paymentService = new PaymentService();

            when(paymentDao.findAll(any(Pagination.class))).thenReturn(
                    expected
            );

            List<PaymentResponseDto> actualDtos = paymentService.getAllPayments(new Pagination(10, 0));

            assertEquals(expected.size(), actualDtos.size());
        }
    }

    @Test
    void testUpdatePayment() {
        Integer id = 1;
        Integer orderId = 2;
        BigDecimal payment = BigDecimal.valueOf(1000);
        Long date = Timestamp.from(Instant.now()).getTime();
        String currencyType = "uah";
        Double exchangeRate = 1.0;

        PaymentRequestDto request = new PaymentRequestDto(orderId, payment, date, currencyType, exchangeRate);

        PaymentModel expectedModel = request.buildModel();
        expectedModel.setId(id);

        try (MockedStatic<DaoSingletonFactoryImpl> mocked = mockStatic(DaoSingletonFactoryImpl.class)) {
            mocked.when(DaoSingletonFactoryImpl::getInstance).thenReturn(factory);

            when(factory.getPaymentDao()).thenReturn(
                    paymentDao
            );

            PaymentService paymentService = new PaymentService();

            doNothing().when(paymentDao).update(expectedModel);

            paymentService.updatePayment(id, request);

            verify(paymentDao, times(1)).update(expectedModel);
        }
    }

    @Test
    void testDeletePayment() {
        Integer id = 1;

        try (MockedStatic<DaoSingletonFactoryImpl> mocked = mockStatic(DaoSingletonFactoryImpl.class)) {
            mocked.when(DaoSingletonFactoryImpl::getInstance).thenReturn(factory);

            when(factory.getPaymentDao()).thenReturn(
                    paymentDao
            );

            PaymentService paymentService = new PaymentService();

            doNothing().when(paymentDao).deleteById(id);

            paymentService.deletePaymentById(id);

            verify(paymentDao, times(1)).deleteById(id);
        }
    }
}
