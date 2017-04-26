package com.lifetea.model;

import com.github.pagehelper.PageInfo;
import org.springframework.ui.ModelMap;

/**
 * Created by lifetea on 2017/4/10.
 */
public class BaseResult<T>{
    public BaseResult(){

    }

    public BaseResult(Integer code, String ok, Object t) {
        this.code           = code;
        this.msg            = msg;
        this.setData((T) t);
    }

    public BaseResult(Integer code, String msg) {
        this.code           = code;
        this.msg            = msg;
        ModelMap modelMap   = new ModelMap();
        this.setData((T) modelMap);
    }

    public BaseResult(Integer code, String msg, PageInfo<T> page) {
        this.code           = code;
        this.msg            = msg;
        ModelMap modelMap   = new ModelMap();

        modelMap.put("list"     ,page.getList());
        modelMap.put("pageNum",page.getPageNum());
        modelMap.put("pageSize",page.getPageSize());
//        modelMap.put("size",page.getSize());
        modelMap.put("total",page.getTotal());
        modelMap.put("startRow",page.getStartRow());
        modelMap.put("endRow",page.getEndRow());
        modelMap.put("size",page.getSize());
        modelMap.put("pages",page.getPages());


        this.setData((T) modelMap);
    }

    public static final Integer OK = 0;
    public static final Integer ERROR = 100;

    private Integer code;

    private String msg;

    private T data;

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }


}
