package com.bwie.dianshang.utils;

/**
 * 类描述：
 * 创建人：guodongdong
 * 创建时间：2017/7/24
 */
public class MessageEvent {
    private boolean isCheckAll;
    private int price;

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public boolean isCheckAll() {
        return isCheckAll;
    }

    public void setCheckAll(boolean checkAll) {
        isCheckAll = checkAll;
    }

    public MessageEvent(boolean isCheckAll) {
        this.isCheckAll = isCheckAll;
    }

    public MessageEvent(int price) {
        this.price = price;
    }

}
