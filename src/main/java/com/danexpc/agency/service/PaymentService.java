package com.danexpc.agency.service;

import com.danexpc.agency.dao.DaoSingletonFactory;
import com.danexpc.agency.dao.PaymentDao;
import com.danexpc.agency.dao.impl.DaoSingletonFactoryImpl;
import com.danexpc.agency.dto.request.PaymentRequestDto;
import com.danexpc.agency.dto.response.PaymentResponseDto;
import com.danexpc.agency.entity.PaymentModel;
import lombok.SneakyThrows;

import java.util.List;
import java.util.stream.Collectors;

public class PaymentService {
    private final DaoSingletonFactory factory = DaoSingletonFactoryImpl.getInstance();

    private final PaymentDao paymentDao = factory.getPaymentDao();

    public void createPayment(PaymentRequestDto dto) {
        PaymentModel model = dto.buildModel();

        paymentDao.create(model);
    }

    public void updatePayment(Integer id, PaymentRequestDto dto) {
        PaymentModel model = dto.buildModel();
        model.setId(id);

        paymentDao.update(model);
    }

    @SneakyThrows
    public PaymentResponseDto getPaymentById(Integer id) {
        PaymentModel model = paymentDao.findById(id);

        return PaymentResponseDto.fromModel(model);
    }

    @SneakyThrows
    public List<PaymentResponseDto> getAllPayments() {
        List<PaymentModel> models = paymentDao.findAll();

        return models.stream().parallel().map(PaymentResponseDto::fromModel).collect(Collectors.toUnmodifiableList());
    }

    public void deletePaymentById(Integer id) {
        paymentDao.deleteById(id);
    }
}
