package com.example.demo.event;

import com.example.demo.model.Order;
import jakarta.annotation.PostConstruct;
import jakarta.persistence.EntityManagerFactory;
import org.hibernate.engine.spi.SessionFactoryImplementor;
import org.hibernate.event.service.spi.EventListenerRegistry;
import org.hibernate.event.spi.EventType;
import org.hibernate.event.spi.PostUpdateEvent;
import org.hibernate.event.spi.PostUpdateEventListener;
import org.hibernate.persister.entity.EntityPersister;
import org.springframework.stereotype.Component;

@Component
public class HibernateEventListener implements PostUpdateEventListener {

    private final EntityManagerFactory entityManagerFactory;

    public HibernateEventListener(EntityManagerFactory entityManagerFactory) {
        this.entityManagerFactory = entityManagerFactory;
    }

    @Override
    public void onPostUpdate(PostUpdateEvent postUpdateEvent) {
        Object entity = postUpdateEvent.getEntity();
        if (entity instanceof Order order) {
            //TODO handle logic...
        }

    }

    @Override
    public boolean requiresPostCommitHandling(EntityPersister entityPersister) {
        return false;
    }

    @PostConstruct
    private void postConstruct() {
        SessionFactoryImplementor sessionFactory = entityManagerFactory.unwrap(SessionFactoryImplementor.class);
        EventListenerRegistry registry = sessionFactory
                .getServiceRegistry()
                .getService(EventListenerRegistry.class);
        assert registry != null;
        registry.prependListeners(EventType.POST_UPDATE, this);
    }
}