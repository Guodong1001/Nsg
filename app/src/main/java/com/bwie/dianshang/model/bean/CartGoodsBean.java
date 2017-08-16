package com.bwie.dianshang.model.bean;

/**
 * 类注释：
 * 创建人： Liyinggang
 * 时间： 2017/7/22 12:12
 */

public class CartGoodsBean {
	private String name;
	private int price;
	private int count;
	private boolean isChecked;

	public boolean isChecked() {
		return isChecked;
	}

	public void setChecked(boolean checked) {
		isChecked = checked;
	}

	public CartGoodsBean(String name, int price, int count, boolean isChecked) {
		this.name = name;
		this.price = price;
		this.count = count;
		this.isChecked = isChecked;
	}

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

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}
}
