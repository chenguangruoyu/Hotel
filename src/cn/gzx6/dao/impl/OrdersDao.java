package cn.gzx6.dao.impl;

import cn.gzx6.dao.IOrdersDao;
import cn.gzx6.entity.Orders;
import cn.gzx6.utils.JdbcUtils;
import cn.gzx6.utils.PageBean;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import java.util.List;

public class OrdersDao implements IOrdersDao{

    private QueryRunner qr = JdbcUtils.getQueryRunner();
    @Override
    public void add(Orders orders) {
        String sql =" INSERT orders(table_id,orderDate,totalPrice) VALUES(?,?,?)";
        try {
            qr.update(sql,orders.getTable_id(),orders.getOrderDate(),orders.getTotalPrice());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public int getCount(){
        String sql ="select count(*) from orders";
        try {
            Long count = qr.query(sql, new ScalarHandler<Long>());
            return count.intValue();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void update(Orders orders) {
        String sql = "UPDATE orders SET orderStatus =? WHERE id=?";
        try {
            qr.update(sql,orders.getOrderStatus(),orders.getId());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<Orders> query() {
        String sql = "SELECT * FROM orders";
        try {
            return qr.query(sql, new BeanListHandler<Orders>(Orders.class));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void getAll(PageBean<Orders> pb) {

        //查询总记录数
        int totalCount = this.getTotalCount();
        pb.setTotalCount(totalCount);

        // 判断
        if (pb.getCurrentPage() <=0) {
            pb.setCurrentPage(1);					    // 把当前页设置为1
        } else if (pb.getCurrentPage() > pb.getTotalPage()){
            pb.setCurrentPage(pb.getTotalPage());		// 把当前页设置为最大页数
        }

        //获取当前页
        int currentPage = pb.getCurrentPage();
        int index = (currentPage -1 ) * pb.getPageCount();		// 查询的起始行
        int count = pb.getPageCount();							// 查询返回的行数


        //分页查询数据
        String sql = "select * from orders limit ?,?";

        try {
            // 查询当前页数据(一页)
            List<Orders> pageData = qr.query(sql, new BeanListHandler<Orders>(Orders.class), index, count);
            // 设置到pb对象中
            pb.setPageData(pageData);

        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }

    @Override
    public int getTotalCount() {
        String sql = "select count(*) from orders";
        try {
            // 执行查询， 返回结果的第一行的第一列
            Long count = qr.query(sql, new ScalarHandler<Long>());
            return count.intValue();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }


}
