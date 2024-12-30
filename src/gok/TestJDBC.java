package gok;

import com.alibaba.fastjson.JSON;
import gok.model.User;
import gok.util.JDBCUtil;

import java.util.ArrayList;
import java.util.List;

public class TestJDBC {
    public static void main(String[] args) throws Exception {
        /*
        * 查询
        * */
//        User user = new User();
//        user.setUsername("zhangsan");
//        List<Object> objectList = JDBCUtil.queryAll(user);
//        System.out.println(JSON.toJSONString(objectList));

        /*
        * 修改
        * */
//        User user = new User();
//        user.setUsername("zhangsan");
//        user.setPassword("123456");
//        int update = JDBCUtil.upAll(user);
//        System.out.println(update);

        /*
        * 添加
        * */
//        User user = new User();
//        user.setUsername("赵六");
//        user.setPassword("123456");
//        int insert = JDBCUtil.addAll(user);
//        System.out.println(insert);

        /*
         * 删除
         * */
//        ArrayList<Integer> ids = new ArrayList<>();
//        ids.add(5);
//        User user = new User();
//        int a = JDBCUtil.delAll(ids, user);
//        System.out.println(a);

    }
}
