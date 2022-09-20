package com.lyy.fruit.dao.impl;

import com.lyy.fruit.dao.FruitDao;
import com.lyy.fruit.dao.base.BaseDao;
import com.lyy.fruit.pojo.Fruit;
import java.util.List;

/**
 * @ClassName FruitDaoImpl
 * @Description 接口实现类
 * @Author Ice
 * @Date 2022/9/20 11:00
 * @Version 1.0
 **/
public class FruitDaoImpl extends BaseDao<Fruit> implements FruitDao {

    private static final String TABLE_NAME = "t_fruit"; //表名称

    @Override
    public List<Fruit> getFruitList() {
        String sql = "select fid,fname,price,fcount,remark from " + TABLE_NAME;
        return super.queryList(sql);
    }

    @Override
    public Fruit getFruitByName(String name) {
        String sql = "select fid,fname,price,fcount,remark from " + TABLE_NAME + " where fname = ?";
        return super.queryOne(sql,name);
    }

    @Override
    public boolean addFruit(Fruit fruit) {
        String sql = "insert into " + TABLE_NAME + "(fname,price,fcount,remark) values(?,?,?,?)";
        return super.executeUpdate(sql,fruit.getFname(),fruit.getPrice(),fruit.getFcount(),fruit.getRemark()) > 0;
    }

    @Override
    public boolean updateFruit(Fruit fruit) {
        String sql = "update " + TABLE_NAME + " set fcount = ? where fid = ?";
        return super.executeUpdate(sql,fruit.getFcount(),fruit.getFid()) > 0;
    }

    @Override
    public boolean deleteFruit(String name) {
        String sql = "delete from " + TABLE_NAME + " where fname = ?";
        return super.executeUpdate(sql,name) > 0;
    }
}
