package com.chafan.entity;

/**
 * @Auther: 茶凡
 * @ClassName NodeInfo
 * @date 2023/10/30 8:42
 * @Description 节点详细信息
 */
public class NodeInformation {

    private String id;
    private String name;
    private String health;
    private String state;
    private String stateStr;

    public NodeInformation() {
    }

    public NodeInformation(String id, String name, String health, String state, String stateStr) {
        this.id = id;
        this.name = name;
        this.health = health;
        this.state = state;
        this.stateStr = stateStr;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getHealth() {
        return health;
    }

    public void setHealth(String health) {
        this.health = health;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getStateStr() {
        return stateStr;
    }

    public void setStateStr(String stateStr) {
        this.stateStr = stateStr;
    }

    @Override
    public String toString() {
        return "NodeInfo{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", health='" + health + '\'' +
                ", state=" + state +
                ", stateStr='" + stateStr + '\'' +
                '}';
    }

}
