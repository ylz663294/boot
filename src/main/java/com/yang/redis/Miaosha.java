package com.yang.redis;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

import com.alibaba.fastjson.JSON;

import lombok.extern.slf4j.Slf4j;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.util.CollectionUtils;

@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest
public class Miaosha {

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    ExecutorService executorService = null;

    @Before
    public void init() {
        List<String> list = new ArrayList<>(100);
        for (int i = 0; i < 100; i++) {
            list.add("1");
        }
        //模拟商品Id和库存
        listRPushByJson("5", list, 1000);
        executorService = Executors.newFixedThreadPool(800);
    }

    @Test
    public void contextLoads() {
        AtomicInteger successNum = new AtomicInteger(0);
        AtomicInteger failNum = new AtomicInteger(0);
        CountDownLatch countDownLatch = new CountDownLatch(800);
        CyclicBarrier cyclicBarrier = new CyclicBarrier(800);
        for (int i = 0; i < 800; i++) {
            executorService.execute(() -> {
                try {
                    cyclicBarrier.await();
                } catch (InterruptedException | BrokenBarrierException e) {
                    e.printStackTrace();
                }
                if (listLPopByJson("5", String.class) != null) {
                    System.out.println("成功的线程name: " + Thread.currentThread().getName());
                    successNum.getAndIncrement();
                } else {
                    failNum.getAndIncrement();
                }
                countDownLatch.countDown();
            });
        }
        try {
            countDownLatch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("下单成功：" + successNum.get());
        System.out.println("下单失败：" + failNum.get());
    }

    /**
     * list lPop(json序列化)
     *
     * @param key 缓存key
     * @return object
     */
    public <T> T listLPopByJson(String key, Class<T> clazz) {
        try {
            String valueStr = stringRedisTemplate.opsForList().leftPop(key);
            if (org.springframework.util.StringUtils.isEmpty(valueStr)) {
                return null;
            }
            return JSON.parseObject(valueStr, clazz);
        } catch (Exception e) {
            log.error("listLPopByJson error key:{}", key, e);
            return null;
        }
    }

    /**
     * list rPush(json序列化)
     *
     * @param key            缓存key
     * @param valueList      需要缓存value集合
     * @param expiredSeconds 过期时间(单位为秒)
     */
    public void listRPushByJson(String key, List<?> valueList, long expiredSeconds) {
        if (org.springframework.util.StringUtils.isEmpty(key) || CollectionUtils.isEmpty(valueList)) {
            return;
        }
        try {
            List<String> jsonValueList = valueList.stream().map(JSON::toJSONString).collect(Collectors.toList());
            stringRedisTemplate.opsForList().rightPushAll(key, jsonValueList);
            stringRedisTemplate.expire(key, expiredSeconds, TimeUnit.SECONDS);
        } catch (Exception e) {
            log.error("listRPushByJson error key:{} valueList:{} expired:{}", key, valueList, expiredSeconds, e);
        }
    }


}