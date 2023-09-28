package run.boring.modules.oss.dao;


import run.boring.modules.oss.entity.SysOssEntity;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

/**
 * 文件上传
 */
@Mapper
@Repository
public interface SysOssDao extends BaseMapper<SysOssEntity> {
	
}
