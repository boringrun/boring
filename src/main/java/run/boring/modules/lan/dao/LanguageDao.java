package run.boring.modules.lan.dao;

import run.boring.modules.lan.entity.LanguageEntity;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

/**
 * 国家语言
 *
 * @author Shaohua Xu
 * @email henryxm@163.com
 * @date 2020-10
 */

@Mapper
@Repository
public interface LanguageDao extends BaseMapper<LanguageEntity> {

    @Select("select count(*) from sys_language where name = #{name}")
    Integer hasKey(@Param("name") String name);

    @Select("select * from sys_language where name = #{name} limit 1")
    LanguageEntity getByKey(@Param("name") String name);
}
