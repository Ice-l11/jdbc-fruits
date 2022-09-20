package com.lyy.fruit.dao.impl;

import com.lyy.fruit.dao.FruitDao;
import com.lyy.fruit.pojo.Fruit;
import com.lyy.util.JDBCUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName FruitDaoImpl
 * @Description 接口实现类
 * @Author Ice
 * @Date 2022/9/20 11:00
 * @Version 1.0
 **/
public class FruitDaoImpl implements FruitDao {

    private static final String TABLE_NAME = "t_fruit"; //表名称

    @Override
    public List<Fruit> getFruitList() {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        List<Fruit> fruitList = null;
        try {
            conn = JDBCUtil.getConnection();
            ps = conn.prepareStatement("select fid,fname,price,fcount,remark from " + TABLE_NAME);
            rs = ps.executeQuery();
            fruitList = new ArrayList<>();
            while (rs.next()){
                int fid = rs.getInt(1);
                String fname = rs.getString(2);
                int price = rs.getInt(3);
                int fcount = rs.getInt(4);
                String remark = rs.getString(5);
                Fruit fruit = new Fruit(fid,fname,price,fcount,remark);
                fruitList.add(fruit);
            }
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            JDBCUtil.closeResource(conn,ps,rs);
        }
        return fruitList;
    }

    @Override
    public Fruit getFruitByName(String name) {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        Fruit fruit = null;
        try {
            conn = JDBCUtil.getConnection();
            ps = conn.prepareStatement("select fid,fname,price,fcount,remark from " + TABLE_NAME +" where fname = ?");
            ps.setObject(1,name);
            rs = ps.executeQuery();
            while (rs.next()){
                int fid = rs.getInt(1);
                String fname = rs.getString(2);
                int price = rs.getInt(3);
                int fcount = rs.getInt(4);
                String remark = rs.getString(5);
                fruit = new Fruit(fid,fname,price,fcount,remark);
            }
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            JDBCUtil.closeResource(conn,ps,rs);
        }
        return fruit;
    }

    @Override
    public boolean addFruit(Fruit fruit) {
        Connection conn = null;
        PreparedStatement ps = null;
        boolean flag = false;
        try {
            conn = JDBCUtil.getConnection();
            ps = conn.prepareStatement("insert into " + TABLE_NAME + "(fname,price,fcount,remark) values(?,?,?,?)");
            ps.setObject(1,fruit.getFname());
            ps.setObject(2,fruit.getPrice());
            ps.setObject(3,fruit.getFcount());
            ps.setObject(4,fruit.getRemark());
            flag = ps.executeUpdate() > 0;
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            JDBCUtil.closeResource(conn,ps);
        }
        return flag;
    }

    @Override
    public boolean updateFruit(Fruit fruit) {
        Connection conn = null;
        PreparedStatement ps = null;
        boolean flag = false;
        try {
            conn = JDBCUtil.getConnection();
            ps = conn.prepareStatement("update" + TABLE_NAME + " set fcount = ? where fid = ?");
            ps.setObject(1,fruit.getFcount());
            ps.setObject(2,fruit.getFid());
            flag = ps.executeUpdate() > 0;
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            JDBCUtil.closeResource(conn,ps);
        }
        return flag;
    }

    @Override
    public boolean deleteFruit(String name) {
        Connection conn = null;
        PreparedStatement ps = null;
        boolean flag = false;
        try {
            conn = JDBCUtil.getConnection();
            ps = conn.prepareStatement("delete from " + TABLE_NAME + " where fname = ?");
            ps.setObject(1,name);
            flag = ps.executeUpdate() > 0;
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            JDBCUtil.closeResource(conn,ps);
        }
        return flag;
    }
}
