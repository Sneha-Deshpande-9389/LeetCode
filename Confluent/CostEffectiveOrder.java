package Confluent;
/*
can somebody help how to solve this problem?


// You are building an App that lets the users determine the most cost-effective order that they can place
in a restaurant for the food items that they want to have.
You have the menu of the restaurant that contains item name, and it's price.
The restaurant can also offer Value Meals, which are groups of several items, at a discounted price.
Write a program that accepts a list of menu items, and a list of items that the user wants to eat,
and outputs the best price at which they can get all of their desired items.
// [Constraint: The user can order a maximum of 3 unique items.]


input
[5.00, "pizza"],
[8.00, "sandwich, coke"],
[4.00, "pasta"],
[2.00, "coke"],
[6.00, "pasta, coke, pizza"],
[8.00, "burger, coke, pizza"],
[5.00, "sandwich"]


user_wants: ["burger", "pasta"]


output
12

https://leetcode.com/discuss/interview-question/4999712/Confluent-coding-round



 */


import javafx.util.Pair;

import java.util.*;

public class CostEffectiveOrder {
    public static void main(String[] args) {
        List<Pair<Double, List<String>>> menu = new ArrayList<>();
        menu.add(new Pair(5.00, Arrays.asList("pizza")));//c1
        menu.add(new Pair(8.00, Arrays.asList("sandwich", "coke")));
        menu.add(new Pair(4.00, Arrays.asList("pasta")));//c3
        menu.add(new Pair(2.00, Arrays.asList("coke")));
        menu.add(new Pair(6.00, Arrays.asList("pasta", "coke", "pizza")));//c5
        menu.add(new Pair(8.00, Arrays.asList("burger", "coke", "pizza")));
        menu.add(new Pair(5.00, Arrays.asList("sandwich")));//c7
        System.out.println("finding best prices: " + findBestPrices(menu, Arrays.asList("coke", "pasta")));
    }

    /*
    sand, coke

    */
    private static Double findBestPrices(List<Pair<Double, List<String>>> menu, List<String> customersOrder) {
        Double res = Double.MAX_VALUE;
        HashMap<String, List<Pair<Double, String>>> map = new HashMap<>();
        int i = 1;
        for(Pair<Double, List<String>> group : menu) {
            List<String> items = group.getValue();
            Double price = group.getKey();
            for(String item : items) {
                map.putIfAbsent(item, new ArrayList<>());
                map.get(item).add(new Pair(price, "C"+i));
            }
            i++;
        }
        HashMap<String, Double> allCouponsForItemsMap = new HashMap<>();
        Double commonCoupon = Double.MAX_VALUE;
        for(String customerOrderItem : customersOrder) {
            String item = customerOrderItem;
            List<Pair<Double, String>> pricesAndCoupons = map.get(item);
            Collections.sort(pricesAndCoupons, (a, b) -> Double.compare(a.getKey(), b.getKey()));
            res += pricesAndCoupons.get(0).getKey();

            for(var pricesAndCoupon : pricesAndCoupons) {
                if(!allCouponsForItemsMap.isEmpty() && allCouponsForItemsMap.containsKey(pricesAndCoupon.getValue())) {

                    commonCoupon = Math.min(commonCoupon, pricesAndCoupon.getKey());
                }
                allCouponsForItemsMap.put(pricesAndCoupon.getValue(), pricesAndCoupon.getKey());
            }
        }

        return Math.min(commonCoupon, res);
    }
}