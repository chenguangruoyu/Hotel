package cn.gzx6.servlet;

import cn.gzx6.factory.BeanFactory;
import cn.gzx6.service.*;
import cn.gzx6.utils.WebUtils;
import com.sun.istack.internal.NotNull;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.Method;

public class BaseServlet extends HttpServlet {
    // 创建Service


    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        // 设置编码
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=UTF-8");
        // (保存跳转的资源)  方法返回值
        Object returnValue = null;

        // 获取操作类型;  【操作类型的值，必须对应servlet中的方法名】
        String methodName = request.getParameter("method");  // listTable

        try {
            // 获取当前运行类的字节码
            Class clazz = this.getClass();
            // 获取当前执行的方法的Method类型
            Method method = clazz.getDeclaredMethod(methodName, HttpServletRequest.class,HttpServletResponse.class);
            // 执行方法
            returnValue = method.invoke(this, request,response);
        } catch (Exception e) {
            e.printStackTrace();
            returnValue = "/error/error.jsp";
        }

        // 跳转
        WebUtils.goTo(request, response, returnValue);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        this.doGet(req, resp);
    }
}
