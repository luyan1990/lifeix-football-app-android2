package com.l99.chinafootball.bean;

/**
 * Created by lifeix-101 on 2016/7/8.
 */
public class CompetitionIntroduceBean {

    private String host_name;
    private String host_flag;
    private String away_name;
    private String away_flag;
    private long startDate;
    private long startTime;

    public CompetitionIntroduceBean(String host_name, String host_flag, String away_name, String away_flag, long startDate, long startTime) {
        this.host_name = host_name;
        this.host_flag = host_flag;
        this.away_name = away_name;
        this.away_flag = away_flag;
        this.startDate = startDate;
        this.startTime = startTime;
    }

    public String getHost_name() {
        return host_name;
    }

    public void setHost_name(String host_name) {
        this.host_name = host_name;
    }

    public String getHost_flag() {
        return host_flag;
    }

    public void setHost_flag(String host_flag) {
        this.host_flag = host_flag;
    }

    public String getAway_name() {
        return away_name;
    }

    public void setAway_name(String away_name) {
        this.away_name = away_name;
    }

    public String getAway_flag() {
        return away_flag;
    }

    public void setAway_flag(String away_flag) {
        this.away_flag = away_flag;
    }

    public long getStartDate() {
        return startDate;
    }

    public void setStartDate(long startDate) {
        this.startDate = startDate;
    }

    public long getStartTime() {
        return startTime;
    }

    public void setStartTime(long startTime) {
        this.startTime = startTime;
    }
}
