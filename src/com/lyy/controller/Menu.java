package com.lyy.controller;

import com.lyy.fruit.dao.FruitDao;
import com.lyy.fruit.dao.impl.FruitDaoImpl;
import com.lyy.fruit.pojo.Fruit;

import java.util.List;
import java.util.Scanner;

/**
 * @ClassName Menu
 * @Description 菜单程序
 * @Author Ice
 * @Date 2022/9/20 14:09
 * @Version 1.0
 **/
public class Menu {

    private Scanner sc = new Scanner(System.in);
    private FruitDao fruitDao = new FruitDaoImpl();

    /*
    * 主菜单抬头
    */
    public int showMainMenu(){
        System.out.println("=================欢迎使用水果库存系统=====================");
        System.out.println("1.查看水果库存列表");
        System.out.println("2.添加水果库存信息");
        System.out.println("3.查看特定水果库存信息");
        System.out.println("4.水果下架");
        System.out.println("5.退出");
        System.out.println("======================================================");
        System.out.println("请选择：");
        int num = sc.nextInt();
        return num;
    }

    /*
    * 1.查看水果库存列表
    */
    public void showFruitList(){
        List<Fruit> fruitList = fruitDao.getFruitList();
        System.out.println("------------------------------------------------------");
        if (fruitList == null || fruitList.size() <= 0){
            System.out.println("对不起，库存为空！");
        }else {
            System.out.println("FID" + "\t\t" + "名称" + "\t\t" + "单价" + "\t\t" + "库存" + "\t\t" + "备注");
            for (Fruit fruit : fruitList){
                System.out.println(fruit.toString());
            }
        }
    }
    /*
     * 2.添加水果库存信息
     */
    public void addFruitCount(){
        System.out.println("请输入水果名称：");
        String name = sc.next();
        Fruit fruit = fruitDao.getFruitByName(name);
        if (fruit == null){
            System.out.println("请输入水果单价：");
            int price = sc.nextInt();
            System.out.println("请输入水果库存量：");
            int count = sc.nextInt();
            System.out.println("请输入水果备注：");
            String remark = sc.next();
            fruit = new Fruit(0,name,price,count,remark);
            if (fruitDao.addFruit(fruit)){
                System.out.println("添加成功！");
            }
        }else {
            System.out.println("请输入追加的库存量：");
            int count = sc.nextInt();
            fruit.setFcount(fruit.getFcount() + count);
            if (fruitDao.updateFruit(fruit)){
                System.out.println("更新库存成功！");
            }
        }
    }

    /*
     * 3.查看特定水果库存信息
     */
    public void showFruitByName(){
        System.out.println("请输入水果名称：");
        String name = sc.next();
        Fruit fruit = fruitDao.getFruitByName(name);
        if (fruit == null){
            System.out.println("对不起，没有找到指定的水果库存记录！");
        }else {
            System.out.println("FID" + "\t\t" + "名称" + "\t\t" + "单价" + "\t\t" + "库存" + "\t\t" + "备注");
            System.out.println(fruit.toString());
        }
    }

    /*
     * 4.水果下架
     */
    public void deleteFruit(){
        System.out.println("请输入水果名称：");
        String name = sc.next();
        Fruit fruit = fruitDao.getFruitByName(name);
        if (fruit == null){
            System.out.println("对不起，没有找到指定的水果库存记录！");
        }else {
            System.out.println("是否确认下架？(Y/N)：");
            String yn = sc.next();
            if ("Y".equalsIgnoreCase(yn)){
                fruitDao.deleteFruit(name);
                System.out.println("下架成功");
            }
        }
    }

    public boolean exit(){
        System.out.println("是否确认退出？(Y/N)：");
        String yn = sc.next();
        return !"Y".equalsIgnoreCase(yn);
    }
}
