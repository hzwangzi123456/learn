package redis;

import redis.clients.jedis.Jedis;

/**
 * @author wangzi
 * @date 18/12/12 下午3:56.
 */
public class demo01 {
    public static void main(String[] args) {
        Jedis jedis = new Jedis("127.0.0.1");
        System.out.println("连接成功");
        System.out.println("服务正在运行:" + jedis.ping());
        jedis.set("abc","abcValue");
        System.out.println("redis 存储的字符串:" +  jedis.get("abc"));
    }
}
