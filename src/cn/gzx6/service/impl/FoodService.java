package cn.gzx6.service.impl;

import cn.gzx6.dao.IFoodDao;
import cn.gzx6.entity.Food;
import cn.gzx6.factory.BeanFactory;
import cn.gzx6.service.IFoodService;
import cn.gzx6.utils.PageBean;

import java.util.List;

public class FoodService implements IFoodService{

    private IFoodDao foodDao = BeanFactory.getInstance("foodDao", IFoodDao.class);

    @Override
    public void add(Food food) {
        foodDao.add(food);
    }

    @Override
    public void delete(int id) {
        foodDao.delete(id);
    }

    @Override
    public void update(Food food) {
        foodDao.update(food);
    }

    @Override
    public List<Food> query() {
        return foodDao.query();
    }

    @Override
    public List<Food> query(String keyword) {
        return foodDao.query(keyword);
    }

    @Override
    public List<Food> findByType(int type) {
        return foodDao.findByType(type);
    }

    @Override
    public Food findById(int id) {
        try {
            return foodDao.findById(id);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void getAll(PageBean<Food> pb) {
        try {
            foodDao.getAll(pb);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }
}
