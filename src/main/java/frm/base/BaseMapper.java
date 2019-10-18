package frm.base;

import frm.provider.BatchProvider;
import org.apache.ibatis.annotations.InsertProvider;
import org.apache.ibatis.annotations.UpdateProvider;
import tk.mybatis.mapper.common.ConditionMapper;
import tk.mybatis.mapper.common.IdsMapper;
import tk.mybatis.mapper.common.Mapper;
import tk.mybatis.mapper.common.MySqlMapper;

import java.util.List;

public interface BaseMapper<T> extends
        Mapper<T>,
        IdsMapper<T>,
        ConditionMapper<T>,
        MySqlMapper<T> {

    /**
     * 批量插入，主键由应用生成，非数据库生成
     * null的属性也会保存，不会使用数据库默认值
     *
     * @param recordList
     */
    @InsertProvider(type = BatchProvider.class, method = "dynamicSQL")
    int insertBatchWithPk(List<T> recordList);

    /**
     * 批量更新
     */
    @UpdateProvider(type = BatchProvider.class, method = "dynamicSQL")
    int updateBatchByPrimaryKey(List<T> recordList);
    /**
     * 批量删除
     */
    @UpdateProvider(type = BatchProvider.class, method = "dynamicSQL")
    int deleteBatchByPrimaryKey(List<T> recordList);
}