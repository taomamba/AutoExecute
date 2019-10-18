package frm.provider;

import org.apache.ibatis.mapping.MappedStatement;
import tk.mybatis.mapper.entity.EntityColumn;
import tk.mybatis.mapper.mapperhelper.EntityHelper;
import tk.mybatis.mapper.mapperhelper.MapperHelper;
import tk.mybatis.mapper.mapperhelper.MapperTemplate;
import tk.mybatis.mapper.mapperhelper.SqlHelper;

import java.util.Set;

/**
 * BatchProvider实现类，实现数据库批量操作
 *
 * @author taoyc
 */
public class BatchProvider extends MapperTemplate {
	public BatchProvider(Class<?> mapperClass, MapperHelper mapperHelper) {
        super(mapperClass, mapperHelper);
    }
	
	/**
	 * 批量插入，主键由应用生成，非数据库生成
	 * null的属性也会保存，不会使用数据库默认值
	 * 
	 * @param ms
	 */
	public String insertBatchWithPk(MappedStatement ms) {
		final Class<?> entityClass = getEntityClass(ms);
		StringBuilder sql = new StringBuilder();
		
		//拼sql
        sql.append(SqlHelper.insertIntoTable(entityClass, tableName(entityClass)));
        sql.append(SqlHelper.insertColumns(entityClass, false, false, false));
        sql.append(" VALUES ");
        sql.append("<foreach collection=\"list\" item=\"record\" separator=\",\" >");
        sql.append("<trim prefix=\"(\" suffix=\")\" suffixOverrides=\",\">");

        //获取全部列
        Set<EntityColumn> columnList = EntityHelper.getColumns(entityClass);
        for (EntityColumn column : columnList) {
        	if (column.isInsertable()) {
        		sql.append(column.getColumnHolder("record") + ",");
        	}
        }
        sql.append("</trim>");
        sql.append("</foreach>");
        
        return sql.toString();
	}
	
	/**
	 * 批量修改
	 * null的属性也会保存，不会使用数据库默认值
	 * 实体字段主键名必须为id，并且id类型为varchar
	 * @param ms
	 * @return
	 */
	public String updateBatchByPrimaryKey(MappedStatement ms) {
		final Class<?> entityClass = getEntityClass(ms);
        StringBuilder sql = new StringBuilder();
        sql.append(SqlHelper.updateTable(entityClass, tableName(entityClass)));
        sql.append("<trim prefix=\"set\" suffixOverrides=\",\">");
        
        //获取全部列
        Set<EntityColumn> columnList = EntityHelper.getColumns(entityClass);
        for (EntityColumn column : columnList) {
        	if (!column.isId()) { //id字段不更新
        		sql.append("<trim prefix=\"").append(column.getColumn()).append("=case\" suffix=\"end,\">");
        		sql.append("<foreach collection=\"list\" item=\"record\">");
        		sql.append(" when id=#{record.id} then ").append(column.getColumnHolder("record"));
        		sql.append("</foreach>");
        		sql.append("</trim>");
        	}
        }
        sql.append("</trim>");
        
        sql.append("where id in");
        sql.append("<foreach collection=\"list\" item=\"record\" separator=\",\" open=\"(\" close=\")\">");
        for (EntityColumn column : columnList) {
        	if (column.isId()) { //id字段
        		sql.append("#{record.id,jdbcType=VARCHAR}");
        		break;
        	}
        }
        sql.append("</foreach>");
        
        return sql.toString();
	}
	public String deleteBatchByPrimaryKey(MappedStatement ms) {
		final Class<?> entityClass = getEntityClass(ms);
		StringBuilder sql = new StringBuilder();
		sql.append(SqlHelper.deleteFromTable(entityClass, tableName(entityClass)));
		sql.append("<trim prefix=\"set\" suffixOverrides=\",\">");
		sql.append("</trim>");
		sql.append("where id in");
		sql.append("<foreach collection=\"list\" item=\"record\" separator=\",\" open=\"(\" close=\")\">");
		sql.append("</foreach>");
		return sql.toString();
	}

}
