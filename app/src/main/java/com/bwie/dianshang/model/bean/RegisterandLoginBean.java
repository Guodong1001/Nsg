package com.bwie.dianshang.model.bean;

/**
 * 类描述：
 * 创建人：guodongdong
 * 创建时间：2017/7/19
 */
public class RegisterandLoginBean {

    /**
     * code : 200
     * datas : {"username":"wxfwcf","userid":"6","key":"1cae28a28512ef514dd6e9e7e026a5dc"}
     */

    private int code;
    private DatasBean datas;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public DatasBean getDatas() {
        return datas;
    }

    public void setDatas(DatasBean datas) {
        this.datas = datas;
    }

    public static class DatasBean {
        /**
         * username : wxfwcf
         * userid : 6
         * key : 1cae28a28512ef514dd6e9e7e026a5dc
         */

        private String username;
        private String userid;
        private String key;

        public String getUsername() {
            return username;
        }

        public void setUsername(String username) {
            this.username = username;
        }

        public String getUserid() {
            return userid;
        }

        public void setUserid(String userid) {
            this.userid = userid;
        }

        public String getKey() {
            return key;
        }

        public void setKey(String key) {
            this.key = key;
        }
    }
}
