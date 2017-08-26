package com.gaoyuan.management.bean;

import java.util.List;

/**
 * Created by gaoyuan on 2017/7/26.
 */

public class EditBean {

    /**
     * projectList : [{"concatPerson":"老张","concatPersonPhone":"1875426359","createDateStr":"2017-07-21 15:50:55","id":2,"info":"1","locationStr":"沈阳","markedNum":"FX0002","name":"测试项目23","status":"open"},{"concatPerson":"徐xx","concatPersonPhone":"18754462512","createDateStr":"2017-07-14 18:22:49","id":1,"info":"","locationStr":"青岛","markedNum":"FX0001","name":"测试项目","status":"open"}]
     * wuliaodan : {"createDateStr":"2017-07-21 21:32:55","csProjectId":2,"fenliaoStatus":"waitFen","gonghuoUserId":25,"id":30,"reciveDateStr":"","reciveMarkedNum":"","sendDateStr":"2017-07-21 21:32:55","sendMarkedNum":"FH20170721005","signRecive":"","signSend":"img/userOther/1500643973638.jpg","status":"sendedWaitRecive","voCanModOrDel":"YES","voCsProject":{"concatPerson":"老张","concatPersonPhone":"1875426359","createDateStr":"2017-07-21 15:50:55","id":2,"info":"1","locationStr":"沈阳","markedNum":"FX0002","name":"测试项目23","status":"open"},"voGonghuoshangUser":{"company":"gy","companyId":0,"csProjectId":0,"headportrait":"http://static.024pm.com/cscec/img/userHead/1500603916461.jpg","id":25,"jobId":0,"name":"supply2","nickName":"supply2","registDateStr":"2017-07-21 09:51:52","shouquanweituoshu":"","status":"ok","telPhone":"14763766684","userType":"gonghuoshang","voCompanyName":"","voCsProjectName":"","voJobName":"","yingyezhizhao":"img/userOther/1500601882499.jpg"},"voImages":[],"voWuliaodanItemList":[{"brand":"哈哈","csProjectId":2,"fenliaoCount":0,"fenliaoStatus":"waitFen","id":22,"name":"西瓜","reciveCount":0,"reciveDateStr":"","recivePrice":0,"reciveStatus":"wait","reciveTotalPrice":0,"remarks":"","restCount":0,"sendCount":50,"sendPrice":0,"sendTotalPrice":0,"specifications":"AS-20","unitName":"吨","wuliaodanId":30}],"voWuziguanliUser":null,"wuziguanliUserId":0}
     */

    private WuliaodanBean wuliaodan;
    private List<ProjectListBean> projectList;

    public WuliaodanBean getWuliaodan() {
        return wuliaodan;
    }

    public void setWuliaodan(WuliaodanBean wuliaodan) {
        this.wuliaodan = wuliaodan;
    }

    public List<ProjectListBean> getProjectList() {
        return projectList;
    }

    public void setProjectList(List<ProjectListBean> projectList) {
        this.projectList = projectList;
    }

    public static class WuliaodanBean {
        /**
         * createDateStr : 2017-07-21 21:32:55
         * csProjectId : 2
         * fenliaoStatus : waitFen
         * gonghuoUserId : 25
         * id : 30
         * reciveDateStr :
         * reciveMarkedNum :
         * sendDateStr : 2017-07-21 21:32:55
         * sendMarkedNum : FH20170721005
         * signRecive :
         * signSend : img/userOther/1500643973638.jpg
         * status : sendedWaitRecive
         * voCanModOrDel : YES
         * voCsProject : {"concatPerson":"老张","concatPersonPhone":"1875426359","createDateStr":"2017-07-21 15:50:55","id":2,"info":"1","locationStr":"沈阳","markedNum":"FX0002","name":"测试项目23","status":"open"}
         * voGonghuoshangUser : {"company":"gy","companyId":0,"csProjectId":0,"headportrait":"http://static.024pm.com/cscec/img/userHead/1500603916461.jpg","id":25,"jobId":0,"name":"supply2","nickName":"supply2","registDateStr":"2017-07-21 09:51:52","shouquanweituoshu":"","status":"ok","telPhone":"14763766684","userType":"gonghuoshang","voCompanyName":"","voCsProjectName":"","voJobName":"","yingyezhizhao":"img/userOther/1500601882499.jpg"}
         * voImages : []
         * voWuliaodanItemList : [{"brand":"哈哈","csProjectId":2,"fenliaoCount":0,"fenliaoStatus":"waitFen","id":22,"name":"西瓜","reciveCount":0,"reciveDateStr":"","recivePrice":0,"reciveStatus":"wait","reciveTotalPrice":0,"remarks":"","restCount":0,"sendCount":50,"sendPrice":0,"sendTotalPrice":0,"specifications":"AS-20","unitName":"吨","wuliaodanId":30}]
         * voWuziguanliUser : null
         * wuziguanliUserId : 0
         */

        private String createDateStr;
        private int csProjectId;
        private String fenliaoStatus;
        private int gonghuoUserId;
        private int id;
        private String reciveDateStr;
        private String reciveMarkedNum;
        private String sendDateStr;
        private String sendMarkedNum;
        private String signRecive;
        private String signSend;
        private String status;
        private String voCanModOrDel;
        private VoCsProjectBean voCsProject;
        private VoGonghuoshangUserBean voGonghuoshangUser;
        private Object voWuziguanliUser;
        private int wuziguanliUserId;
        private List<?> voImages;
        private List<VoWuliaodanItemListBean> voWuliaodanItemList;

        public String getCreateDateStr() {
            return createDateStr;
        }

        public void setCreateDateStr(String createDateStr) {
            this.createDateStr = createDateStr;
        }

        public int getCsProjectId() {
            return csProjectId;
        }

        public void setCsProjectId(int csProjectId) {
            this.csProjectId = csProjectId;
        }

        public String getFenliaoStatus() {
            return fenliaoStatus;
        }

        public void setFenliaoStatus(String fenliaoStatus) {
            this.fenliaoStatus = fenliaoStatus;
        }

        public int getGonghuoUserId() {
            return gonghuoUserId;
        }

        public void setGonghuoUserId(int gonghuoUserId) {
            this.gonghuoUserId = gonghuoUserId;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getReciveDateStr() {
            return reciveDateStr;
        }

        public void setReciveDateStr(String reciveDateStr) {
            this.reciveDateStr = reciveDateStr;
        }

        public String getReciveMarkedNum() {
            return reciveMarkedNum;
        }

        public void setReciveMarkedNum(String reciveMarkedNum) {
            this.reciveMarkedNum = reciveMarkedNum;
        }

        public String getSendDateStr() {
            return sendDateStr;
        }

        public void setSendDateStr(String sendDateStr) {
            this.sendDateStr = sendDateStr;
        }

        public String getSendMarkedNum() {
            return sendMarkedNum;
        }

        public void setSendMarkedNum(String sendMarkedNum) {
            this.sendMarkedNum = sendMarkedNum;
        }

        public String getSignRecive() {
            return signRecive;
        }

        public void setSignRecive(String signRecive) {
            this.signRecive = signRecive;
        }

        public String getSignSend() {
            return signSend;
        }

        public void setSignSend(String signSend) {
            this.signSend = signSend;
        }

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }

        public String getVoCanModOrDel() {
            return voCanModOrDel;
        }

        public void setVoCanModOrDel(String voCanModOrDel) {
            this.voCanModOrDel = voCanModOrDel;
        }

        public VoCsProjectBean getVoCsProject() {
            return voCsProject;
        }

        public void setVoCsProject(VoCsProjectBean voCsProject) {
            this.voCsProject = voCsProject;
        }

        public VoGonghuoshangUserBean getVoGonghuoshangUser() {
            return voGonghuoshangUser;
        }

        public void setVoGonghuoshangUser(VoGonghuoshangUserBean voGonghuoshangUser) {
            this.voGonghuoshangUser = voGonghuoshangUser;
        }

        public Object getVoWuziguanliUser() {
            return voWuziguanliUser;
        }

        public void setVoWuziguanliUser(Object voWuziguanliUser) {
            this.voWuziguanliUser = voWuziguanliUser;
        }

        public int getWuziguanliUserId() {
            return wuziguanliUserId;
        }

        public void setWuziguanliUserId(int wuziguanliUserId) {
            this.wuziguanliUserId = wuziguanliUserId;
        }

        public List<?> getVoImages() {
            return voImages;
        }

        public void setVoImages(List<?> voImages) {
            this.voImages = voImages;
        }

        public List<VoWuliaodanItemListBean> getVoWuliaodanItemList() {
            return voWuliaodanItemList;
        }

        public void setVoWuliaodanItemList(List<VoWuliaodanItemListBean> voWuliaodanItemList) {
            this.voWuliaodanItemList = voWuliaodanItemList;
        }

        public static class VoCsProjectBean {
            /**
             * concatPerson : 老张
             * concatPersonPhone : 1875426359
             * createDateStr : 2017-07-21 15:50:55
             * id : 2
             * info : 1
             * locationStr : 沈阳
             * markedNum : FX0002
             * name : 测试项目23
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

        public static class VoGonghuoshangUserBean {
            /**
             * company : gy
             * companyId : 0
             * csProjectId : 0
             * headportrait : http://static.024pm.com/cscec/img/userHead/1500603916461.jpg
             * id : 25
             * jobId : 0
             * name : supply2
             * nickName : supply2
             * registDateStr : 2017-07-21 09:51:52
             * shouquanweituoshu :
             * status : ok
             * telPhone : 14763766684
             * userType : gonghuoshang
             * voCompanyName :
             * voCsProjectName :
             * voJobName :
             * yingyezhizhao : img/userOther/1500601882499.jpg
             */

            private String company;
            private int companyId;
            private int csProjectId;
            private String headportrait;
            private int id;
            private int jobId;
            private String name;
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

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
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

        public static class VoWuliaodanItemListBean {
            /**
             * brand : 哈哈
             * csProjectId : 2
             * fenliaoCount : 0
             * fenliaoStatus : waitFen
             * id : 22
             * name : 西瓜
             * reciveCount : 0
             * reciveDateStr :
             * recivePrice : 0
             * reciveStatus : wait
             * reciveTotalPrice : 0
             * remarks :
             * restCount : 0
             * sendCount : 50
             * sendPrice : 0
             * sendTotalPrice : 0
             * specifications : AS-20
             * unitName : 吨
             * wuliaodanId : 30
             */

            private String brand;
            private int csProjectId;
            private int fenliaoCount;
            private String fenliaoStatus;
            private int id;
            private String name;
            private int reciveCount;
            private String reciveDateStr;
            private int recivePrice;
            private String reciveStatus;
            private int reciveTotalPrice;
            private String remarks;
            private int restCount;
            private int sendCount;
            private int sendPrice;
            private int sendTotalPrice;
            private String specifications;
            private String unitName;
            private int wuliaodanId;

            public String getBrand() {
                return brand;
            }

            public void setBrand(String brand) {
                this.brand = brand;
            }

            public int getCsProjectId() {
                return csProjectId;
            }

            public void setCsProjectId(int csProjectId) {
                this.csProjectId = csProjectId;
            }

            public int getFenliaoCount() {
                return fenliaoCount;
            }

            public void setFenliaoCount(int fenliaoCount) {
                this.fenliaoCount = fenliaoCount;
            }

            public String getFenliaoStatus() {
                return fenliaoStatus;
            }

            public void setFenliaoStatus(String fenliaoStatus) {
                this.fenliaoStatus = fenliaoStatus;
            }

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

            public int getReciveCount() {
                return reciveCount;
            }

            public void setReciveCount(int reciveCount) {
                this.reciveCount = reciveCount;
            }

            public String getReciveDateStr() {
                return reciveDateStr;
            }

            public void setReciveDateStr(String reciveDateStr) {
                this.reciveDateStr = reciveDateStr;
            }

            public int getRecivePrice() {
                return recivePrice;
            }

            public void setRecivePrice(int recivePrice) {
                this.recivePrice = recivePrice;
            }

            public String getReciveStatus() {
                return reciveStatus;
            }

            public void setReciveStatus(String reciveStatus) {
                this.reciveStatus = reciveStatus;
            }

            public int getReciveTotalPrice() {
                return reciveTotalPrice;
            }

            public void setReciveTotalPrice(int reciveTotalPrice) {
                this.reciveTotalPrice = reciveTotalPrice;
            }

            public String getRemarks() {
                return remarks;
            }

            public void setRemarks(String remarks) {
                this.remarks = remarks;
            }

            public int getRestCount() {
                return restCount;
            }

            public void setRestCount(int restCount) {
                this.restCount = restCount;
            }

            public int getSendCount() {
                return sendCount;
            }

            public void setSendCount(int sendCount) {
                this.sendCount = sendCount;
            }

            public int getSendPrice() {
                return sendPrice;
            }

            public void setSendPrice(int sendPrice) {
                this.sendPrice = sendPrice;
            }

            public int getSendTotalPrice() {
                return sendTotalPrice;
            }

            public void setSendTotalPrice(int sendTotalPrice) {
                this.sendTotalPrice = sendTotalPrice;
            }

            public String getSpecifications() {
                return specifications;
            }

            public void setSpecifications(String specifications) {
                this.specifications = specifications;
            }

            public String getUnitName() {
                return unitName;
            }

            public void setUnitName(String unitName) {
                this.unitName = unitName;
            }

            public int getWuliaodanId() {
                return wuliaodanId;
            }

            public void setWuliaodanId(int wuliaodanId) {
                this.wuliaodanId = wuliaodanId;
            }
        }
    }

    public static class ProjectListBean {
        /**
         * concatPerson : 老张
         * concatPersonPhone : 1875426359
         * createDateStr : 2017-07-21 15:50:55
         * id : 2
         * info : 1
         * locationStr : 沈阳
         * markedNum : FX0002
         * name : 测试项目23
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
