package com.telepathy.question2;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class PlanSet  {
    private List<Plan> plans;

    
    public PlanSet(List<Plan> plans) {
		super();
		this.plans = plans;
	}

	public PlanSet() {
        this.plans = new ArrayList<>();
    }

    public void addPlan(Plan plan) {
        plans.add(plan);
    }

    public List<Plan> getPlans() {
        return plans;
    }

    public int getTotalPrice() {
        int totalPrice = 0;
        for (Plan plan : plans) {
            totalPrice += plan.getPrice();
        }
        return totalPrice;
    }

    public boolean containsAllFeatures(Set<String> features) {
        for (String feature : features) {
            boolean found = false;
            for (Plan plan : plans) {
                if (plan.getFeatures().contains(feature)) {
                    found = true;
                    break;
                }
            }
            if (!found) {
                return false;
            }
        }
        return true;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (Plan plan : plans) {
            sb.append(plan.getName()).append(",");
        }
        sb.setLength(sb.length() - 1); // remove last comma
        return sb.toString();
    }

	

	
}
