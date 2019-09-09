package controller;

import model.User;
import redis.RedisHelper;
import sun.security.pkcs11.Secmod;

public class TestRedis {

    public static void main(String[] args) {

        //1.取值
        String key = "user";
        //优先从redis取值
        if(RedisHelper.containsKey("user")){
            //直接从redis中取取,不交互数据库
        }else{
            //从数据库查询数据,放一份在redis中
            //DB.selectUser()
            User user = null;//从数据库查询的数据
            RedisHelper.setObject("user", user);
        }

        //2.插入新值到数据库,清空redis缓存
        RedisHelper.flushDB();


    }
}
