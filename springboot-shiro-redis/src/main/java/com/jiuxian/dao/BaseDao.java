package com.jiuxian.dao;

import com.jiuxian.entity.BaseEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.querydsl.QueryDslPredicateExecutor;
import org.springframework.data.repository.NoRepositoryBean;

import java.io.Serializable;

@NoRepositoryBean
public interface BaseDao<T extends BaseEntity> extends JpaRepository<T, Serializable>, JpaSpecificationExecutor<T> {
}
