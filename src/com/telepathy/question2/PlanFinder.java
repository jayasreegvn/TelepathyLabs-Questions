package com.telepathy.question2;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class PlanFinder {

    public static void main(String[] args) throws IOException {
       if (args.length < 2) {
            System.out.println("Usage: java PlanFinder [plan file] [required features]");
            return;
        }

    	String planFile = args[0];
        String[] requiredFeatures = args[1].split(","); 


        List<Plan> plans = readPlans(planFile);
        PlanSet planSet = findCheapestPlanSet(plans, requiredFeatures);
        if (planSet != null) {
            System.out.println(planSet.getTotalPrice() + "," + planSet.toString());
        } else {
            System.out.println("0");
        }
    }

    private static List<Plan> readPlans(String filename) throws IOException {
        List<Plan> plans = new ArrayList<Plan>();
        try (BufferedReader br = new BufferedReader(new FileReader( filename))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.trim().split(",");
                String name = parts[0];
                int price = Integer.parseInt(parts[1]);
                Set<String> features = new HashSet<>(Arrays.asList(parts).subList(2, parts.length));
                plans.add(new Plan(name, price, features));
            }
        }
        return plans;
    }

    private static PlanSet findCheapestPlanSet(List<Plan> plans, String[] requiredFeatures) {
        Set<String> features = new HashSet<String>(Arrays.asList(requiredFeatures));
        List<PlanSet> allPlanSets = new ArrayList<PlanSet>();
        allPlanSets.addAll(getAllPlanSets(plans, features));
        if (allPlanSets.isEmpty()) {
            return null;
        }
       Collections.sort(allPlanSets, (x1,x2)->((Integer)x1.getTotalPrice()).compareTo((Integer)x2.getTotalPrice()));
       return allPlanSets.get(0);
    }

    private static List<PlanSet> getAllPlanSets(List<Plan> plans, Set<String> requiredFeatures) {
        List<PlanSet> allPlanSets = new ArrayList<PlanSet>();
        for (int i = 1; i <= plans.size(); i++) {
            List<PlanSet> planSets = getPlanSets(plans, i, requiredFeatures);
            allPlanSets.addAll(planSets);
        }
        return allPlanSets;
    }

    private static List<PlanSet> getPlanSets(List<Plan> plans, int size, Set<String> requiredFeatures) {
        List<List<Plan>> planLists = getCombinations(plans, size);
        List<PlanSet> planSets = new ArrayList<PlanSet>();
        for (List<Plan> planList : planLists) {
            PlanSet planSet = new PlanSet(planList);
            if (planSet.containsAllFeatures(requiredFeatures)) {
                planSets.add(planSet);
            }
        }
        return planSets;
    }

    private static <T> List<List<T>> getCombinations(List<T> items, int size) {
        if (size == 0) {
            return Collections.singletonList(Collections.emptyList());
        }
        if (items.isEmpty()) {
            return Collections.emptyList();
        }
        List<List<T>> combinations = new ArrayList<>();
        T head = items.get(0);
        List<T> tail = items.subList(1, items.size());
        for (List<T> smallerCombination : getCombinations(tail, size - 1)) {
            List<T> newCombination = new ArrayList<>();
            newCombination.add(head);
            newCombination.addAll(smallerCombination);
            combinations.add(newCombination);
        }
        combinations.addAll(getCombinations(tail, size));
        return combinations;
    }
}
