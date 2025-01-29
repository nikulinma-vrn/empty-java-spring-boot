package com.example.demo.config;

import com.example.demo.dto.OrderDto;
import com.example.demo.mapper.OrderMapper;
import com.example.demo.model.Order;
import jakarta.annotation.PostConstruct;
import jakarta.persistence.EntityManagerFactory;
import org.hibernate.HibernateException;
import org.hibernate.engine.spi.SessionFactoryImplementor;
import org.hibernate.event.service.spi.EventListenerRegistry;
import org.hibernate.event.spi.EventType;
import org.hibernate.event.spi.LoadEvent;
import org.hibernate.event.spi.LoadEventListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Component
public class HibernateEventListener implements LoadEventListener {

    private final EntityManagerFactory entityManagerFactory;

    private final OrderMapper orderMapper;

    private final KafkaTemplate<String, OrderDto> kafkaTemplate;

    public HibernateEventListener(EntityManagerFactory entityManagerFactory,
                                  OrderMapper orderMapper,
                                  KafkaTemplate<String, OrderDto> kafkaTemplate) {
        this.entityManagerFactory = entityManagerFactory;
        this.orderMapper = orderMapper;
        this.kafkaTemplate = kafkaTemplate;
    }

    public void sendMessage(Order order) {
        kafkaTemplate.send("Order created", orderMapper.toOrderDto(order));
    }
    @Override
    public void onLoad(LoadEvent loadEvent, LoadType loadType) throws HibernateException {

    }

    @PostConstruct
    private void postConstruct() {
        SessionFactoryImplementor sessionFactory = entityManagerFactory.unwrap(SessionFactoryImplementor.class);
        EventListenerRegistry registry = sessionFactory
                .getServiceRegistry()
                .getService(EventListenerRegistry.class);
        assert registry != null;
        registry.prependListeners(EventType.LOAD, this);
    }
}