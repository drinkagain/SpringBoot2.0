package com.jiuxian.service;


import com.jiuxian.entity.BaseEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;

import java.util.List;

public interface BaseService<T extends BaseEntity> {
    T findById(String id);

    T findUniqueOne(T eg);

    List<T> findByEg(T eg);

    List<T> findAll();

    List<T> findAll(Specification specification);

    T doSave(T bean);

    T doUpdate(T bean);

    T doSaveOrUpdate(T bean);

    void doSaveOrUpdateBatch(List<T> beans);

    Page findBy(Specification<T> specification, Pageable pageable);

    PageResult getPageResult(Specification<T> specification, Pageable pageable);
}
