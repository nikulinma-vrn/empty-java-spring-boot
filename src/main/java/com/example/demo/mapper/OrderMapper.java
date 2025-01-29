package com.example.demo.mapper;

import com.example.demo.dto.OrderDto;
import com.example.demo.model.Order;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = "spring")
public interface OrderMapper {
    Order toEntity(OrderDto orderDto);

    OrderDto toOrderDto(Order order);
}