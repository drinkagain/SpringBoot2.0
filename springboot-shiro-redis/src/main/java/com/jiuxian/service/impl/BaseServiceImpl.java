package com.jiuxian.service.impl;

import com.jiuxian.dao.BaseDao;
import com.jiuxian.entity.BaseEntity;
import com.jiuxian.exception.DaoException;
import com.jiuxian.service.BaseService;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;

import java.util.List;

public class BaseServiceImpl<T extends BaseEntity> implements BaseService<T> {
    protected BaseDao<T> dao;

    @Override
    public T findOne(Long id) {
        return dao.findOne(id);
    }

    @Override
    public T findUniqueOne(T eg) {
        Example<T> example = Example.of(eg);
        return dao.findOne(example);
    }

    @Override
    public List<T> findByEg(T eg) {
        Example<T> example = Example.of(eg);
        return dao.findAll(example);
    }

    @Override
    public List<T> findAll() {
        return dao.findAll();
    }

    @Override
    public List<T> findAll(Specification specification) {
        return dao.findAll(specification);
    }

    @Override
    public T doSave(T bean) {
        bean.setId(null);
        return dao.save(bean);
    }

    @Override
    public T doUpdate(T bean) {
        if (bean.getId() == null) {
            throw new DaoException("ID CANNOT NULL");
        }
        return dao.save(bean);
    }

    @Override
    public T doSaveOrUpdate(T bean) {
        return dao.save(bean);
    }

    @Override
    public List<T> doSaveBatch(List<T> list) {
        list.forEach(bean -> bean.setId(null));
        return dao.save(list);
    }

    @Override
    public List<T> doUpdateBatch(List<T> list) {
        list.forEach(bean -> {
            if (bean.getId() == null) {
                throw new DaoException("ID CANNOT NULL");
            }
        });
        return dao.save(list);
    }

    @Override
    public void doDeleteById(Long id) {
        dao.delete(id);
    }

    @Override
    public void doDeleteByIds(Long[] ids) {
        if (ids != null) {
            for (Long id : ids) {
                dao.delete(id);
            }
        }
    }

    @Override
    public void doDeleteAll() {
        dao.deleteAllInBatch();
    }

    @Override
    public Page<T> findBy(Specification specification, Pageable pageable) {
        return dao.findAll(specification, pageable);
    }
}
