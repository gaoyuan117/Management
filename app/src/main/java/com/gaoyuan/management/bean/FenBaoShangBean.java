package com.gaoyuan.management.bean;

import java.util.List;

/**
 * Created by gaoyuan on 2017/7/24.
 */

public class FenBaoShangBean {

    private List<FenbaoshangListBean> fenbaoshangList;

    public List<FenbaoshangListBean> getFenbaoshangList() {
        return fenbaoshangList;
    }

    public void setFenbaoshangList(List<FenbaoshangListBean> fenbaoshangList) {
        this.fenbaoshangList = fenbaoshangList;
    }

    public static class FenbaoshangListBean {
        /**
         * company :
         * companyId : 1
         * csProjectId : 2
         * headportrait : http://static.024pm.com/cscec/img/userHead/default.png
         * id : 36
         * jobId : 0
         * nickName : fenbaoshang0
         * registDateStr : 2017-07-22 02:10:37
         * shouquanweituoshu :
         * status : ok
         * telPhone : 18523447654
         * userType : fenbaoshang
         * voCompanyName : 分包商单位A
         * voCsProjectName : 测试项目23
         * voJobName :
         * yingyezhizhao :
         */

        private String company;
        private int companyId;
        private int csProjectId;
        private String headportrait;
        private int id;
        private int jobId;
        private String nickName;
        private String registDateStr;
        private String shouquanweituoshu;
        private String status;
        private String telPhone;
        private String userType;
        private String voCompanyName;
        private String voCsProjectName;
        private String voJobName;
        private String yingyezhizhao;

        public String getCompany() {
            return company;
        }

        public void setCompany(String company) {
            this.company = company;
        }

        public int getCompanyId() {
            return companyId;
        }

        public void setCompanyId(int companyId) {
            this.companyId = companyId;
        }

        public int getCsProjectId() {
            return csProjectId;
        }

        public void setCsProjectId(int csProjectId) {
            this.csProjectId = csProjectId;
        }

        public String getHeadportrait() {
            return headportrait;
        }

        public void setHeadportrait(String headportrait) {
            this.headportrait = headportrait;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public int getJobId() {
            return jobId;
        }

        public void setJobId(int jobId) {
            this.jobId = jobId;
        }

        public String getNickName() {
            return nickName;
        }

        public void setNickName(String nickName) {
            this.nickName = nickName;
        }

        public String getRegistDateStr() {
            return registDateStr;
        }

        public void setRegistDateStr(String registDateStr) {
            this.registDateStr = registDateStr;
        }

        public String getShouquanweituoshu() {
            return shouquanweituoshu;
        }

        public void setShouquanweituoshu(String shouquanweituoshu) {
            this.shouquanweituoshu = shouquanweituoshu;
        }

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }

        public String getTelPhone() {
            return telPhone;
        }

        public void setTelPhone(String telPhone) {
            this.telPhone = telPhone;
        }

        public String getUserType() {
            return userType;
        }

        public void setUserType(String userType) {
            this.userType = userType;
        }

        public String getVoCompanyName() {
            return voCompanyName;
        }

        public void setVoCompanyName(String voCompanyName) {
            this.voCompanyName = voCompanyName;
        }

        public String getVoCsProjectName() {
            return voCsProjectName;
        }

        public void setVoCsProjectName(String voCsProjectName) {
            this.voCsProjectName = voCsProjectName;
        }

        public String getVoJobName() {
            return voJobName;
        }

        public void setVoJobName(String voJobName) {
            this.voJobName = voJobName;
        }

        public String getYingyezhizhao() {
            return yingyezhizhao;
        }

        public void setYingyezhizhao(String yingyezhizhao) {
            this.yingyezhizhao = yingyezhizhao;
        }
    }
}
