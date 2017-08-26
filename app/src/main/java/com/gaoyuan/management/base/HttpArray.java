package com.gaoyuan.management.base;

import java.util.List;

/**
 * Created by admin on 2017/3/27.
 *
 */

public class HttpArray<T> {
    public String status;
    public String errorMessage;
    public List<T> results;
}
