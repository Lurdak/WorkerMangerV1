package lurdak.try3.Model;

import java.util.Date;

/**
 * Created by Lurdak on 6/7/2018.
 */

public class WorkOn {
    private int id;
    private String workerId;
    private String companyId;
    private Date startDate;
    private Date endDate;

    public WorkOn(int id) {
        this.id = id;
    }

    public WorkOn(int id, String workerId, String companyId, Date startDate, Date endDate) {
        this.id = id;
        this.workerId = workerId;
        this.companyId = companyId;
        this.startDate = startDate;
        this.endDate = endDate;
    }

    public WorkOn(int id, String workerId, String companyId) {
        this.id = id;
        this.workerId = workerId;
        this.companyId = companyId;
    }

    public WorkOn() {

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getWorkerId() {
        return workerId;
    }

    public void setWorkerId(String workerId) {
        this.workerId = workerId;
    }

    public String getCompanyId() {
        return companyId;
    }

    public void setCompanyId(String companyId) {
        this.companyId = companyId;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }
}
