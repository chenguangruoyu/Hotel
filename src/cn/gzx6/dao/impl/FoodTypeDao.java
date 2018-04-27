package cn.gzx6.dao.impl;

import cn.gzx6.dao.IFoodTypeDao;
import cn.gzx6.entity.FoodType;
import cn.gzx6.utils.JdbcUtils;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import java.util.List;

public class FoodTypeDao implements IFoodTypeDao{

    private QueryRunner qr = JdbcUtils.getQueryRunner();

    @Override
    public void save(FoodType foodType) {
        String sql = "insert into foodType(typeName) values(?)";
        try{
            qr.update(sql,foodType.getTypeName());
        }catch (Exception e){
            throw new RuntimeException(e);
        }
    }

    @Override
    public void update(FoodType foodType) {
        String sql = "update foodType set typeName=? where id=?";
        try{
            qr.update(sql,foodType.getTypeName(),foodType.getId());
        }catch (Exception e){
            throw new RuntimeException(e);
        }
    }

    @Override
    public void delete(int id) {
        String sql = "delete from foodType where id=?";
        try{
            qr.update(sql,id);
        }catch (Exception e){
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<FoodType> getAll() {
        String sql = "select * from foodType";
        try{
            return qr.query(sql,new BeanListHandler<FoodType>(FoodType.class));
        }catch (Exception e){
            throw new RuntimeException(e);
        }

    }

    @Override
    public List<FoodType> getAll(String typeName) {
        String sql = "select * from foodType where typeName like ?";
        try{
            return qr.query(sql,new BeanListHandler<FoodType>(FoodType.class),"%" + typeName +"%");
        }catch (Exception e){
            throw new RuntimeException(e);
        }

    }

    @Override
    public FoodType findById(int id) {
        String sql = "select * from foodType where id=?";
        try{
            return qr.query(sql,new BeanHandler<FoodType>(FoodType.class),id);
        }catch (Exception e){
            throw new RuntimeException(e);
        }

    }

    @Override
    public List<FoodType> query(String keyword) {
        try {
            String sql ="SELECT * FROM foodtype WHERE typeName LIKE ?";
            return qr.query(sql,new BeanListHandler<FoodType>(FoodType.class) , "%"+keyword+"%");
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<FoodType> query() {
        try {
            String sql ="SELECT * FROM foodtype";
            return  qr.query(sql,new BeanListHandler<FoodType>(FoodType.class));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
