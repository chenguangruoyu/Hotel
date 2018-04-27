package cn.gzx6.dao.impl;

import cn.gzx6.dao.IFoodDao;
import cn.gzx6.entity.Food;
import cn.gzx6.utils.Condition;
import cn.gzx6.utils.JdbcUtils;
import cn.gzx6.utils.PageBean;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class FoodDao implements IFoodDao{

    private QueryRunner queryRunner = JdbcUtils.getQueryRunner();

    @Override
    public void add(Food food) {
        String sql =" INSERT food(foodName,foodType_id,price,mprice,remark,img) VALUES(?,?,?,?,?,?);";
        try{
            queryRunner.update(sql,food.getFoodName(),food.getFoodType_id(),
                    food.getPrice(),food.getMprice(),food.getRemark(),food.getImg());
        }catch (Exception e){
            throw new RuntimeException(e);
        }
    }

    @Override
    public void delete(int id) {
        try {
            String sql ="DELETE FROM food WHERE id=?";
            queryRunner.update(sql,id);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void update(Food food) {
        try {
            String sql ="UPDATE food SET foodName=?,foodType_id=?,price=?,mprice=?,remark=?,img=? WHERE id =?";
            queryRunner.update(sql,food.getFoodName(),food.getFoodType_id(),food.getPrice(),
                    food.getMprice(),food.getRemark(),food.getImg(),food.getId());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<Food> query() {
        try {
            String sql ="SELECT * FROM food";
            return  queryRunner.query(sql,new BeanListHandler<Food>(Food.class));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<Food> query(String keyword) {
        try {
            String sql ="SELECT * FROM food WHERE foodName LIKE ?";
            return queryRunner.query(sql,new BeanListHandler<Food>(Food.class) , "%"+keyword+"%");
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<Food> findByType(int type) {
        try {
            //根据菜系找到食物
            String sql ="SELECT * FROM food WHERE foodType_id =?";
            return queryRunner.query(sql, new BeanListHandler<Food>(Food.class), type);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void getAll(PageBean<Food> pb) {

        int totalCount = this.getTotalCount(pb);
        pb.setTotalCount(totalCount);
        //存储查询条件对应的值
        List<Object> list = new ArrayList<Object>();
        if(pb.getCurrentPage()<=0){
            pb.setCurrentPage(1);
        }else if(pb.getCurrentPage()>pb.getTotalPage()){
            pb.setCurrentPage(pb.getTotalPage());
        }

        //起始行
        int index = (pb.getCurrentPage() - 1) * pb.getPageCount();
        //返回记录行
        int count = pb.getPageCount();

        Condition condition = pb.getCondition();

        StringBuffer sb = new StringBuffer();
        sb.append("select");
        sb.append("		f.id,");
        sb.append("		f.foodName,");
        sb.append("		f.price,");
        sb.append("		f.mprice,");
        sb.append("		f.remark,");
        sb.append("		f.img,");
        sb.append("		f.foodType_id,");
        sb.append("		t.typeName ");
        sb.append("from ");
        sb.append("		food f, ");
        sb.append("		foodtype t ");
        sb.append("where 1=1");
        sb.append("		and f.foodType_id=t.id ");

        if(condition!=null){

            String foodName = condition.getFoodName();
            if(foodName!=null && !foodName.isEmpty()){
                sb.append(" and f.foodName like ? ");
                list.add("%"+foodName+"%");
            }

            int typeId = condition.getFoodTypeId();
            if(typeId>0){
                sb.append(" and f.foodType_id=? ");
                list.add(typeId);
            }
        }
        sb.append(" LIMIT  ?,? ");

        list.add(index);
        list.add(count);

        try {
            if(index>=0){
                List<Food> pageData = queryRunner.query(sb.toString(),new BeanListHandler<Food>(Food.class),list.toArray());
                pb.setPageData(pageData);
            }

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public int getTotalCount(PageBean<Food> pb) {

        //获取条件对象
        Condition condition = pb.getCondition();

        StringBuffer sb = new StringBuffer();
        sb.append("select");
        sb.append("		count(*) ");
        sb.append("from ");
        sb.append("		food f, ");
        sb.append("		foodtype t ");
        sb.append("where 1=1");
        sb.append("		and f.foodType_id=t.id ");
        // 存储查询条件对应的值
        List<Object> list = new ArrayList<Object>();
        if(condition!=null){
            //条件、id
            int typeId = condition.getFoodTypeId();
            if (typeId > 0) {
                sb.append("	and f.foodType_id=?");
                list.add(typeId);  // 组装条件值
            }

            //条件、foodName
            String foodName = condition.getFoodName();
            if (foodName != null && !"".equals(foodName.trim())) {
                sb.append("  and f.foodName like ?");
                list.add(foodName);    // 组装条件值
            }
        }

        try {
            // 查询
            Long num = queryRunner.query(sb.toString(), new ScalarHandler<Long>(),list.toArray());
            return num.intValue();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Food findById(int id) {
//        StringBuffer sb = new StringBuffer();
//        sb.append("select");
//        sb.append("   f.id,");
//        sb.append("   f.foodName,");
//        sb.append("   f.price,");
//        sb.append("   f.mprice,");
//        sb.append("   f.remark,");
//        sb.append("   f.img,");
//        sb.append("   f.foodType_id,");
//        sb.append("   f.typeName ");
//        sb.append("from ");
//        sb.append("  food f,");
//        sb.append("  foodtype t ");
//        sb.append("where 1=1");
//        sb.append("  and f.foodType_id=t.id");
        try {
            String sql ="SELECT * FROM food where id =?";
            return queryRunner.query(sql,new BeanHandler<Food>(Food.class), id);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
//        try {
//            return queryRunner.query(sb.toString(),new BeanHandler<Food>(Food.class));
//        }catch (Exception e){
//            throw new RuntimeException(e);
//        }
    }
}
