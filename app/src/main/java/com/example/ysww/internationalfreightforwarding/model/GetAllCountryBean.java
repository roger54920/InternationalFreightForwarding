package com.example.ysww.internationalfreightforwarding.model;

import java.util.List;

/**
 * 获取国家
 */

public class GetAllCountryBean extends BaseBean{

    private List<DataBean> data;

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * id : 7
         * name : 中国
         * typeId : 10004
         * prentId : null
         * ags1 :
         * ags2 : null
         * createDate : 2018-04-18 20:48:19
         * createBy : admin
         * updateDate : null
         * updateBy : null
         * status : null
         */

        private int id;
        private String name;
        private int typeId;
        private Object prentId;
        private String ags1;
        private Object ags2;
        private String createDate;
        private String createBy;
        private Object updateDate;
        private Object updateBy;
        private Object status;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public int getTypeId() {
            return typeId;
        }

        public void setTypeId(int typeId) {
            this.typeId = typeId;
        }

        public Object getPrentId() {
            return prentId;
        }

        public void setPrentId(Object prentId) {
            this.prentId = prentId;
        }

        public String getAgs1() {
            return ags1;
        }

        public void setAgs1(String ags1) {
            this.ags1 = ags1;
        }

        public Object getAgs2() {
            return ags2;
        }

        public void setAgs2(Object ags2) {
            this.ags2 = ags2;
        }

        public String getCreateDate() {
            return createDate;
        }

        public void setCreateDate(String createDate) {
            this.createDate = createDate;
        }

        public String getCreateBy() {
            return createBy;
        }

        public void setCreateBy(String createBy) {
            this.createBy = createBy;
        }

        public Object getUpdateDate() {
            return updateDate;
        }

        public void setUpdateDate(Object updateDate) {
            this.updateDate = updateDate;
        }

        public Object getUpdateBy() {
            return updateBy;
        }

        public void setUpdateBy(Object updateBy) {
            this.updateBy = updateBy;
        }

        public Object getStatus() {
            return status;
        }

        public void setStatus(Object status) {
            this.status = status;
        }
    }
}
