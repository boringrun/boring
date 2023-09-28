package run.boring.modules.sys.dao;

import run.boring.modules.sys.entity.SysLogEntity;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface SysLogDao extends BaseMapper<SysLogEntity> {
    @Delete("truncate table sys_log")
    void clear();
}
