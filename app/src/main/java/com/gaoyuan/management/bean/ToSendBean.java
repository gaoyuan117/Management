package com.gaoyuan.management.bean;

import java.util.List;

/**
 * Created by gaoyuan on 2017/7/18.
 */

public class ToSendBean {

    /**
     * projectList : [{"concatPerson":"寰恱x","concatPersonPhone":"18754462512","createDateStr":"2017-07-14 18:22:49","id":1,"info":"","locationStr":"闈掑矝","markedNum":"FX0001","name":"娴嬭瘯椤圭洰","status":"open"}]
     * sendMarkedNum : FH20170718002
     */

    private String sendMarkedNum;
    private List<ProjectListBean> projectList;

    public String getSendMarkedNum() {
        return sendMarkedNum;
    }

    public void setSendMarkedNum(String sendMarkedNum) {
        this.sendMarkedNum = sendMarkedNum;
    }

    public List<ProjectListBean> getProjectList() {
        return projectList;
    }

    public void setProjectList(List<ProjectListBean> projectList) {
        this.projectList = projectList;
    }

    public static class ProjectListBean {
        /**
         * concatPerson : 寰恱x
         * concatPersonPhone : 18754462512
         * createDateStr : 2017-07-14 18:22:49
         * id : 1
         * info :
         * locationStr : 闈掑矝
         * markedNum : FX0001
         * name : 娴嬭瘯椤圭洰
         * status : open
         */

        private String concatPerson;
        private String concatPersonPhone;
        private String createDateStr;
        private int id;
        private String info;
        private String locationStr;
        private String markedNum;
        private String name;
        private String status;

        public String getConcatPerson() {
            return concatPerson;
        }

        public void setConcatPerson(String concatPerson) {
            this.concatPerson = concatPerson;
        }

        public String getConcatPersonPhone() {
            return concatPersonPhone;
        }

        public void setConcatPersonPhone(String concatPersonPhone) {
            this.concatPersonPhone = concatPersonPhone;
        }

        public String getCreateDateStr() {
            return createDateStr;
        }

        public void setCreateDateStr(String createDateStr) {
            this.createDateStr = createDateStr;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getInfo() {
            return info;
        }

        public void setInfo(String info) {
            this.info = info;
        }

        public String getLocationStr() {
            return locationStr;
        }

        public void setLocationStr(String locationStr) {
            this.locationStr = locationStr;
        }

        public String getMarkedNum() {
            return markedNum;
        }

        public void setMarkedNum(String markedNum) {
            this.markedNum = markedNum;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }
    }
}
