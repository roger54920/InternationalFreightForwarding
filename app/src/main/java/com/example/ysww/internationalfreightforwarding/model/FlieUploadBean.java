package com.example.ysww.internationalfreightforwarding.model;

/**
 * 上传文件所需要的参数
 */

public class FlieUploadBean extends BaseBean{

    /**
     * data : {"name":"\u201d文件原生名称\u201d","url":"\u201d相对路径\u201d"}
     */

    private DataBean data;

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * name : ”文件原生名称”
         * url : ”相对路径”
         */

        private String name;
        private String url;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }
    }
}
