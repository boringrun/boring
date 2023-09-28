package run.boring.modules.gen.dao;

import run.boring.modules.gen.entity.GenTypeEntity;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

/**
 * 代码生成设置
 * 
 * @author Shaohua Xu
 * @email henryxm@163.com
 * @date 2018-10
 */

@Mapper
@Repository
public interface GenTypeDao extends BaseMapper<GenTypeEntity> {
	
}
