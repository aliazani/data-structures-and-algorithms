package io.github.aliazani.linear.hashtables;

import io.github.aliazani.linear.hashtables.chaining.MyHashTableChaining;
import io.github.aliazani.linear.hashtables.open_addressing.double_hashing.MyHashTableDoubleHashing;
import io.github.aliazani.linear.hashtables.open_addressing.linear_probing.MyHashTableLinearProbing;
import lombok.extern.slf4j.Slf4j;

import java.text.MessageFormat;
import java.util.*;
import java.util.stream.Collectors;

@Slf4j
public class HashTableDemo {
    public static void main(String[] args) {
        showJavaImplementationOfHashMap();

        log.info(String.valueOf(MapHelper.findFirstNonRepeatingCharacter("A Green Apple.")));

        showJavaImplementationOfSet();
        log.info(String.valueOf(MapHelper.findFirstRepeatedCharacter("A Green apple")));

        showMyHashTable();

        log.info(String.valueOf(MapHelper.mostFrequent(new int[]{1, 2, 3, 3, 3, 1, 2, 4, 5, 4, 3, 9, 3})));
        log.info(String.valueOf(MapHelper.countPairsWithDiff(new int[]{1, 7, 5, 9, 2, 12, 3, 3, 4, 9}, 2)));
        log.info(Arrays.toString(MapHelper.twoSum(new int[]{2, 7, 11, 15}, 9)));
    }

    private static void showJavaImplementationOfHashMap() {
        log.warn("showJavaImplementationOfHashMap");
        Map<Integer, String> hashMap = new HashMap<>();
        hashMap.put(1, "ali");
        hashMap.put(2, "reza");
        hashMap.put(3, "mohammad");
        hashMap.put(1, "mohammadali");
        hashMap.put(null, null);

        log.info(MessageFormat.format("HashMap: {0}", hashMap));
        hashMap.remove(null);
        log.info(MessageFormat.format("HashMap after removing null: {0}", hashMap));
        log.info(MessageFormat.format("Get(2): {0}", hashMap.get(2)));
        log.info(MessageFormat.format("ContainsValue(reza): {0}", hashMap.containsValue("reza")));
        log.info(MessageFormat.format("ContainsKey(1): {0}", hashMap.containsKey(1)));

        hashMap.forEach((key, value) -> log.info(MessageFormat.format("{0}: {1}", key, value)));
    }

    public static void showJavaImplementationOfSet() {
        log.warn("showJavaImplementationOfSet");
        Integer[] numbers = {1, 2, 3, 3, 4, 4, 5, 6, 7, 8, 8};
        log.info(MessageFormat.format("Array of integer: {0}", Arrays.toString(numbers)));
        Set<Integer> set = Arrays.stream(numbers).collect(Collectors.toSet());
        log.info(MessageFormat.format("Set of integer: {0}", set));
    }

    public static void showMyHashTable() {
        log.warn("MyHashTableChaining");
        MyHashTableChaining<Integer, String> table = new MyHashTableChaining<>(5);
        table.put(6, "A");
        table.put(6, "A+");
        table.put(8, "B");
        table.put(11, "C");
        log.info(MessageFormat.format("HashTableChaining: {0}", table));
        log.info(MessageFormat.format("get(6): {0}", table.get(6)));
        table.remove(6);
        log.info(MessageFormat.format("HashTableChaining after remove(6): {0}", table));
        table.remove(8);
        log.info(MessageFormat.format("HashTableChaining after remove(8): {0}", table));
        table.put(8, "ali");
        log.info(MessageFormat.format("HashTableChaining after put(8, ali): {0}", table));
        table.put(13, "ali+");
        log.info(MessageFormat.format("HashTableChaining after put(13, ali+): {0}", table));

        log.warn("MyHashTableLinearProbing");
        MyHashTableLinearProbing<Integer, String> map = new MyHashTableLinearProbing<>(5);
        map.put(6, "A");
        map.put(6, "A+");
        map.put(8, "B");
        map.put(11, "C");
        log.info(MessageFormat.format("HashTableLinearProbing: {0}", map));
        log.info(MessageFormat.format("get(6): {0}", map.get(6)));
        map.remove(6);
        log.info(MessageFormat.format("HashTableLinearProbing after remove(6): {0}", map));
        map.remove(8);
        log.info(MessageFormat.format("HashTableLinearProbing after remove(8): {0}", map));
        map.put(8, "ali");
        log.info(MessageFormat.format("HashTableLinearProbing after put(8, ali): {0}", map));
        map.put(13, "ali+");
        log.info(MessageFormat.format("HashTableLinearProbing after put(13, ali+): {0}", map));

        log.warn("MyHashTableDoubleHashing");
        MyHashTableDoubleHashing<Integer, String> mapDoubleHashing = new MyHashTableDoubleHashing<>(5);
        mapDoubleHashing.put(6, "A");
        mapDoubleHashing.put(6, "A+");
        mapDoubleHashing.put(8, "B");
        mapDoubleHashing.put(11, "C");
        log.info(MessageFormat.format("HashTableDoubleHashing: {0}", mapDoubleHashing));
        log.info(MessageFormat.format("get(6): {0}", mapDoubleHashing.get(6)));
        mapDoubleHashing.remove(6);
        log.info(MessageFormat.format("HashTableDoubleHashing after remove(6): {0}", mapDoubleHashing));
        mapDoubleHashing.remove(8);
        log.info(MessageFormat.format("HashTableDoubleHashing after remove(8): {0}", mapDoubleHashing));
        mapDoubleHashing.put(8, "ali");
        log.info(MessageFormat.format("HashTableLinearProbing after put(8, ali): {0}", mapDoubleHashing));
        mapDoubleHashing.put(13, "ali+");
        log.info(MessageFormat.format("HashTableLinearProbing after put(13, ali+): {0}", mapDoubleHashing));
    }
}
