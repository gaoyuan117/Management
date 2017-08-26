package com.gaoyuan.management.bean;

import java.util.List;

/**
 * Created by gaoyuan on 2017/7/16.
 */

public class RegisterListBean {


    /**
     * status : ok
     * errorMessage :
     * results : {"companyList":[{"id":1,"info":"","name":"分包商单位A"}],"jobList":[{"id":1,"info":"","name":"职位A工程师"}],"projectList":[{"id":1,"name":"测试项目","status":"open"}]}
     */

    private List<CompanyListBean> companyList;
    private List<JobListBean> jobList;
    private List<ProjectListBean> projectList;

    public List<CompanyListBean> getCompanyList() {
        return companyList;
    }

    public void setCompanyList(List<CompanyListBean> companyList) {
        this.companyList = companyList;
    }

    public List<JobListBean> getJobList() {
        return jobList;
    }

    public void setJobList(List<JobListBean> jobList) {
        this.jobList = jobList;
    }

    public List<ProjectListBean> getProjectList() {
        return projectList;
    }

    public void setProjectList(List<ProjectListBean> projectList) {
        this.projectList = projectList;
    }

    public static class CompanyListBean {
        /**
         * id : 1
         * info :
         * name : 分包商单位A
         */

        private int id;
        private String info;
        private String name;

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

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }

    public static class JobListBean {
        /**
         * id : 1
         * info :
         * name : 职位A工程师
         */

        private int id;
        private String info;
        private String name;

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

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }

    public static class ProjectListBean {
        /**
         * id : 1
         * name : 测试项目
         * status : open
         */

        private int id;
        private String name;
        private String status;

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

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }
    }
}
