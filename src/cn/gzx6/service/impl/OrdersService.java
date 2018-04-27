package cn.gzx6.service.impl;

import cn.gzx6.dao.IOrdersDao;
import cn.gzx6.entity.Orders;
import cn.gzx6.factory.BeanFactory;
import cn.gzx6.service.IOrdersService;
import cn.gzx6.utils.PageBean;

import java.util.List;

public class OrdersService implements IOrdersService{

    private IOrdersDao dao = BeanFactory.getInstance("ordersDao", IOrdersDao.class);

    @Override
    public void update(Orders orders) {
        dao.update(orders);
    }

    @Override
    public List<Orders> query() {
        return dao.query();
    }

    @Override
    public void add(Orders orders) {
        dao.add(orders);
    }

    @Override
    public int getCount() {
        return dao.getCount();
    }

    @Override
    public void getAll(PageBean<Orders> pb) {
        try {
            dao.getAll(pb);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}
