package com.inspur.auto.entity.po;

import javax.persistence.Id;

import javax.persistence.Column;
import javax.persistence.Table;
import java.io.Serializable;

@Table(name = "t_auto_request_message")
public class AutoRequestMessage implements Serializable {
    @Id
    private String id;

    @Column(name = "expected_result_id")
    private String expectedResultId;

    @Column(name = "end_point")
    private String endPoint;

    private String message;

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
     * @return expected_result_id
     */
    public String getExpectedResultId() {
        return expectedResultId;
    }

    /**
     * @param expectedResultId
     */
    public void setExpectedResultId(String expectedResultId) {
        this.expectedResultId = expectedResultId;
    }

    /**
     * @return end_point
     */
    public String getEndPoint() {
        return endPoint;
    }

    /**
     * @param endPoint
     */
    public void setEndPoint(String endPoint) {
        this.endPoint = endPoint;
    }

    /**
     * @return message
     */
    public String getMessage() {
        return message;
    }

    /**
     * @param message
     */
    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", expectedResultId=").append(expectedResultId);
        sb.append(", endPoint=").append(endPoint);
        sb.append(", message=").append(message);
        sb.append("]");
        return sb.toString();
    }
}