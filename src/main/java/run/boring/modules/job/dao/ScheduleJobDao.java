package run.boring.modules.job.dao;

import run.boring.modules.job.entity.ScheduleJobEntity;
import run.boring.core.mybatis.SelectInLangDriver;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface ScheduleJobDao extends BaseMapper<ScheduleJobEntity> {

    @Update("UPDATE sys_schedule_job SET status = #{status} where job_id IN (#{jobIds})")
    @Lang(SelectInLangDriver.class)
    int updateBatch(@Param("jobIds") Long[] jobIds, @Param("status") int status);
}
