package com.jiuxian.dao;

import com.jiuxian.entity.BaseEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.NoRepositoryBean;

@NoRepositoryBean
public interface BaseDao<T extends BaseEntity> extends JpaRepository<T, String>, JpaSpecificationExecutor<T> {
}
