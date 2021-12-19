package com.danexpc.agency.service;

import com.danexpc.agency.builder.NotificationDtoBuilder;
import com.danexpc.agency.dao.DaoSingletonFactory;
import com.danexpc.agency.dao.PaymentDao;
import com.danexpc.agency.dao.impl.DaoSingletonFactoryImpl;
import com.danexpc.agency.dto.notification.NotificationDto;
import com.danexpc.agency.dto.request.PaymentRequestDto;
import com.danexpc.agency.dto.response.OrderResponseDto;
import com.danexpc.agency.dto.response.PaymentResponseDto;
import com.danexpc.agency.entity.PaymentModel;
import com.danexpc.agency.helpers.Pagination;
import com.danexpc.agency.rmq.NotificationsSender;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.SneakyThrows;

import java.util.List;
import java.util.stream.Collectors;

public class PaymentService {
    private final DaoSingletonFactory factory = DaoSingletonFactoryImpl.getInstance();

    private final PaymentDao paymentDao = factory.getPaymentDao();

    private final NotificationService notificationService = new NotificationService();

    public void createPayment(PaymentRequestDto dto) {
        PaymentModel model = dto.buildModel();

        // payment logic

        paymentDao.create(model);

        notificationService.sendMessage(dto);
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
    public List<PaymentResponseDto> getAllPayments(Pagination pagination) {
        List<PaymentModel> models = paymentDao.findAll(pagination);

        return models.stream().parallel().map(PaymentResponseDto::fromModel).collect(Collectors.toUnmodifiableList());
    }

    public void deletePaymentById(Integer id) {
        paymentDao.deleteById(id);
    }
}
