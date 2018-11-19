package com.jiuxian.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;

import java.util.List;

public interface BaseService<T> {
    T findOne(Long id);

    T findUniqueOne(T eg);

    List<T> findByEg(T eg);

    List<T> findAll();

    List<T> findAll(Specification specification);

    T doSave(T bean);

    T doUpdate(T bean);

    T doSaveOrUpdate(T bean);

    List<T> doSaveBatch(List<T> list);

    List<T> doUpdateBatch(List<T> list);

    void doDeleteById(Long id);

    void doDeleteByIds(Long[] ids);

    void doDeleteAll();

    public Page<T> findBy(Specification specification, Pageable pageable);
}
