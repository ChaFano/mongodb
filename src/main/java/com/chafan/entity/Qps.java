package com.chafan.entity;

/**
 * @Auther: 茶凡
 * @ClassName Progress
 * @date 2023/11/3 0:38
 * @Description TODO
 */
public class Qps {

    int number;

    double time_consume;

    double qps;

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public double getTime_consume() {
        return time_consume;
    }

    public void setTime_consume(double time_consume) {
        this.time_consume = time_consume;
    }

    public double getQps() {
        return qps;
    }

    public void setQps(double qps) {
        this.qps = qps;
    }

    @Override
    public String toString() {
        return "Progress2{" +
                "number=" + number +
                ", time_consume=" + time_consume +
                ", qps=" + qps +
                '}';
    }


}
