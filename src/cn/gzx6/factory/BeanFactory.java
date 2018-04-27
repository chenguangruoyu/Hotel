package cn.gzx6.factory;

import java.util.ResourceBundle;

import static java.lang.Class.forName;


/**
 * 工厂: 创建dao 或service实例
 */
public class BeanFactory {

    //加载配置文件
    private static ResourceBundle bundle;
    static {
        bundle = ResourceBundle.getBundle("instance");
    }

    /**
     * 根据制定的key 读取全路径
     *
     */
    public static <T> T getInstance(String key,Class<T> clazz) {
        String className = bundle.getString(key);
        try {
            return (T) forName(className).newInstance();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
