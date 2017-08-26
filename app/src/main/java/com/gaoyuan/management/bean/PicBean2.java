package com.gaoyuan.management.bean;

import java.util.List;

/**
 * Created by gaoyuan on 2017/8/9.
 */

public class PicBean2 {
    /**
     * url : img/userOther/1500601426535.jpg
     * title : image.jpg
     * state : SUCCESS
     */

    private List<String> url;
    private String title;
    private String state;

    public List<String> getUrl() {
        return url;
    }

    public void setUrl(List<String> url) {
        this.url = url;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }
}
