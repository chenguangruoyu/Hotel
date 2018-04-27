package cn.gzx6.service;

import cn.gzx6.entity.Orders;
import cn.gzx6.utils.PageBean;

import java.util.List;

public interface IOrdersService {

    void update(Orders orders);

    List<Orders> query();

    void add(Orders orders);

    int getCount();

    public void getAll(PageBean<Orders> pb);

}
