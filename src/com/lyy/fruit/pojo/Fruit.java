package com.lyy.fruit.pojo;

/**
 * @ClassName Fruit
 * @Description 水果实体类
 * @Author Ice
 * @Date 2022/9/20 10:32
 * @Version 1.0
 **/
public class Fruit {

    private Integer fid;  //id
    private String fname; //名称
    private Integer price; //价格
    private Integer fcount; //库存
    private String remark; //评论

    public Fruit(){}

    public Fruit(Integer fid, String fname, Integer price, Integer fcount, String remark) {
        this.fid = fid;
        this.fname = fname;
        this.price = price;
        this.fcount = fcount;
        this.remark = remark;
    }

    public Integer getFid() {
        return fid;
    }

    public void setFid(Integer fid) {
        this.fid = fid;
    }

    public String getFname() {
        return fname;
    }

    public void setFname(String fname) {
        this.fname = fname;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public Integer getFcount() {
        return fcount;
    }

    public void setFcount(Integer fcount) {
        this.fcount = fcount;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    @Override
    public String toString() {
        return fid + "\t\t" + fname + "\t\t" + price + "\t\t" + fcount + "\t\t" + remark;
    }
}
