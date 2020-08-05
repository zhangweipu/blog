package com.wp.weipu.test.redislearn;


import org.junit.Test;
import redis.clients.jedis.Jedis;

import java.util.HashMap;
import java.util.Map;

/**
 * 这就是分布式锁的原理吗？？？
 * 搞不懂 它是怎么加锁的
 * 这是可重入锁，一个线程支持多次加锁
 */
public class RedisWithReentrantLock {

    /**
     * threadlocal不是为不同的线程提供不同的变量吧
     */
    private ThreadLocal<Map<String, Integer>> lockers = new ThreadLocal<>();

    private Jedis jedis;

    public RedisWithReentrantLock(Jedis jedis) {
        this.jedis = jedis;
    }

    private boolean _lock(String key) {
        //版本不对吗
        return jedis.set(key, "nx") != null;
    }

    private void _unlock(String key) {
        jedis.del(key);
    }

    private Map<String, Integer> currentLockers() {
        Map<String, Integer> refs = lockers.get();
        if (refs != null) {
            return refs;
        }
        lockers.set(new HashMap<>());
        //还是获取了一个空的
        return lockers.get();
    }

    /**
     * 枷锁
     *
     * @param key
     * @return
     */
    public boolean lock(String key) {
        Map<String, Integer> refs = currentLockers();
        Integer refCnt = refs.get(key);
        if (refCnt != null) {
            //merge可以用啊
            refs.put(key, refCnt + 1);
            return true;
        }
        boolean ok = this._lock(key);
        if (!ok) {
            return false;
        }
        refs.put(key, 1);
        return true;
    }

    /**
     * 解锁
     *
     * @param key
     * @return
     */
    public boolean unlock(String key) {
        Map<String, Integer> refs = currentLockers();
        Integer refCnt = refs.get(key);
        if (refCnt == null) {
            return false;
        }
        refCnt -= 1;
        if (refCnt > 0) {
            refs.put(key, refCnt);
        } else {
            refs.remove(key);
            this._unlock(key);
        }
        return true;
    }

}
