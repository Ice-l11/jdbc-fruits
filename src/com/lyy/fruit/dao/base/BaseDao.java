package com.lyy.fruit.dao.base;

import com.lyy.util.JDBCUtil;

import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName BaseDao
 * @Description BaseDao
 * @Author Ice
 * @Date 2022/9/20 15:26
 * @Version 1.0
 **/
public abstract class BaseDao<T> {

    protected Connection conn ;
    protected PreparedStatement ps ;
    protected ResultSet rs ;

    //T的Class对象
    private Class entityClass ;

    public BaseDao(){
        //getClass() 获取Class对象，当前我们执行的是new FruitDAOImpl() , 创建的是FruitDAOImpl的实例
        //那么子类构造方法内部首先会调用父类（BaseDAO）的无参构造方法
        //因此此处的getClass()会被执行，但是getClass获取的是FruitDAOImpl的Class
        //所以getGenericSuperclass()获取到的是BaseDAO的Class
        Type genericType = getClass().getGenericSuperclass();
        //ParameterizedType 参数化类型
        Type[] actualTypeArguments = ((ParameterizedType) genericType).getActualTypeArguments();
        //获取到的<T>中的T的真实的类型
        Type actualType = actualTypeArguments[0];
        try {
            entityClass = Class.forName(actualType.getTypeName());
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    /*
    * 查询一条信息记录，返回单个实体
    */
    protected T queryOne(String sql, Object... args){
        T t = null;
        try {
            conn = JDBCUtil.getConnection();
            ps = conn.prepareStatement(sql);
            for (int i = 0; i < args.length; i++){
                ps.setObject(i + 1, args[i]);
            }
            rs = ps.executeQuery();
            ResultSetMetaData rsmd = rs.getMetaData();
            if (rs.next()){
                t = (T) entityClass.newInstance();
                for (int i = 0; i < rsmd.getColumnCount(); i++){
                    String columnLabel = rsmd.getColumnLabel(i + 1);
                    Object object = rs.getObject(columnLabel);
                    Field field = entityClass.getDeclaredField(columnLabel);
                    field.setAccessible(true);
                    field.set(t,object);
                }
            }
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            JDBCUtil.closeResource(conn,ps,rs);
        }
        return t;
    }

    /*
    * 查询多条记录，返回List集合
    */
    protected List<T> queryList(String sql, Object... args){
        List<T> list = new ArrayList<>();
        try {
            conn = JDBCUtil.getConnection();
            ps = conn.prepareStatement(sql);
            for (int i = 0; i < args.length; i++){
                ps.setObject(i + 1, args[i]);
            }
            rs = ps.executeQuery();
            ResultSetMetaData rsmd = rs.getMetaData();
            while (rs.next()){
                T t = (T) entityClass.newInstance();
                for (int i = 0; i < rsmd.getColumnCount(); i++){
                    String columnLabel = rsmd.getColumnLabel(i + 1);
                    Object object = rs.getObject(columnLabel);
                    Field field = entityClass.getDeclaredField(columnLabel);
                    field.setAccessible(true);
                    field.set(t,object);
                }
                list.add(t);
            }
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            JDBCUtil.closeResource(conn,ps,rs);
        }
        return list;
    }

    /*
    * 执行更新，返回影响行数
    */
    protected int executeUpdate(String sql, Object... args){
        try{
            conn = JDBCUtil.getConnection();
            ps = conn.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
            for (int i = 0; i < args.length; i++){
                ps.setObject(i + 1, args[i]);
            }
            int row = ps.executeUpdate();
            rs = ps.getGeneratedKeys();
            if (rs.next()){
                return ((Long)rs.getLong(1)).intValue();
            }
            return row;
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            JDBCUtil.closeResource(conn,ps,rs);
        }
        return 0;
    }
}
