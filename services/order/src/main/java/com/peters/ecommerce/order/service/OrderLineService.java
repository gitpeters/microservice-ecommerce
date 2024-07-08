package com.peters.ecommerce.order.service;

import com.peters.ecommerce.order.dto.OrderLineRequest;
import com.peters.ecommerce.order.dto.OrderLineResponse;
import com.peters.ecommerce.order.repository.OrderLineRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderLineService {
    private final OrderLineRepository repository;
    private final OrderLineMapper mapper;

    public void saveOrderLine(OrderLineRequest request) {
        this.repository.save(mapper.toOrderLine(request));
    }

    public List<OrderLineResponse> findAllByOrderId(Integer orderId) {
        return repository.findAllByOrderId(orderId).stream().map(mapper::fromOrderLine).toList();
    }
}
