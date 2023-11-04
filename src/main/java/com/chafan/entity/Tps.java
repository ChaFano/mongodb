package com.chafan.entity;

/**
 * @Auther: 茶凡
 * @ClassName Progress
 * @date 2023/11/3 0:38
 * @Description TODO
 */
public class Tps {

    Long number;

    Long time_consume;

    float tps;


    public Long getNumber() {
        return number;
    }

    public void setNumber(Long number) {
        this.number = number;
    }

    public Long getTime_consume() {
        return time_consume;
    }

    public void setTime_consume(Long time_consume) {
        this.time_consume = time_consume;
    }

    public float getTps() {
        return tps;
    }

    public void setTps(float tps) {
        this.tps = tps;
    }

    @Override
    public String toString() {
        return "Progress{" +
                "number=" + number +
                ", time_consume=" + time_consume +
                ", tps=" + tps +
                '}';
    }
}
