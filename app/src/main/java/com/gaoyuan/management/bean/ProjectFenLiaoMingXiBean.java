package com.gaoyuan.management.bean;

import java.util.List;

/**
 * Created by gaoyuan on 2017/7/25.
 */

public class ProjectFenLiaoMingXiBean {


    /**
     * pager : {"currentPage":1,"currentPageRowCount":1,"nextPage":0,"pageSize":30,"startRow":0,"totalPages":1,"totalRows":1}
     * project : {"concatPerson":"鑰佸紶","concatPersonPhone":"1875426359","createDateStr":"2017-07-21 15:50:55","id":2,"info":"1","locationStr":"娌堥槼","markedNum":"FX0002","name":"娴嬭瘯椤圭洰23","status":"open"}
     * wuliaodan : {"createDateStr":"2017-07-22 23:12:31","csProjectId":2,"fenliaoStatus":"fening","gonghuoUserId":25,"id":44,"reciveDateStr":"2017-07-23 00:15:04","reciveMarkedNum":"SH20170723001","sendDateStr":"2017-07-22 23:12:31","sendMarkedNum":"FH20170722004","signRecive":"img/sign/1500740065324.jpg","signSend":"img/userOther/1500736349353.jpg","status":"recived","voCanModOrDel":"NO","voCsProject":{"concatPerson":"鑰佸紶","concatPersonPhone":"1875426359","createDateStr":"2017-07-21 15:50:55","id":2,"info":"1","locationStr":"娌堥槼","markedNum":"FX0002","name":"娴嬭瘯椤圭洰23","status":"open"},"voGonghuoshangUser":{"company":"gy","companyId":0,"csProjectId":0,"headportrait":"http://static.024pm.com/cscec/img/userHead/1500603916461.jpg","id":25,"jobId":0,"name":"supply2","nickName":"supply2","registDateStr":"2017-07-21 09:51:52","shouquanweituoshu":"","status":"ok","telPhone":"14763766684","userType":"gonghuoshang","voCompanyName":"","voCsProjectName":"","voJobName":"","yingyezhizhao":"img/userOther/1500601882499.jpg"},"voImages":[],"voWuliaodanItemList":[],"voWuziguanliUser":null,"wuziguanliUserId":30}
     * gonghuoshangUser : {"company":"gy","companyId":0,"csProjectId":0,"headportrait":"http://static.024pm.com/cscec/img/userHead/1500603916461.jpg","id":25,"jobId":0,"nickName":"supply2","registDateStr":"2017-07-21 09:51:52","shouquanweituoshu":"","status":"ok","telPhone":"14763766684","userType":"gonghuoshang","voCompanyName":"","voCsProjectName":"","voJobName":"","yingyezhizhao":"img/userOther/1500601882499.jpg"}
     * fenliaodanList : [{"csProjectId":2,"fenbaoshangUserId":36,"id":1575,"reciveDateStr":"","sendDateStr":"2017-07-24 23:40:06","sendWuziguanliUserId":30,"shouliaoWuziguanliUserId":30,"signFenbaoshang":"","signWuziguanli":"img/sign/1500910804762.jpg","status":"waitRecive","voCsProject":null,"voFenbaoshangUser":{"company":"","companyId":1,"csProjectId":2,"headportrait":"http://static.024pm.com/cscec/img/userHead/default.png","id":36,"jobId":0,"name":"fenbaoshang0","nickName":"fenbaoshang0","registDateStr":"2017-07-22 02:10:37","shouquanweituoshu":"","status":"ok","telPhone":"18523447654","userType":"fenbaoshang","voCompanyName":"","voCsProjectName":"","voJobName":"","yingyezhizhao":""},"voFenliaodanItemList":[{"brand":"e","csProjectId":2,"fenliaoCount":1,"fenliaodanId":1575,"fromWuliaodanItemId":31,"id":3142,"name":"1","recivePrice":0,"reciveTotalPrice":0,"sendPrice":0,"sendTotalPrice":0,"specifications":"e","unitName":"w"},{"brand":"c","csProjectId":2,"fenliaoCount":2,"fenliaodanId":1575,"fromWuliaodanItemId":33,"id":3143,"name":"j","recivePrice":0,"reciveTotalPrice":0,"sendPrice":0,"sendTotalPrice":0,"specifications":"j","unitName":"g"},{"brand":"g","csProjectId":2,"fenliaoCount":3,"fenliaodanId":1575,"fromWuliaodanItemId":35,"id":3144,"name":"b","recivePrice":0,"reciveTotalPrice":0,"sendPrice":0,"sendTotalPrice":0,"specifications":"h","unitName":"h"}],"voGonghuoshangUser":null,"voSendWuziguanliUser":{"company":"","companyId":0,"csProjectId":2,"headportrait":"http://static.024pm.com/cscec/img/userHead/default.png","id":30,"jobId":1,"name":"manager2","nickName":"manager2","registDateStr":"2017-07-21 20:21:50","shouquanweituoshu":"鎺堟潈濮旀墭涔﹀浘鐗�","status":"ok","telPhone":"14763766888","userType":"wuziguanliren","voCompanyName":"","voCsProjectName":"","voJobName":"","yingyezhizhao":""},"voShouliaoWuziguanliUser":null,"voWuliaodan":null,"wuliaodanId":44}]
     */

    private PagerBean pager;
    private ProjectBean project;
    private WuliaodanBean wuliaodan;
    private GonghuoshangUserBean gonghuoshangUser;
    private List<FenliaodanListBean> fenliaodanList;

    public PagerBean getPager() {
        return pager;
    }

    public void setPager(PagerBean pager) {
        this.pager = pager;
    }

    public ProjectBean getProject() {
        return project;
    }

    public void setProject(ProjectBean project) {
        this.project = project;
    }

    public WuliaodanBean getWuliaodan() {
        return wuliaodan;
    }

    public void setWuliaodan(WuliaodanBean wuliaodan) {
        this.wuliaodan = wuliaodan;
    }

    public GonghuoshangUserBean getGonghuoshangUser() {
        return gonghuoshangUser;
    }

    public void setGonghuoshangUser(GonghuoshangUserBean gonghuoshangUser) {
        this.gonghuoshangUser = gonghuoshangUser;
    }

    public List<FenliaodanListBean> getFenliaodanList() {
        return fenliaodanList;
    }

    public void setFenliaodanList(List<FenliaodanListBean> fenliaodanList) {
        this.fenliaodanList = fenliaodanList;
    }

    public static class PagerBean {
        /**
         * currentPage : 1
         * currentPageRowCount : 1
         * nextPage : 0
         * pageSize : 30
         * startRow : 0
         * totalPages : 1
         * totalRows : 1
         */

        private int currentPage;
        private int currentPageRowCount;
        private int nextPage;
        private int pageSize;
        private int startRow;
        private int totalPages;
        private int totalRows;

        public int getCurrentPage() {
            return currentPage;
        }

        public void setCurrentPage(int currentPage) {
            this.currentPage = currentPage;
        }

        public int getCurrentPageRowCount() {
            return currentPageRowCount;
        }

        public void setCurrentPageRowCount(int currentPageRowCount) {
            this.currentPageRowCount = currentPageRowCount;
        }

        public int getNextPage() {
            return nextPage;
        }

        public void setNextPage(int nextPage) {
            this.nextPage = nextPage;
        }

        public int getPageSize() {
            return pageSize;
        }

        public void setPageSize(int pageSize) {
            this.pageSize = pageSize;
        }

        public int getStartRow() {
            return startRow;
        }

        public void setStartRow(int startRow) {
            this.startRow = startRow;
        }

        public int getTotalPages() {
            return totalPages;
        }

        public void setTotalPages(int totalPages) {
            this.totalPages = totalPages;
        }

        public int getTotalRows() {
            return totalRows;
        }

        public void setTotalRows(int totalRows) {
            this.totalRows = totalRows;
        }
    }

    public static class ProjectBean {
        /**
         * concatPerson : 鑰佸紶
         * concatPersonPhone : 1875426359
         * createDateStr : 2017-07-21 15:50:55
         * id : 2
         * info : 1
         * locationStr : 娌堥槼
         * markedNum : FX0002
         * name : 娴嬭瘯椤圭洰23
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

    public static class WuliaodanBean {
        /**
         * createDateStr : 2017-07-22 23:12:31
         * csProjectId : 2
         * fenliaoStatus : fening
         * gonghuoUserId : 25
         * id : 44
         * reciveDateStr : 2017-07-23 00:15:04
         * reciveMarkedNum : SH20170723001
         * sendDateStr : 2017-07-22 23:12:31
         * sendMarkedNum : FH20170722004
         * signRecive : img/sign/1500740065324.jpg
         * signSend : img/userOther/1500736349353.jpg
         * status : recived
         * voCanModOrDel : NO
         * voCsProject : {"concatPerson":"鑰佸紶","concatPersonPhone":"1875426359","createDateStr":"2017-07-21 15:50:55","id":2,"info":"1","locationStr":"娌堥槼","markedNum":"FX0002","name":"娴嬭瘯椤圭洰23","status":"open"}
         * voGonghuoshangUser : {"company":"gy","companyId":0,"csProjectId":0,"headportrait":"http://static.024pm.com/cscec/img/userHead/1500603916461.jpg","id":25,"jobId":0,"name":"supply2","nickName":"supply2","registDateStr":"2017-07-21 09:51:52","shouquanweituoshu":"","status":"ok","telPhone":"14763766684","userType":"gonghuoshang","voCompanyName":"","voCsProjectName":"","voJobName":"","yingyezhizhao":"img/userOther/1500601882499.jpg"}
         * voImages : []
         * voWuliaodanItemList : []
         * voWuziguanliUser : null
         * wuziguanliUserId : 30
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
        private List<?> voWuliaodanItemList;

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

        public List<?> getVoWuliaodanItemList() {
            return voWuliaodanItemList;
        }

        public void setVoWuliaodanItemList(List<?> voWuliaodanItemList) {
            this.voWuliaodanItemList = voWuliaodanItemList;
        }

        public static class VoCsProjectBean {
            /**
             * concatPerson : 鑰佸紶
             * concatPersonPhone : 1875426359
             * createDateStr : 2017-07-21 15:50:55
             * id : 2
             * info : 1
             * locationStr : 娌堥槼
             * markedNum : FX0002
             * name : 娴嬭瘯椤圭洰23
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
    }

    public static class GonghuoshangUserBean {
        /**
         * company : gy
         * companyId : 0
         * csProjectId : 0
         * headportrait : http://static.024pm.com/cscec/img/userHead/1500603916461.jpg
         * id : 25
         * jobId : 0
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

    public static class FenliaodanListBean {
        /**
         * csProjectId : 2
         * fenbaoshangUserId : 36
         * id : 1575
         * reciveDateStr :
         * sendDateStr : 2017-07-24 23:40:06
         * sendWuziguanliUserId : 30
         * shouliaoWuziguanliUserId : 30
         * signFenbaoshang :
         * signWuziguanli : img/sign/1500910804762.jpg
         * status : waitRecive
         * voCsProject : null
         * voFenbaoshangUser : {"company":"","companyId":1,"csProjectId":2,"headportrait":"http://static.024pm.com/cscec/img/userHead/default.png","id":36,"jobId":0,"name":"fenbaoshang0","nickName":"fenbaoshang0","registDateStr":"2017-07-22 02:10:37","shouquanweituoshu":"","status":"ok","telPhone":"18523447654","userType":"fenbaoshang","voCompanyName":"","voCsProjectName":"","voJobName":"","yingyezhizhao":""}
         * voFenliaodanItemList : [{"brand":"e","csProjectId":2,"fenliaoCount":1,"fenliaodanId":1575,"fromWuliaodanItemId":31,"id":3142,"name":"1","recivePrice":0,"reciveTotalPrice":0,"sendPrice":0,"sendTotalPrice":0,"specifications":"e","unitName":"w"},{"brand":"c","csProjectId":2,"fenliaoCount":2,"fenliaodanId":1575,"fromWuliaodanItemId":33,"id":3143,"name":"j","recivePrice":0,"reciveTotalPrice":0,"sendPrice":0,"sendTotalPrice":0,"specifications":"j","unitName":"g"},{"brand":"g","csProjectId":2,"fenliaoCount":3,"fenliaodanId":1575,"fromWuliaodanItemId":35,"id":3144,"name":"b","recivePrice":0,"reciveTotalPrice":0,"sendPrice":0,"sendTotalPrice":0,"specifications":"h","unitName":"h"}]
         * voGonghuoshangUser : null
         * voSendWuziguanliUser : {"company":"","companyId":0,"csProjectId":2,"headportrait":"http://static.024pm.com/cscec/img/userHead/default.png","id":30,"jobId":1,"name":"manager2","nickName":"manager2","registDateStr":"2017-07-21 20:21:50","shouquanweituoshu":"鎺堟潈濮旀墭涔﹀浘鐗�","status":"ok","telPhone":"14763766888","userType":"wuziguanliren","voCompanyName":"","voCsProjectName":"","voJobName":"","yingyezhizhao":""}
         * voShouliaoWuziguanliUser : null
         * voWuliaodan : null
         * wuliaodanId : 44
         */

        private int csProjectId;
        private int fenbaoshangUserId;
        private int id;
        private String reciveDateStr;
        private String sendDateStr;
        private int sendWuziguanliUserId;
        private int shouliaoWuziguanliUserId;
        private String signFenbaoshang;
        private String signWuziguanli;
        private String status;
        private Object voCsProject;
        private VoFenbaoshangUserBean voFenbaoshangUser;
        private Object voGonghuoshangUser;
        private VoSendWuziguanliUserBean voSendWuziguanliUser;
        private Object voShouliaoWuziguanliUser;
        private Object voWuliaodan;
        private int wuliaodanId;
        private List<VoFenliaodanItemListBean> voFenliaodanItemList;

        public int getCsProjectId() {
            return csProjectId;
        }

        public void setCsProjectId(int csProjectId) {
            this.csProjectId = csProjectId;
        }

        public int getFenbaoshangUserId() {
            return fenbaoshangUserId;
        }

        public void setFenbaoshangUserId(int fenbaoshangUserId) {
            this.fenbaoshangUserId = fenbaoshangUserId;
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

        public String getSendDateStr() {
            return sendDateStr;
        }

        public void setSendDateStr(String sendDateStr) {
            this.sendDateStr = sendDateStr;
        }

        public int getSendWuziguanliUserId() {
            return sendWuziguanliUserId;
        }

        public void setSendWuziguanliUserId(int sendWuziguanliUserId) {
            this.sendWuziguanliUserId = sendWuziguanliUserId;
        }

        public int getShouliaoWuziguanliUserId() {
            return shouliaoWuziguanliUserId;
        }

        public void setShouliaoWuziguanliUserId(int shouliaoWuziguanliUserId) {
            this.shouliaoWuziguanliUserId = shouliaoWuziguanliUserId;
        }

        public String getSignFenbaoshang() {
            return signFenbaoshang;
        }

        public void setSignFenbaoshang(String signFenbaoshang) {
            this.signFenbaoshang = signFenbaoshang;
        }

        public String getSignWuziguanli() {
            return signWuziguanli;
        }

        public void setSignWuziguanli(String signWuziguanli) {
            this.signWuziguanli = signWuziguanli;
        }

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }

        public Object getVoCsProject() {
            return voCsProject;
        }

        public void setVoCsProject(Object voCsProject) {
            this.voCsProject = voCsProject;
        }

        public VoFenbaoshangUserBean getVoFenbaoshangUser() {
            return voFenbaoshangUser;
        }

        public void setVoFenbaoshangUser(VoFenbaoshangUserBean voFenbaoshangUser) {
            this.voFenbaoshangUser = voFenbaoshangUser;
        }

        public Object getVoGonghuoshangUser() {
            return voGonghuoshangUser;
        }

        public void setVoGonghuoshangUser(Object voGonghuoshangUser) {
            this.voGonghuoshangUser = voGonghuoshangUser;
        }

        public VoSendWuziguanliUserBean getVoSendWuziguanliUser() {
            return voSendWuziguanliUser;
        }

        public void setVoSendWuziguanliUser(VoSendWuziguanliUserBean voSendWuziguanliUser) {
            this.voSendWuziguanliUser = voSendWuziguanliUser;
        }

        public Object getVoShouliaoWuziguanliUser() {
            return voShouliaoWuziguanliUser;
        }

        public void setVoShouliaoWuziguanliUser(Object voShouliaoWuziguanliUser) {
            this.voShouliaoWuziguanliUser = voShouliaoWuziguanliUser;
        }

        public Object getVoWuliaodan() {
            return voWuliaodan;
        }

        public void setVoWuliaodan(Object voWuliaodan) {
            this.voWuliaodan = voWuliaodan;
        }

        public int getWuliaodanId() {
            return wuliaodanId;
        }

        public void setWuliaodanId(int wuliaodanId) {
            this.wuliaodanId = wuliaodanId;
        }

        public List<VoFenliaodanItemListBean> getVoFenliaodanItemList() {
            return voFenliaodanItemList;
        }

        public void setVoFenliaodanItemList(List<VoFenliaodanItemListBean> voFenliaodanItemList) {
            this.voFenliaodanItemList = voFenliaodanItemList;
        }

        public static class VoFenbaoshangUserBean {
            /**
             * company :
             * companyId : 1
             * csProjectId : 2
             * headportrait : http://static.024pm.com/cscec/img/userHead/default.png
             * id : 36
             * jobId : 0
             * name : fenbaoshang0
             * nickName : fenbaoshang0
             * registDateStr : 2017-07-22 02:10:37
             * shouquanweituoshu :
             * status : ok
             * telPhone : 18523447654
             * userType : fenbaoshang
             * voCompanyName :
             * voCsProjectName :
             * voJobName :
             * yingyezhizhao :
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

        public static class VoSendWuziguanliUserBean {
            /**
             * company :
             * companyId : 0
             * csProjectId : 2
             * headportrait : http://static.024pm.com/cscec/img/userHead/default.png
             * id : 30
             * jobId : 1
             * name : manager2
             * nickName : manager2
             * registDateStr : 2017-07-21 20:21:50
             * shouquanweituoshu : 鎺堟潈濮旀墭涔﹀浘鐗�
             * status : ok
             * telPhone : 14763766888
             * userType : wuziguanliren
             * voCompanyName :
             * voCsProjectName :
             * voJobName :
             * yingyezhizhao :
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

        public static class VoFenliaodanItemListBean {
            /**
             * brand : e
             * csProjectId : 2
             * fenliaoCount : 1
             * fenliaodanId : 1575
             * fromWuliaodanItemId : 31
             * id : 3142
             * name : 1
             * recivePrice : 0
             * reciveTotalPrice : 0
             * sendPrice : 0
             * sendTotalPrice : 0
             * specifications : e
             * unitName : w
             */

            private String brand;
            private int csProjectId;
            private int fenliaoCount;
            private int fenliaodanId;
            private int fromWuliaodanItemId;
            private int id;
            private String name;
            private int recivePrice;
            private int reciveTotalPrice;
            private int sendPrice;
            private int sendTotalPrice;
            private String specifications;
            private String unitName;

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

            public int getFenliaodanId() {
                return fenliaodanId;
            }

            public void setFenliaodanId(int fenliaodanId) {
                this.fenliaodanId = fenliaodanId;
            }

            public int getFromWuliaodanItemId() {
                return fromWuliaodanItemId;
            }

            public void setFromWuliaodanItemId(int fromWuliaodanItemId) {
                this.fromWuliaodanItemId = fromWuliaodanItemId;
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

            public int getRecivePrice() {
                return recivePrice;
            }

            public void setRecivePrice(int recivePrice) {
                this.recivePrice = recivePrice;
            }

            public int getReciveTotalPrice() {
                return reciveTotalPrice;
            }

            public void setReciveTotalPrice(int reciveTotalPrice) {
                this.reciveTotalPrice = reciveTotalPrice;
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
        }
    }
}
