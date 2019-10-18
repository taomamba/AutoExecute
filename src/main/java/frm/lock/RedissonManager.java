package frm.lock;

import org.redisson.Redisson;
import org.redisson.config.Config;

public class RedissonManager {
    private static Config config = new Config();
    //声明redisso对象
    private static Redisson redisson = null;
    //实例化redisson
    public static void init(String key,String value){
        try {
            //使用单机版配置
            config.useSingleServer().setAddress("127.0.0.1:6379");
            //得到redisson对象
            redisson = (Redisson) Redisson.create(config);

        }catch (Exception e){
            e.printStackTrace();
        }
    }
    //获取redisson对象的方法
    public static Redisson getRedisson(){
        return redisson;
    }
}
