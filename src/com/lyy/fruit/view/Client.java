package com.lyy.fruit.view;

import com.lyy.fruit.controller.Menu;

/**
 * @ClassName Client
 * @Description 启动视图
 * @Author Ice
 * @Date 2022/9/20 15:03
 * @Version 1.0
 **/
public class Client {
    public static void main(String[] args) {
        Menu menu = new Menu();
        boolean flag = true;
        while (flag){
            int num = menu.showMainMenu();
            switch (num){
                case 1 : menu.showFruitList();break;
                case 2 : menu.addFruitCount();break;
                case 3 : menu.showFruitByName();break;
                case 4 : menu.deleteFruit();break;
                case 5 : flag = menu.exit(); break;
                default:
                    System.out.println("输入有误！"); break;
            }
        }
        System.out.println("Good Bye! Peace And Love");
    }
}
