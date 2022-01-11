package com.gxitsky.stream;

import com.alibaba.fastjson.JSON;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class StreamGroupBy {

    public static void main(String[] args) {
        List<User2> userList = new ArrayList<>();
        userList.add(new User2(1, "AA1", 11, "boy"));
        userList.add(new User2(2, "AA2", 12, "girl"));
        userList.add(new User2(3, "AA3", 13, "girl"));
        userList.add(new User2(4, "AA4", 14, "boy"));
        userList.add(new User2(5, "AA5", 16, "girl"));
        userList.add(new User2(6, "AA6", 16, "girl"));
        userList.add(new User2(7, "AA7", 16, "girl"));
        userList.add(new User2(8, "AA8", 18, "boy"));
        userList.add(new User2(9, "AA9", 19, "girl"));

        //Collectors.groupingBy
//        Map<String, List<User>> userSexMap = userList.stream().collect(Collectors.groupingBy(User::getSex));
        Map<Integer, User2> userIdMap1 = userList.stream().collect(Collectors.toMap(User2::getId, p -> p));
        Map<Integer, User2> userIdMap2 = userList.stream().collect(Collectors.toMap(User2::getId, p -> p, (key1, key2) -> key2));
        Map<Integer, User2> userIdMap3 = userList.stream().collect(Collectors.toMap(User2::getId, Function.identity(), (key1, key2) -> key2));

        //Collectors.partitioningBy
        Map<Boolean, List<User2>> map = userList.stream().collect(Collectors.partitioningBy(p -> p.getAge() < 15));

        Stream.of(userList).flatMap(Collection::stream).forEach(System.out::print);

        //filter 过滤
        List<User2> boyList = userList.stream().filter(user -> user.getSex().equals("boy")).collect(Collectors.toList());

        List<User2> limitList = userList.stream().limit(4).collect(Collectors.toList());
        long girlNum = userList.stream().filter(user -> user.getSex().equals("girl")).count();
        List<Integer> ageList = userList.stream().map(user -> user.getAge()).distinct().collect(Collectors.toList());

        Integer[] ageArr = {11,5,12,16,4,15,14,17,21};
        List<Integer> ageList1 = Arrays.asList(ageArr);
        //sorted排序
        List<Integer> sorted1 = ageList1.stream().sorted().collect(Collectors.toList());
        List<Integer> sorted2 = ageList1.stream().sorted(Comparator.comparing(Integer::intValue)).collect(Collectors.toList());
        List<Integer> sorted3 = ageList.stream().sorted(Comparator.reverseOrder()).collect(Collectors.toList());
        List<User2> sorted4= userList.stream().sorted(Comparator.comparing(User2::getAge)).collect(Collectors.toList());
        List<User2> sorted5= userList.stream().sorted(Comparator.comparing(User2::getAge).reversed()).collect(Collectors.toList());


        /*集合遍历*/
        //for循环
        //Iterator迭代器
        Iterator<User2> userIterator = userList.iterator();
        while (userIterator.hasNext()) {
            User2 user = userIterator.next();
        }

        //统计
        List<Integer> numbers = Arrays.asList(3, 2, 2, 3, 7, 3, 5);
        IntSummaryStatistics stats = numbers.stream().mapToInt((x) -> x).summaryStatistics();
        System.out.println("列表中最大的数 : " + stats.getMax());
        System.out.println("列表中最小的数 : " + stats.getMin());
        System.out.println("所有数之和 : " + stats.getSum());
        System.out.println("平均数 : " + stats.getAverage());
        System.out.println("个数 ：" + stats.getCount());

        System.out.println(JSON.toJSONString(map));
    }

    static class User2{
        private Integer id;
        private String name;
        private Integer age;
        private String sex;

        public User2() {
        }

        public User2(Integer id, String name, Integer age, String sex) {
            this.id = id;
            this.name = name;
            this.age = age;
            this.sex = sex;
        }

        public Integer getId() {
            return id;
        }

        public User2 setId(Integer id) {
            this.id = id;
            return this;
        }

        public String getName() {
            return name;
        }

        public User2 setName(String name) {
            this.name = name;
            return this;
        }

        public Integer getAge() {
            return age;
        }

        public User2 setAge(Integer age) {
            this.age = age;
            return this;
        }

        public String getSex() {
            return sex;
        }

        public User2 setSex(String sex) {
            this.sex = sex;
            return this;
        }
    }
}
