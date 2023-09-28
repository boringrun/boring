package run.boring.core.table.dao;

import run.boring.core.table.data.IndexInfo;
import run.boring.core.table.data.TableInfo;
import run.boring.core.table.data.UniqueKeyInfo;
import run.boring.core.table.mysql.ColumnMeta;
import run.boring.core.table.mysql.QuerySql;
import run.boring.core.table.mysql.TableMeta;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Mapper
@Repository
public interface TableDao {

    /**
     * 创建表
     *
     * @param map
     */
    @SelectProvider(type = QuerySql.class, method = QuerySql.createTable)
    void createTable(@Param(QuerySql.paramName) Map<TableInfo, List<Object>> map);

    /**
     * 根据表名查询表在库中是否存在
     *
     * @param tableName
     * @return true 表存在  false 表不存在
     */
    @SelectProvider(type = QuerySql.class, method = QuerySql.hasTable)
    @ResultType(Boolean.class)
    boolean hasTable(@Param(QuerySql.paramName) String tableName);

    /**
     * 查询表的字段
     *
     * @param tableName
     * @return
     */
    @SelectProvider(type = QuerySql.class, method = QuerySql.getColumnMetas)
    @ResultType(List.class)
    List<ColumnMeta> getColumnMetas(@Param(QuerySql.paramName) String tableName);

    /**
     * get table meta data
     * <p>
     * support empty parameter and one parameter with "like" searching.
     *
     * @param tableName
     * @return
     */
    @SelectProvider(type = QuerySql.class, method = QuerySql.getTableMetas)
    @ResultType(List.class)
    List<TableMeta> getTableMetas(@Param(QuerySql.paramName) String tableName,
                                  @Param("offset") int offset,
                                  @Param("rows") int rows);

    /**
     * @param tableName
     * @return
     */
    @SelectProvider(type = QuerySql.class, method = QuerySql.getTableMetas)
    @ResultType(List.class)
    List<TableMeta> getTableMetas(@Param(QuerySql.paramName) String tableName);

    @SelectProvider(type = QuerySql.class, method = QuerySql.showKeys)
    @ResultType(List.class)
    List<UniqueKeyInfo> getTableKeys(@Param(QuerySql.paramName) String tableName);

    @SelectProvider(type = QuerySql.class, method = QuerySql.showIndex)
    @ResultType(List.class)
    List<IndexInfo> getTableIndex(@Param(QuerySql.paramName) String tableName);

    @SelectProvider(type = QuerySql.class, method = QuerySql.getTableCount)
    @ResultType(Integer.class)
    Integer getTableCount();

    /**
     * 为表增加字段
     *
     * @param map
     */
    @SelectProvider(type = QuerySql.class, method = QuerySql.addColumns)
    void addColumns(@Param(QuerySql.paramName) Map<TableInfo, Object> map);

    /**
     * 修改表字段
     *
     * @param map
     */
    @SelectProvider(type = QuerySql.class, method = QuerySql.modifyColumn)
    void modifyColumn(@Param(QuerySql.paramName) Map<TableInfo, Object> map);

    /**
     * 删除表字段
     *
     * @param map
     */
    @SelectProvider(type = QuerySql.class, method = QuerySql.dropColumn)
    void dropColumn(@Param(QuerySql.paramName) Map<TableInfo, Object> map);

    /**
     * 删除主键
     *
     * @param map
     */
    @SelectProvider(type = QuerySql.class, method = QuerySql.dropPrimaryKey)
    void dropPrimaryKey(@Param(QuerySql.paramName) Map<TableInfo, Object> map);

    /**
     * 删除索引
     *
     * @param map
     */
    @SelectProvider(type = QuerySql.class, method = QuerySql.dropIndex)
    void dropIndex(@Param(QuerySql.paramName) Map<TableInfo, Object> map);

    /**
     * 如果表存在，就删除
     *
     * @param tableName
     */
    @SelectProvider(type = QuerySql.class, method = QuerySql.dropTable)
    void dropTable(@Param(QuerySql.paramName) String tableName);
    /**
     * 增加索引
     *
     * @param map
     */
    @SelectProvider(type = QuerySql.class, method = QuerySql.addIndex)
    void addIndex(@Param(QuerySql.paramName) Map<TableInfo, Object> map);
}
