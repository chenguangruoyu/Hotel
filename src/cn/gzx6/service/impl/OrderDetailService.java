package cn.gzx6.service.impl;

import cn.gzx6.dao.IOrderDetailDao;
import cn.gzx6.entity.OrderDetail;
import cn.gzx6.factory.BeanFactory;
import cn.gzx6.service.IOrderDetailService;

import java.util.List;

public class OrderDetailService implements IOrderDetailService{


    private IOrderDetailDao dao = BeanFactory.getInstance("orderDetailDao", IOrderDetailDao.class);

    @Override
    public void add(OrderDetail od) {
        dao.add(od);
    }

    @Override
    public List<OrderDetail> query() {
        return dao.query();
    }
    @Override
    public List<OrderDetail> findByOrderId(int id) {
        return dao.findByOrderid(id);
    }

}
