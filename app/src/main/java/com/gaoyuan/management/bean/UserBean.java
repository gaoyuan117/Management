package com.gaoyuan.management.bean;

/**
 * Created by gaoyuan on 2017/7/17.
 */

public class UserBean {


    /**
     * status : ok
     * errorMessage :
     * results : {"company":"tt","companyId":0,"csProjectId":0,"headportrait":"http://static.024pm.com/cscec/img/userHead/default.png","id":8,"jobId":0,"name":"supply","nickName":"","password":"123456","registDate":{"date":16,"day":0,"hours":21,"minutes":0,"month":6,"nanos":0,"seconds":40,"time":1500210040000,"timezoneOffset":-480,"year":117},"shouquanweituoshu":"","status":"ok","telPhone":"14763766681","userType":"gonghuoshang","voCompanyName":"","voCsProjectName":"","voJobName":"","yingyezhizhao":"钀ヤ笟鎵х収鍥剧墖"}
     */

    private String status;
    private String errorMessage;
    private ResultsBean results;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    public ResultsBean getResults() {
        return results;
    }

    public void setResults(ResultsBean results) {
        this.results = results;
    }

    public static class ResultsBean {
        /**
         * company : tt
         * companyId : 0
         * csProjectId : 0
         * headportrait : http://static.024pm.com/cscec/img/userHead/default.png
         * id : 8
         * jobId : 0
         * name : supply
         * nickName :
         * password : 123456
         * registDate : {"date":16,"day":0,"hours":21,"minutes":0,"month":6,"nanos":0,"seconds":40,"time":1500210040000,"timezoneOffset":-480,"year":117}
         * shouquanweituoshu :
         * status : ok
         * telPhone : 14763766681
         * userType : gonghuoshang
         * voCompanyName :
         * voCsProjectName :
         * voJobName :
         * yingyezhizhao : 钀ヤ笟鎵х収鍥剧墖
         */

        private String company;
        private int companyId;
        private int csProjectId;
        private String headportrait;
        private int id;
        private int jobId;
        private String name;
        private String nickName;
        private String password;
        private RegistDateBean registDate;
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

        public String getPassword() {
            return password;
        }

        public void setPassword(String password) {
            this.password = password;
        }

        public RegistDateBean getRegistDate() {
            return registDate;
        }

        public void setRegistDate(RegistDateBean registDate) {
            this.registDate = registDate;
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

        public static class RegistDateBean {
            /**
             * date : 16
             * day : 0
             * hours : 21
             * minutes : 0
             * month : 6
             * nanos : 0
             * seconds : 40
             * time : 1500210040000
             * timezoneOffset : -480
             * year : 117
             */

            private int date;
            private int day;
            private int hours;
            private int minutes;
            private int month;
            private int nanos;
            private int seconds;
            private long time;
            private int timezoneOffset;
            private int year;

            public int getDate() {
                return date;
            }

            public void setDate(int date) {
                this.date = date;
            }

            public int getDay() {
                return day;
            }

            public void setDay(int day) {
                this.day = day;
            }

            public int getHours() {
                return hours;
            }

            public void setHours(int hours) {
                this.hours = hours;
            }

            public int getMinutes() {
                return minutes;
            }

            public void setMinutes(int minutes) {
                this.minutes = minutes;
            }

            public int getMonth() {
                return month;
            }

            public void setMonth(int month) {
                this.month = month;
            }

            public int getNanos() {
                return nanos;
            }

            public void setNanos(int nanos) {
                this.nanos = nanos;
            }

            public int getSeconds() {
                return seconds;
            }

            public void setSeconds(int seconds) {
                this.seconds = seconds;
            }

            public long getTime() {
                return time;
            }

            public void setTime(long time) {
                this.time = time;
            }

            public int getTimezoneOffset() {
                return timezoneOffset;
            }

            public void setTimezoneOffset(int timezoneOffset) {
                this.timezoneOffset = timezoneOffset;
            }

            public int getYear() {
                return year;
            }

            public void setYear(int year) {
                this.year = year;
            }
        }
    }
}
