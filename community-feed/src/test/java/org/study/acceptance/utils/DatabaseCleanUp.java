package org.study.acceptance.utils;

import jakarta.persistence.Entity;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Table;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Profile("test")
@Component
@Slf4j
public class DatabaseCleanUp implements InitializingBean {

    @PersistenceContext
    private EntityManager entityManager;
    private List<String> tableNames;
    private List<String> notGeneratedIdTableNames;

    @Override
    public void afterPropertiesSet() throws Exception {
        tableNames = entityManager.getMetamodel().getEntities()
            .stream()
            .filter(entity -> entity.getJavaType().getAnnotation(Entity.class) != null)
            .map(entity -> entity.getJavaType().getAnnotation(Table.class).name())
            .toList();
        notGeneratedIdTableNames = List.of("community_user_auth", "community_user_relation", "community_like", "community_fcm_token");
    }

    @Transactional
    public void execute() {
        entityManager.flush();
        entityManager.createNativeQuery("SET REFERENTIAL_INTEGRITY FALSE").executeUpdate();
        for (String tableName : tableNames) {
            entityManager.createNativeQuery("TRUNCATE TABLE " + tableName).executeUpdate();
            if (!notGeneratedIdTableNames.contains(tableName)) {
                entityManager.createNativeQuery("ALTER TABLE " + tableName + " ALTER COLUMN ID RESTART WITH 1").executeUpdate();
            }
        }
        entityManager.createNativeQuery("SET REFERENTIAL_INTEGRITY TRUE").executeUpdate();
    }

}
