package com.jiuxian.service.impl;

import com.jiuxian.dao.BaseDao;
import com.jiuxian.entity.BaseEntity;
import com.jiuxian.service.BaseService;
import com.jiuxian.service.PageResult;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.util.Assert;

import java.util.Date;
import java.util.List;
import java.util.Optional;

public class BaseServiceImpl<T extends BaseEntity> implements BaseService<T> {
    protected BaseDao<T> dao;

    @Override
    public T findById(String id) {
        return dao.getOne(id);
    }

    @Override
    public T findUniqueOne(T eg) {
        Example<T> example = Example.of(eg);
        Optional<T> one = dao.findOne(example);
        return one.orElse(null);
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
        bean.setCreateTime(new Date());
        bean.setUpdateTime(new Date());
        return dao.save(bean);
    }

    @Override
    public T doUpdate(T bean) {
        Assert.isNull(bean.getId(), "ID 不能为空");
        T before = this.findById(bean.getId());
        bean.setCreateTime(before.getCreateTime());
        bean.setUpdateTime(new Date());
        return dao.save(bean);
    }

    @Override
    public T doSaveOrUpdate(T bean) {
        return dao.save(bean);
    }

    @Override
    public void doSaveOrUpdateBatch(List<T> beans) {
        dao.saveAll(beans);
    }

    @Override
    public Page findBy(Specification<T> specification, Pageable pageable) {
        return dao.findAll(specification, pageable);
    }

    @Override
    public PageResult getPageResult(Specification<T> specification, Pageable pageable) {
        Page page = this.findBy(specification, pageable);
        return new PageResult(1, 1, 1, page.getContent());
    }
}
