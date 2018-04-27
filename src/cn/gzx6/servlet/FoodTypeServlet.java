package cn.gzx6.servlet;

import cn.gzx6.entity.FoodType;
import cn.gzx6.factory.BeanFactory;
import cn.gzx6.service.IFoodTypeService;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;


public class FoodTypeServlet extends BaseServlet {

    private IFoodTypeService foodTypeService = BeanFactory.getInstance("foodTypeService",IFoodTypeService.class);
    /*// 调用service对象

    // 重定向uri
    private Object uri;

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // 设置编码
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=UTF-8");

        // 获取操作的类型
        String method = request.getParameter("method");
        // 判断
        if ("addFoodType".equals(method)) {
            addFoodType(request, response);
        } else if ("list".equals(method)) {
            list(request, response);
        } else if ("viewUpdate".equals(method)) {
            viewUpdate(request, response);
        } else if ("delete".equals(method)) {
            delete(request, response);
        } else if ("update".equals(method)) {
            update(request, response);
        }
    }*/

    //添加菜系
    public Object addFoodType(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        Object uri = null;
        // 1. 获取请求数据封装
        String foodTypeName = request.getParameter("foodTypeName");
        FoodType ft = new FoodType();
        ft.setTypeName(foodTypeName);
        // 2. 调用service处理业务逻辑
        foodTypeService.save(ft);
        // 3. 跳转
        uri = request.getRequestDispatcher("/foodType?method=list");

        return uri;
    }

    //菜系列表
    public Object list(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        Object uri = null;
        // 调用Service查询所有的类别
        List<FoodType> list = foodTypeService.getAll();
        // 保存
        request.setAttribute("listFoodType", list);
        // 跳转的菜系列表页面
        uri = request.getRequestDispatcher("/sys/type/foodtype_list.jsp");

        return uri;
    }

    //更新页面
    public Object viewUpdate(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Object uri = null;
        // 1. 获取请求id
        String id = request.getParameter("id");
        // 2. 根据id查询对象
        FoodType ft = foodTypeService.findById(Integer.parseInt(id));
        // 3. 保存
        request.setAttribute("foodType", ft);
        // 4. 跳转
        uri = request.getRequestDispatcher("/sys/type/foodtype_update.jsp");
        return uri;
    }

    //删除
    public Object delete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Object uri = null;
        // 1. 获取请求id
        String id = request.getParameter("id");
        // 2. 调用Service
        foodTypeService.delete(Integer.parseInt(id));
        // 3. 跳转
        uri = "/foodType?method=list";
        return uri;
    }

    //更新
    public Object update(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Object uri = null;
        // 1. 获取请求数据封装
        int id = Integer.parseInt(request.getParameter("id"));
        String name = request.getParameter("foodTypeName");
        FoodType foodType = new FoodType();
        foodType.setId(id);
        foodType.setTypeName(name);
        // 2. 调用Service更新
        foodTypeService.update(foodType);
        // 3. 跳转
        // list(request,response);
        uri = "/foodType?method=list";
        return uri;
    }

}
