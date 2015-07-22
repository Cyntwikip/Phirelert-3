package com.cyntwikip.android.phirelert.model;

import java.util.Date;

/**
 * Created by Cyntwikip on 6/30/2015.
 */
public class Report {
    private ReportStatus status;
    private String location;
    private Date date;

    public Report(ReportStatus status, String location, Date date) {
        this.status = status;
        this.location = location;
        this.date = date;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public ReportStatus getStatus() {
        return status;
    }

    public void setStatus(ReportStatus status) {
        this.status = status;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

}
