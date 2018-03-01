package com.dredh;

import com.dredh.lock.RedisDistributedLock;
import com.dredh.mapper.KeyFetchRecordMapper;
import com.dredh.mapper.KeyGeneratorMapper;
import com.dredh.model.KeyFetchRecord;
import com.dredh.model.KeyGenerator;
import com.dredh.util.MybatisHelper;
import com.dredh.util.RedisHelper;
import org.apache.ibatis.session.SqlSession;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class LockTester {

    private String server;

    private RedisDistributedLock lock = new RedisDistributedLock(RedisHelper.instance, "key_generator");

    public LockTester(String server) {
        this.server = server;
    }

    public void run() {
        this.init();
        ExecutorService executorService = Executors.newFixedThreadPool(5);
        for (int i = 0; i < 5; i++) {
            executorService.execute(new Task());
        }
        executorService.shutdown();
    }

    private void init() {
        SqlSession session = MybatisHelper.instance.openSession();
        try {
            KeyGeneratorMapper generatorMapper = session.getMapper(KeyGeneratorMapper.class);
            KeyGenerator generator = generatorMapper.select(1);
            if (generator == null) {
                try {
                    lock.lock();
                    if (generatorMapper.select(1) == null) {
                        generatorMapper.insert(new KeyGenerator(1, 0));
                        session.commit();
                    }
                } finally {
                    lock.unlock();
                }
            }
        } finally {
            session.close();
        }
    }

    private class Task implements Runnable {

        private static final int MAX_KEY = 10000;
        @Override
        public void run() {
            SqlSession session = MybatisHelper.instance.openSession(true);
            try {
                KeyGeneratorMapper generatorMapper = session.getMapper(KeyGeneratorMapper.class);
                KeyFetchRecordMapper recordMapper = session.getMapper(KeyFetchRecordMapper.class);
                while (true) {
                    try {
                        lock.lock();
                        KeyGenerator keyGenerator = generatorMapper.select(1);
                        if (keyGenerator.getKey() >= MAX_KEY) {
                            System.exit(0);
                        }
                        recordMapper.insert(new KeyFetchRecord(keyGenerator.getKey(), server));
                        generatorMapper.increase(1, 1);
                        session.commit();
                    } catch (RuntimeException e) {
                        e.printStackTrace();
                        continue;
                    } finally {
                        lock.unlock();
                    }
                }
            } finally {
                session.close();
            }
        }
    }
}
