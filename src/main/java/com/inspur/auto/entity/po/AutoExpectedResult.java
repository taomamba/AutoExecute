package com.inspur.auto.entity.po;

import javax.persistence.Id;

import javax.persistence.Column;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.Date;

@Table(name = "t_auto_expected_result")
public class AutoExpectedResult implements Serializable {
    @Id
    private String id;

    @Column(name = "plat_form_id")
    private String platFormId;

    @Column(name = "case_code")
    private String caseCode;

    @Column(name = "excepted_result")
    private String exceptedResult;

    private String note;

    @Column(name = "create_time")
    private Date createTime;

    @Column(name = "update_time")
    private Date updateTime;

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
     * @return excepted_result
     */
    public String getExceptedResult() {
        return exceptedResult;
    }

    /**
     * @param exceptedResult
     */
    public void setExceptedResult(String exceptedResult) {
        this.exceptedResult = exceptedResult;
    }

    /**
     * @return note
     */
    public String getNote() {
        return note;
    }

    /**
     * @param note
     */
    public void setNote(String note) {
        this.note = note;
    }

    /**
     * @return create_time
     */
    public Date getCreateTime() {
        return createTime;
    }

    /**
     * @param createTime
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    /**
     * @return update_time
     */
    public Date getUpdateTime() {
        return updateTime;
    }

    /**
     * @param updateTime
     */
    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
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
        sb.append(", exceptedResult=").append(exceptedResult);
        sb.append(", note=").append(note);
        sb.append(", createTime=").append(createTime);
        sb.append(", updateTime=").append(updateTime);
        sb.append("]");
        return sb.toString();
    }
}