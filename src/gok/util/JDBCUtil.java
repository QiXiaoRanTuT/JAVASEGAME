package gok.util;


import com.alibaba.fastjson.JSON;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

/**
 * @作者: liwang
 * @时间: 2020/8/10
 */
public class JDBCUtil {
    public static Connection connection;
    public static PreparedStatement ps;
    public static ResultSet rs;
    static String driver = "com.mysql.jdbc.Driver";//驱动位置
    static String url = "jdbc:mysql://127.0.0.1:3306/game?useUnicode=true&characterEncoding=utf-8&useSSL=false&serverTimezone=UTC";//连接字符串
    static String name = "root";//用户名
    static String password = "root";//密码

    /*
   查询
    */
    public static List<Object> queryAll(Object obj, String... others) throws Exception {
        String tableName=obj.getClass().getSimpleName();//自动获取表名
        String sql="select * from "+tableName+" where 1=1 ";
        //传进来数据
        ResultSet rs=JDBCUtil.retrieveTable(JDBCUtil.getConnection(),sql,obj,others);
        //返回数据
        List list=JDBCUtil.getRetrieveTable(rs,sql,obj);
        return list;
    }
    /*
    添加
     */
    public static int addAll(Object obj) throws Exception {
        String tableName=obj.getClass().getSimpleName();

        List list=new ArrayList();//动态拿到字段名
        List list2=new ArrayList();//动态添加?
        Field[] fields = obj.getClass().getDeclaredFields();//获取所有的属性
        int sum=0;
        for (Field f : fields) {
            Object value=invokeGet(obj, f.getName());
            if (value != null&value != "") {
                list.add(f.getName());
                list2.add("?");
                sum++;
            }
        }
        String testsql="insert into "+tableName+" ("+list.toString().replace("[","").replace("]","")+") values("+list2.toString().replace("[","").replace("]","")+")";
        Object[] objects=new Object[sum];
        int i=0;
        for (Field f : fields) {
            Object value=invokeGet(obj, f.getName());
            if (value != null&value != "") {
                objects[i]=value;
                i++;
            }
        }
        int a=JDBCUtil.CUDTable(JDBCUtil.getConnection(),testsql,objects);
        return a;
    }
    /*
    删除
     */
    public static int delAll(List ids,Object o) throws Exception {
        String tableName=o.getClass().getSimpleName();
        List list=new ArrayList();
        for (Object a:ids) {
            list.add("?");
        }
        String s=list.toString().replace("[","").replace("]","");
        String id=tableName.substring(0, 1).toLowerCase() + tableName.substring(1)+"Id";
        String sql="delete from "+tableName+" where "+id+" in ("+s+")";
        Object[] objects=new Object[ids.size()];
        for (int i=0;i<ids.size();i++) {
            objects[i]=ids.get(i);
        }
        int a=JDBCUtil.CUDTable(JDBCUtil.getConnection(),sql,objects);
        return a;
    }
    /*
    修改
     */
    public static int upAll(Object o) throws Exception {
        String tableName=o.getClass().getSimpleName();
        String id=tableName.substring(0, 1).toLowerCase() + tableName.substring(1)+"Id";
        Field[] fields = o.getClass().getDeclaredFields();
        List list=new ArrayList();
        for (Field f:fields) {
            if (!f.getName().equals(id)){
                Object value=invokeGet(o,f.getName());
//                System.out.println(value);
                if (value!=null&value!=""){
                    list.add(f.getName()+"=?");
                }
            }
        }
        String s=list.toString().replace("[","").replace("]","");
        String sql="update "+tableName+" set "+s+" where "+id+"=?";
        Object[] objects=new Object[list.size()+1];
        int i=0;
        for (Field f:fields) {
            Object value=invokeGet(o,f.getName());

            if (i==0){
                objects[list.size()]=value;
            }
            if (!f.getName().equals(id)){
                if (value!=null&value!="") {
                    //System.out.println("value: "+value);
                    objects[i]=value;
                    i++;
                }
            }
        }
        int a=JDBCUtil.CUDTable(JDBCUtil.getConnection(),sql,objects);
        return a;
    }
    /*
    获得连接
     */
    public static Connection getConnection() throws Exception {
        Class.forName(driver);//1.加载驱动
        connection = DriverManager.getConnection(url, name, password);//2.获得连接
        System.out.println("连接成功" + connection);
        return connection;
    }
    /*
    关闭连接
     */
    public static void closeConnection(Connection connection, PreparedStatement ps, ResultSet rs) throws Exception {
        if (rs != null) {
            rs.close();
            System.out.println("成功关闭结果集");
        }
        if (ps != null) {
            ps.close();
            System.out.println("成功关闭预编译接口");
        }
        if (connection != null) {
            connection.close();
            System.out.println("成功关闭连接");
        }
    }
    //增删改数据表
    public static int CUDTable(Connection connection, String sql, Object... obj) throws Exception {
        //预编译sql语句
        PreparedStatement ps = connection.prepareStatement(sql);
        //填充占位符
        for (int i = 1; i <= obj.length; i++) {
            ps.setObject(i, obj[i - 1]);
        }
        System.out.println("obj:  "+ JSON.toJSONString(obj));
        System.out.println("sql:  "+sql);
        //执行sql语句
        int a = ps.executeUpdate();

        ResultSet rs = null;
        JDBCUtil.closeConnection(connection, ps, rs);
        return a;
    }
    //查询数据表
    public static ResultSet retrieveTable(Connection connection, String sql, Object obj,String... others) throws Exception {
        Field[] fields = obj.getClass().getDeclaredFields();//获取所有的属性
//        System.out.println("ok");

        boolean ObjIsNull=true;//判断传来的obj是否为空
        for (Field f : fields) {
            Object value=invokeGet(obj, f.getName());
//            System.out.println(value);
            if (value != null&value != "") {
                ObjIsNull=false;
                if (f.getName().endsWith("Name")){
                    sql+=" and "+f.getName()+" like ?";
                    ps = connection.prepareStatement(sql);
                }else {
                    sql += " and " + f.getName() + "=?";
                    ps = connection.prepareStatement(sql);
                }
            }
        }
        if (ObjIsNull){
            ps = connection.prepareStatement(sql);
        }
//        System.out.println(sql);
//        if (others!=null|!others.equals("")){
//            System.out.println(others[0]);
//            sql+=others[0];
//            ps = connection.prepareStatement(sql);
//        }
        for (int i=0;i<others.length;i++) {
            sql+=others[i];
            ps = connection.prepareStatement(sql);
        }
        System.out.println("sql: "+sql);
        int sum = 1;
        //动态填充占位符
        for (Field f : fields) {
            if (invokeGet(obj, f.getName()) != null&invokeGet(obj, f.getName()) != "") {
                Object value=invokeGet(obj, f.getName());//获得值
                if (f.getName().endsWith("Name")){
                    ps.setObject(sum++, "%"+value+"%");
                }else {
                    ps.setObject(sum++, value);
                }
            }
        }
        //执行sql语句
        rs = ps.executeQuery();
        return rs;
    }
    /*
    自动获取查询信息
     */
    public static List<Object> getRetrieveTable(ResultSet rs, String sql, Object o) throws Exception {
        List<Object> objects = new ArrayList<>();
        while (rs.next()) {
            Field[] fields = o.getClass().getDeclaredFields();
            Object o2 = o.getClass().newInstance();//实例化对象
            for (Field f : fields) {
                invokeSet(o2, f.getName(), rs.getObject(f.getName()));
            }
            objects.add(o2);
        }
        closeConnection(connection,ps,rs);
        return objects;
    }
    /*
    获取set方法
     */
    public static Method getSetMethod(Class<?> objectClass, String fieldName) {
        try {
            Class<?>[] parameterTypes = new Class<?>[1];//获取当前类
            Field field = objectClass.getDeclaredField(fieldName);//获取当前类的属性
            parameterTypes[0] = field.getType();//返回类类型的接口
            /*
            通过属性拼接出set方法
             */
            StringBuilder sb = new StringBuilder();
            sb.append("set");
            sb.append(fieldName.substring(0, 1).toUpperCase(Locale.ROOT));
            sb.append(fieldName.substring(1));
            return objectClass.getMethod(sb.toString(), parameterTypes);
        } catch (NoSuchFieldException | NoSuchMethodException e) {
            throw new RuntimeException("Reflect error!");
        }
    }
    /**
     * 执行set方法
     */
    public static void invokeSet(Object obj, String fieldName, Object value) {
        Method method = getSetMethod(obj.getClass(), fieldName);//调用getSetMethod方法
        try {
            method.invoke(obj, value);//扩展set方法
        } catch (IllegalAccessException | InvocationTargetException e) {
            throw new RuntimeException("Reflect error!");
        }
    }

    /*
    获取get方法
     */
    public static Method getGetMethod(Class<?> objectClass, String fieldName) {
        StringBuilder sb = new StringBuilder();
        sb.append("get");
        sb.append(fieldName.substring(0, 1).toUpperCase(Locale.ROOT));
        sb.append(fieldName.substring(1));

        try {
            return objectClass.getMethod(sb.toString());
        } catch (NoSuchMethodException e) {
            throw new RuntimeException("Reflect error!");
        }
    }
    /*
    执行get方法
     */
    public static Object invokeGet(Object obj, String fieldName) {
        Method method = getGetMethod(obj.getClass(), fieldName);
        try {
            return method.invoke(obj);
        } catch (IllegalAccessException | InvocationTargetException e) {
            throw new RuntimeException("Reflect error!");
        }
    }


}
