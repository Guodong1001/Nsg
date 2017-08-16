package com.bwie.dianshang.utils;

/**
 * 类描述：
 * 创建人：guodongdong
 * 创建时间：2017/7/12
 */
public class Urls {
    public static final String LINK_MAIN = "http://169.254.58.81/";
    public static final String LINK_MOBILE = LINK_MAIN + "mobile/index.php?act=";


    public static final String LINK_MOBILE_CLASS = LINK_MOBILE + "goods_class";
    public static final String LINK_MOBILE_LOGIN = LINK_MOBILE + "login";
    public static final String LINK_MOBILE_REG = LINK_MOBILE + "login&op=register";
    public static final String GOODS_CONNECT = LINK_MOBILE + "goods&op=goods_list&page=100";
    public static final String GOODS_DETAIL = LINK_MOBILE+"goods&op=goods_detail&goods_id=";


}
