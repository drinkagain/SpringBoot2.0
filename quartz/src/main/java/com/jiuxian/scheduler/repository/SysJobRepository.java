package com.jiuxian.scheduler.repository;

import com.jiuxian.core.dao.BaseDao;
import com.jiuxian.scheduler.entity.SysJob;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.io.Serializable;
import java.util.List;

/**
 * @author liuzejun
 * @Date 17:21 2018/3/2
 * @Desc
 */
@Repository
@Transactional
public interface SysJobRepository extends BaseDao<SysJob> {
    List<SysJob> findByEnableStatus(String enableStatus);

    @Modifying
    @Query(" UPDATE SysJob job SET job.jobStatus =:jobStatus  where job.id =:jobId")
    void doUpdateJobStatus(@Param("jobId") String jobId, @Param("jobStatus") String jobStatus);
}
