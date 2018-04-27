package cn.gzx6.dao;

import cn.gzx6.entity.Orders;
import cn.gzx6.utils.PageBean;

import java.util.List;

public interface IOrdersDao {

    void update(Orders orders);

    List<Orders> query();

    void add(Orders orders);

    int getCount();

    void getAll(PageBean<Orders> pb);

    int getTotalCount();
}
