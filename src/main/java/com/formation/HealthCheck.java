package com.formation;

import com.formation.dao.ICatalogDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.actuate.health.Health;
import org.springframework.boot.actuate.health.HealthIndicator;
import org.springframework.stereotype.Component;

@Component
public class HealthCheck implements HealthIndicator {
    @Autowired
    private ICatalogDAO catalogService;

    @Override
    public Health health() {
        if(!catalogService.get().isPresent() ||
                catalogService.get().get().isEmpty()) {
            return Health.outOfService().build();
        }
        return Health.up().build();
    }
}
