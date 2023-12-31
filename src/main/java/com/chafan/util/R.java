package com.chafan.util;

import java.util.HashMap;
import java.util.Map;

/**
 * @Auther: 茶凡
 * @ClassName R
 * @date 2023/10/30 10:58
 * @Description 返回给前端的 统一返回工具类
 */
public class R extends HashMap<String, Object> {
    private static final long serialVersionUID = 1L;

    public R setData(Object data){
        put("data",data);
        return this;
    }
    public R setData(Object data,Long count){
        put("data",data);
        put("count",count);
        return this;
    }

    public R(){
        put("code",0);
        put("msg","success");
    }

    public static R error(int code, String msg) {
        R r = new R();
        r.put("code", code);
        r.put("msg", msg);
        return r;
    }

    public static R ok(String msg) {
        R r = new R();
        r.put("msg", msg);
        return r;
    }

    public static R ok(Map<String, Object> map) {
        R r = new R();
        r.putAll(map);
        return r;
    }

    public static R ok() {
        return new R();
    }

    public R put(String key, Object value) {
        super.put(key, value);
        return this;
    }

    public Integer getCode(){
        return (Integer)this.get("code");
    }

}
