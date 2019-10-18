
package frm.util;


import frm.lock.DistributedRedisLock;
import sun.misc.BASE64Encoder;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Random;

public class TokenProcessor {
    //为保证随机数唯一，我们使用单例模式
    public static final TokenProcessor instance = new TokenProcessor();
    private TokenProcessor(){

    }
    public static TokenProcessor getInstance(){
        return instance;
    }
    //一个产生生成随机数的方法
    public String generateToken(){
        String token = System.currentTimeMillis()+new Random(99999).nextInt()+"";
        //先做MD5的实例(如果需要考虑加盐)
        try {
            MessageDigest md5 = MessageDigest.getInstance("md5");
            byte[] tokenByte = md5.digest(token.getBytes());
            BASE64Encoder base64Encoder = new BASE64Encoder();
            return base64Encoder.encode(tokenByte);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return null;
    }
    //检查令牌是否有效（同步线程，使用分布式锁）
    public boolean RepeatSubmit(HttpServletRequest request){
        String key = "isRepeatSubmit";
        //加锁
        DistributedRedisLock.acquire(key);
        //执行具体业务逻辑
        String serverToken = (String)request.getSession(false).getAttribute("token");
        String clienToken = request.getParameter("token");
        if (null == clienToken & null == serverToken){
            return true;
        }
        boolean result = serverToken.equals(clienToken);
        //释放锁
        DistributedRedisLock.release(key);
        //返回结果
        return !result;
    }

}
