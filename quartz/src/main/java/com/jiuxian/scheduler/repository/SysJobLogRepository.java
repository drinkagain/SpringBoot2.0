package com.jiuxian.scheduler.repository;

import com.jiuxian.core.dao.BaseDao;
import com.jiuxian.scheduler.entity.SysJobLog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.io.Serializable;

@Repository
@Transactional
public interface SysJobLogRepository extends BaseDao<SysJobLog> {

}