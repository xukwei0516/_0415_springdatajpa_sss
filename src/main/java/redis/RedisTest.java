package redis;

import ch.qos.logback.core.net.SyslogOutputStream;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import model.User;
import redis.clients.jedis.Jedis;

import java.util.Arrays;
import java.util.List;

public class RedisTest {

    public static void main(String[] args) {
        Jedis redis = new Jedis("127.0.0.1", 6379);
        redis.auth("1234");
        String mess = redis.ping();
        System.out.println(mess);
        //字符串
        redis.set("mess","hello redis");
        System.out.println(redis.get("mess"));

        //字节类型
        //redis.set([], [])

        //对像数据(转化为json)
        User user = new User();
        user.setId(1);
        user.setUsername("admin");
        user.setPassword("1234");

        //存
        String json = JSON.toJSONString(user);
        System.out.println(json);
        redis.set("user", json);
        //取
        String json2 = redis.get("user");

        //User user2 = JSON.parseObject(json2).toJavaObject(User.class);
        User user2 = JSONObject.parseObject(json2, User.class);

        //System.out.println(user2.getUsername()+","+user2.getPassword());

        //使用工具类
        RedisHelper.setObject("myuser", user);
        User user3 = RedisHelper.getObject("myuser", User.class);
        System.out.println(user3.getUsername()+","+user3.getPassword());

        List<User> list = Arrays.asList(user);
        RedisHelper.setObject("list", list);
        List<User> list2 = RedisHelper.getList("list", User.class);
        System.out.println(list2);








    }
}
