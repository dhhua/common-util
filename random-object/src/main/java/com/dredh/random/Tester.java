package com.dredh.random;

import java.util.List;
import java.util.Random;

public class Tester {

    private static class Prize implements RandomObject {

        private final double weight;

        private final String name;
        public Prize(String name, double weight) {
            this.name = name;
            this.weight = weight;
        }

        @Override
        public double getWeight() {
            return weight;
        }

        @Override
        public String toString() {
            final StringBuilder sb = new StringBuilder("Prize{");
            sb.append("weight=").append(weight);
            sb.append(", name='").append(name).append('\'');
            sb.append('}');
            return sb.toString();
        }
    }

    public static void main(String[] args) {
        RandomObjectPool randomObjectPool = new RandomObjectPool();
        for (int i = 0; i < 100; i++) {
            randomObjectPool.addRandomObject(new Prize("prize" + i, new Random(System.currentTimeMillis()).nextDouble() * 100));
        }

        RandomObject randomObject = randomObjectPool.random();
        System.out.println(randomObject);
        System.out.println("remaining size : " + randomObjectPool.size());

        List<RandomObject> randomObjects = randomObjectPool.random(10);
        System.out.println(randomObjects);
        System.out.println("remaining size : " + randomObjectPool.size());

        RandomObject randomObject1 = randomObjectPool.randomAndRemove();
        System.out.println(randomObject1);
        System.out.println("remaining size : " + randomObjectPool.size());

        List<RandomObject> randomObjects1 = randomObjectPool.randomAndRemove(10);
        System.out.println(randomObjects1);
        System.out.println("remaining size : " + randomObjectPool.size());
    }
}
