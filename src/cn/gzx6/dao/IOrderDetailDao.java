package cn.gzx6.dao;

import cn.gzx6.entity.OrderDetail;

import java.util.List;

public interface IOrderDetailDao {

    void add(OrderDetail od);

    List<OrderDetail> query();

    List<OrderDetail> findByOrderid(int id);
}
