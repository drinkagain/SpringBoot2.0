package com.jiuxian.scheduler.repository;

import com.jiuxian.dao.BaseDao;
import com.jiuxian.scheduler.entity.SysJobLog;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

@Repository
@Transactional
public interface SysJobLogRepository extends BaseDao<SysJobLog> {

}