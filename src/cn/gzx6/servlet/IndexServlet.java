package cn.gzx6.servlet;

import cn.gzx6.entity.Food;
import cn.gzx6.entity.FoodType;
import cn.gzx6.factory.BeanFactory;
import cn.gzx6.service.*;
import cn.gzx6.utils.Condition;
import cn.gzx6.utils.PageBean;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

public class IndexServlet extends BaseServlet {
    protected IDinnerTableService tableService = BeanFactory.getInstance("dinnerTableService", IDinnerTableService.class);
    protected IFoodTypeService foodTypeService = BeanFactory.getInstance("foodTypeService",IFoodTypeService.class);
    protected IFoodService foodService = BeanFactory.getInstance("foodService",IFoodService.class);

    public Object getMenu(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Object uri = null;
        HttpSession session = request.getSession();// 用于存储订单信息
        // 获取session里的值
        Object obj = session.getAttribute("table_id");

        String table_id = request.getParameter("table_id");// 桌的id
        if (table_id != null) {
            tableService.changeState(Integer.parseInt(table_id));
            if (obj == null) {
                session.setAttribute("table_id", table_id);// 存放桌id以备订单用
            }
        }

        // 查询菜系信息
        List<FoodType> foodtypes = foodTypeService.query();
        request.setAttribute("foodtypes", foodtypes);

        // 获取菜单页面信息
        PageBean<Food> pb = new PageBean<Food>();

        Condition con = new Condition();
        // 获取页面得到的参数
        String foodtype = request.getParameter("foodtype");
        String foodName = request.getParameter("foodName");
        if (foodtype != null && !foodtype.isEmpty()) {
            con.setFoodTypeId(Integer.parseInt(foodtype));
            pb.setCondition(con);
        }
        if (foodName != null && !foodName.isEmpty()) {
            con.setFoodName(foodName);
            pb.setCondition(con);
        }

        pb.setPageCount(6);
        String curPage = request.getParameter("currentPage");// 获取当前页
        if (curPage == null || curPage.isEmpty()) {
            pb.setCurrentPage(1);
        }
        if (curPage != null && !curPage.isEmpty()) {
            int currentPage = Integer.parseInt(curPage);
            pb.setCurrentPage(currentPage);
        }

        foodService.getAll(pb);

        request.setAttribute("pageBean", pb);
        // 跳转
        uri = request.getRequestDispatcher("/app/detail/caidan.jsp");

        return uri;
    }

    public Object searchFood(HttpServletRequest request,
                             HttpServletResponse response) throws ServletException, IOException {
        Object uri=null;

        PageBean<Food> pb = new PageBean<Food>();
        Condition condition = new Condition();

        String keyword = request.getParameter("keyword");//设置关键词
        if(keyword!=null && !keyword.isEmpty()){
            condition.setFoodName(keyword);
        }
        if(condition!=null){
            pb.setCondition(condition);
        }

        pb.setCondition(condition);

        foodService.getAll(pb);

        request.setAttribute("pageBean", pb);
        // 跳转
        uri = request.getRequestDispatcher("/app/detail/caidan.jsp");

        return uri;
    }


    public Object getFoodDetail(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Object uri =null;
        String id = request.getParameter("food");//获取菜品id
        Food food = foodService.findById(Integer.parseInt(id));
        List<FoodType> foodtypes = foodTypeService.query();
        request.setAttribute("food", food);
        request.setAttribute("foodtypes", foodtypes);
        uri = request.getRequestDispatcher("/app/detail/caixiangxi.jsp");

        return uri;
    }

}
