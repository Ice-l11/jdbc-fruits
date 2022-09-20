package com.lyy.fruit.dao;

import com.lyy.fruit.pojo.Fruit;

import java.util.List;

/**
 * @ClassName FruitDao
 * @Description FruitDao
 * @Author Ice
 * @Date 2022/9/20 10:44
 * @Version 1.0
 **/
public interface FruitDao {

    /*
    * 查询所有水果库存信息
    */
    List<Fruit> getFruitList();

    /*
    * 根据水果名称查询
    */
    Fruit getFruitByName(String name);

    /*
    * 插入一条水果记录
    */
    boolean addFruit(Fruit fruit);

    /*
    * 更新水果库存
    */
    boolean updateFruit(Fruit fruit);

    /*
    * 根据名称删除
    */
    boolean deleteFruit(String name);
}
