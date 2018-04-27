package cn.gzx6.service;

import cn.gzx6.entity.OrderDetail;

import java.util.List;

public interface IOrderDetailService {

    void add(OrderDetail od);

    List<OrderDetail> query();

    List<OrderDetail> findByOrderId(int id);

}
