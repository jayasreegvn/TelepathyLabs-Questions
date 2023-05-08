package com.telepathy.question2;

import java.util.Set;

public class Plan {
	private String name;
	private int price;
	private Set<String> features;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getPrice() {
		return price;
	}
	public void setPrice(int price) {
		this.price = price;
	}
	public Set<String> getFeatures() {
		return features;
	}
	public void setFeatures(Set<String> features) {
		this.features = features;
	}
	public Plan(String name, int price, Set<String> features) {
		super();
		this.name = name;
		this.price = price;
		this.features = features;
	}
	@Override
	public String toString() {
		return "Plan [name=" + name + ", price=" + price + ", features="
				+ features + "]";
	}
}
