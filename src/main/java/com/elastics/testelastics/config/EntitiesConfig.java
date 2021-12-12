package com.elastics.testelastics.config;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.rest.core.config.RepositoryRestConfiguration;

import javax.annotation.PostConstruct;
import javax.persistence.EntityManager;
import javax.persistence.metamodel.Type;

@Configuration
@RequiredArgsConstructor
public class EntitiesConfig {

    private final RepositoryRestConfiguration restConfiguration;
    private final EntityManager entityManager;

    @PostConstruct
    void init() {
        restConfiguration.exposeIdsFor(
                entityManager.getMetamodel().getEntities().stream().map(
                        Type::getJavaType
                ).toArray(Class[]::new));
    }

}
