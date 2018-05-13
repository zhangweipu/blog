package com.wp.weipu.entity;

import javax.persistence.*;

public class City {
    @Id
    @Column(name = "ID")
    private String id;

    @Column(name = "DSC")
    private String dsc;

    @Column(name = "PID")
    private String pid;

    /**
     * @return ID
     */
    public String getId() {
        return id;
    }

    /**
     * @param id
     */
    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    /**
     * @return DSC
     */
    public String getDsc() {
        return dsc;
    }

    /**
     * @param dsc
     */
    public void setDsc(String dsc) {
        this.dsc = dsc == null ? null : dsc.trim();
    }

    /**
     * @return PID
     */
    public String getPid() {
        return pid;
    }

    /**
     * @param pid
     */
    public void setPid(String pid) {
        this.pid = pid == null ? null : pid.trim();
    }
}