package com.practice;

import java.util.HashMap;
import java.util.Map;

/**
 * @author yugi
 * @apiNote
 * @since 2017-03-29
 */
public class TestMemoryLeak {

    public static void main(String[] args) {
        Map<Key, String> map = new HashMap<>(1000);
        int counter = 0;
        while (true) {
            // creates duplicate objects due to bad Key class
            map.put(new Key("dummyKey"), "value");
            counter++;
            if (counter % 1000 == 0) {
                System.out.println("map size: " + map.size());
                System.out.println("Free memory after count " + counter + " is " + getFreeMemory() + "MB");
                sleep(1000);
            }
        }
    }

    // inner class key without hashcode() or equals() -- bad implementation
    static class Key {
        private String key;

        public Key(String key) {
            this.key = key;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;

            Key key1 = (Key) o;

            return key != null ? key.equals(key1.key) : key1.key == null;
        }

        @Override
        public int hashCode() {
            return key != null ? key.hashCode() : 0;
        }
    }

    //delay for a given period in milli seconds
    public static void sleep(long sleepFor) {
        try {
            Thread.sleep(sleepFor);
        }
        catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    //get available memory in MB
    public static long getFreeMemory() {
        return Runtime.getRuntime().freeMemory() / (1024 * 1024);
    }
}
