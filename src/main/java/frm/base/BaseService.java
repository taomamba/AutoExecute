package frm.base;


import com.github.pagehelper.PageHelper;
import org.apache.ibatis.session.RowBounds;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * 单表CRUD操作基础类
 * @author taoyc
 * @param <T>
 */
public abstract class BaseService<T> {

    @Autowired
    protected BaseMapper<T> mapper;

    protected void setBaseMapper(BaseMapper<T> mapper) {
        this.mapper= mapper;
    }

    /**************************************************
     ********************  Select  ********************
     **************************************************/

    /**
     * 根据实体中的属性值进行查询，查询条件使用等号
     * order by实体的@OrderBy
     * @param entity
     * @return
     */
    public List<T> select(T entity) {
        return mapper.select(entity);
    }

    /**
     * 查询全部结果，select(null)方法能达到同样的效果
     * order by实体的@OrderBy
     * @return
     */
    public List<T> selectAll() {
        return mapper.selectAll();
    }

    /**
     * 根据实体中的属性进行查询，只能有一个返回值，有多个结果是抛出异常，查询条件使用等号
     * @param entity
     * @return
     */
    public T selectOne(T entity) {
        return mapper.selectOne(entity);
    }

    /**
     * 根据主键字段进行查询，方法参数必须包含完整的主键属性，查询条件使用等号
     * @param key
     * @return
     */
    public T selectByPrimaryKey(Object key) {
        return mapper.selectByPrimaryKey(key);
    }

    /**
     * 根据Condition条件进行查询，支持通过Condition类指定查询列
     * Condition con = new Condition(Class<?> modelClass)
     * con.selectProperties方法指定查询列
     * con.createCriteria()后加上查询条件、排序条件等
     * andEqualTo将实体对象的不为空的字段参数作为相等查询条件
     * andAllEqualTo将实体对象的所有字段参数作为相等查询条件，如果字段为 null，则为 is null
     * 支持or、>=、<=、>、<、<>、between、not between、count(字段名)、is null、is not null、in、not in、like、not like
     * 支持distinct、forUpdate
     * con.andCondition方法可写入sql查询条件，例如 "length(字段名)=”
     *
     * 当condition使用orderby时，按condition的orderby排序
     * 当condition未使用orderby时，按实体的@OrderBy排序
     * @param condition
     * @return
     */
    public List<T> selectByCondition(Object condition) {
        return mapper.selectByCondition(condition);
    }

    /**
     * 根据主键字符串进行查询，类中只有存在一个带有@Id注解的字段
     * order by实体的@OrderBy
     * @param ids
     * @return
     */
    public List<T> selectByIds(String ids) {
        return mapper.selectByIds(ids);
    }

    /**
     * 根据实体中的属性查询总数，查询条件使用等号
     * @param entity
     * @return
     */
    public int selectCount(T entity) {
        return mapper.selectCount(entity);
    }

    /**
     * 根据Condition条件进行查询总数
     * @param condition
     * @return
     */
    public int selectCountByCondition(Object condition) {
        return mapper.selectCountByCondition(condition);
    }

    /**
     * 根据主键字段进行查询是否存在记录，方法参数必须包含完整的主键属性，查询条件使用等号
     * @param key
     * @return
     */
    public boolean existsWithPrimaryKey(Object key) {
        return mapper.existsWithPrimaryKey(key);
    }

    /**
     * 根据实体属性和RowBounds进行分页查询 new RowBounds(int offset, int limit) offset从0开始
     * order by实体的@OrderBy
     * RowBounds是逻辑分页，性能差，慎用
     * @param entity
     * @param rowBounds
     * @return
     */
    public List<T> selectByRowBounds(T entity, RowBounds rowBounds) {
        return mapper.selectByRowBounds(entity, rowBounds);
    }

    /**
     * 根据Condition条件和RowBounds进行分页查询 new RowBounds(int offset, int limit) offset从0开始
     * 当condition使用orderby时，按condition的orderby排序
     * 当condition未使用orderby时，按实体的@OrderBy排序
     * RowBounds是逻辑分页，性能差，慎用
     * @param condition
     * @param rowBounds
     * @return
     */
 /*   public List<T> selectByConditionAndRowBounds(Object condition, RowBounds rowBounds) {
        return mapper.selectByConditionAndRowBounds(condition, rowBounds);
    }*/

    /**
     * 使用PageHelper进行分页查询 pageNum=1或pageNum=0都表示第1页
     * PageHelper是物理分页，性能高
     * order by实体的@OrderBy
     * @param pageNum
     * @param pageSize
     * @return
     */
    public List<T> selectPage(int pageNum, int pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        return mapper.select(null);
    }


    /**************************************************
     ********************  Insert  ********************
     **************************************************/

    /**
     * 保存一个实体，null的属性也会保存，不会使用数据库默认值
     * @param entity
     * @return
     */
    public int insert(T entity) {
        return mapper.insert(entity);
    }

    /**
     * 保存一个实体，null的属性不会保存，会使用数据库默认值
     * @param entity
     * @return
     */
    public int insertSelective(T entity) {
        return mapper.insertSelective(entity);
    }

    /**
     * 插入数据，限制为实体包含id属性并且必须为自增列，实体配置的主键策略无效
     * @param entity
     * @return
     */
    public int insertUseGeneratedKeys(T entity) {
        return mapper.insertUseGeneratedKeys(entity);
    }

    /**
     * 批量插入，限制实体包含id属性并且必须为数据库自增列
     * null的属性也会保存，不会使用数据库默认值
     * @param entityList
     * @return
     */
    public int insertBatchNoPk(List<T> entityList) {
        return mapper.insertList(entityList);
    }

    /**
     * 批量插入，主键由应用生成，非数据库生成
     * null的属性也会保存，不会使用数据库默认值
     * @param entityList
     * @return
     */
    public int insertBatchWithPk(List<T> entityList) {
        return mapper.insertBatchWithPk(entityList);
    }

    public int updateBatchByPrimaryKey(List<T> entityList) {
        return mapper.updateBatchByPrimaryKey(entityList);
    }

    /**************************************************
     ********************  Update  ********************
     **************************************************/

    /**
     * 根据主键更新实体全部字段，null值会被更新
     * @param entity
     * @return
     */
    public int updateByPrimaryKey(T entity) {
        return mapper.updateByPrimaryKey(entity);
    }

    /**
     * 根据主键更新属性不为null的值
     * @param entity
     * @return
     */
    public int updateByPrimaryKeySelective(T entity) {
        return mapper.updateByPrimaryKeySelective(entity);
    }

    /**
     * 根据Condition条件更新实体entity包含的全部属性，null值会被更新
     * @param entity
     * @param condition
     * @return
     */
    public int updateByCondition(T entity, Object condition) {
        return mapper.updateByCondition(entity, condition);
    }

    /**
     * 根据Condition条件更新实体entity包含的不是null的属性值
     * @param entity
     * @param condition
     * @return
     */
    public int updateByConditionSelective(T entity, Object condition) {
        return mapper.updateByConditionSelective(entity, condition);
    }

    /**************************************************
     ********************  Delete  ********************
     **************************************************/

    /**
     * 根据实体属性作为条件进行删除，查询条件使用等号
     * @param entity
     * @return
     */
    public int delete(T entity) {
        return mapper.delete(entity);
    }

    /**
     * 根据主键字段进行删除，方法参数必须包含完整的主键属性
     * @param key
     * @return
     */
    public int deleteByPrimaryKey(Object key) {
        return mapper.deleteByPrimaryKey(key);
    }

    /**
     * 根据Condition条件删除数据
     * @param condition
     * @return
     */
    public int deleteByCondition(Object condition) {
        return mapper.deleteByCondition(condition);
    }

    /**
     * 根据主键字符串进行删除，类中只有存在一个带有@Id注解的字段
     * @param ids
     * @return
     */
    public int deleteByIds(String ids) {
        return mapper.deleteByIds(ids);
    }
}
