package com.example.demo.mapper;

import com.example.demo.dto.OrderDto;
import com.example.demo.entity.Order;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.ReportingPolicy;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = "spring")
public interface OrderMapper {
    Order toEntity(OrderDto orderDto);

    OrderDto toOrderDto(Order order);

    Order updateWithNull(OrderDto orderDto, @MappingTarget Order order);
}