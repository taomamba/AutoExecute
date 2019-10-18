package com.inspur.auto.entity.po;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.Date;

@Table(name = "t_auto_actual_result")
public class AutoActualResult implements Serializable {
    @Id
    private String id;

    @Column(name = "plat_form_id")
    private String platFormId;

    @Column(name = "case_code")
    private String caseCode;

    @Column(name = "batch_id")
    private String batchId;

    @Column(name = "actual_result")
    private String actualResult;

    @Column(name = "excute_time")
    private Date excuteTime;

    private static final long serialVersionUID = 1L;

    /**
     * @return id
     */
    public String getId() {
        return id;
    }

    /**
     * @param id
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * @return plat_form_id
     */
    public String getPlatFormId() {
        return platFormId;
    }

    /**
     * @param platFormId
     */
    public void setPlatFormId(String platFormId) {
        this.platFormId = platFormId;
    }

    /**
     * @return case_code
     */
    public String getCaseCode() {
        return caseCode;
    }

    /**
     * @param caseCode
     */
    public void setCaseCode(String caseCode) {
        this.caseCode = caseCode;
    }

    /**
     * @return batch_id
     */
    public String getBatchId() {
        return batchId;
    }

    /**
     * @param batchId
     */
    public void setBatchId(String batchId) {
        this.batchId = batchId;
    }

    /**
     * @return actual_result
     */
    public String getActualResult() {
        return actualResult;
    }

    /**
     * @param actualResult
     */
    public void setActualResult(String actualResult) {
        this.actualResult = actualResult;
    }

    /**
     * @return excute_time
     */
    public Date getExcuteTime() {
        return excuteTime;
    }

    /**
     * @param excuteTime
     */
    public void setExcuteTime(Date excuteTime) {
        this.excuteTime = excuteTime;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", platFormId=").append(platFormId);
        sb.append(", caseCode=").append(caseCode);
        sb.append(", batchId=").append(batchId);
        sb.append(", actualResult=").append(actualResult);
        sb.append(", excuteTime=").append(excuteTime);
        sb.append("]");
        return sb.toString();
    }
}