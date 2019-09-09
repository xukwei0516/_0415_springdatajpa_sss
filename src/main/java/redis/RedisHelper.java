package redis;

import com.alibaba.fastjson.JSONObject;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

import java.util.List;

public class RedisHelper {

    private static JedisPool pool;

    static{
        JedisPoolConfig config = new JedisPoolConfig();
        //初始化参数
        config.setMaxIdle(200);
        config.setMaxIdle(50);
        config.setMaxWaitMillis(1000 * 100);
        config.setTestOnBorrow(false);
        pool = new JedisPool(config,"localhost",6379,600,"1234");
    }

    /**
     * 存储数据
     * @param key
     * @param t
     * @param <T>
     * @return
     */
    public static <T> boolean setObject(String key,T t){
        Jedis redis = pool.getResource();//从池中获取连接对像
        try {
            String json = JSONObject.toJSONString(t);
            redis.set(key, json);
            redis.close();//把连接对像返回池中
            return true;
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
    }

    /**
     * 获取对像数据
     * @param key
     * @param clazz
     * @param <T>
     * @return
     */
    public static <T>T getObject(String key,Class<T> clazz){

        Jedis redis = pool.getResource();//从池中获取连接对像
        String jsonStr = redis.get(key);
        T t = JSONObject.parseObject(jsonStr, clazz);
        redis.close();//把连接对像返回池中
        return t;

    }

    /**
     * 返回集合数据
     * @param key
     * @param clazz
     * @param <T>
     * @return
     */
    public static <T> List<T> getList(String key, Class<T> clazz){
        Jedis redis = pool.getResource();//从池中获取连接对像
        String jsonStr = redis.get(key);
        List<T> list = JSONObject.parseArray(jsonStr, clazz);
        redis.close();//把连接对像返回池中
        return list;
    }

    /**
     * 判断一个key在redis中是否存在
     * @param key
     * @return
     */
    public static boolean containsKey(String key){
        Jedis redis = pool.getResource();
        return redis.exists("key");
    }

    /**
     * 清空缓存
     */
    public static void  flushDB(){
        Jedis redis = pool.getResource();
        redis.flushDB();
    }
}
