package com.inspur.auto.entity.po;

import javax.persistence.Id;

import javax.persistence.Column;
import javax.persistence.Table;
import java.io.Serializable;

@Table(name = "t_auto_response_message")
public class AutoResponseMessage implements Serializable {

    @Id
    private String id;

    @Column(name = "actual_result_id")
    private String actualResultId;

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
     * @return actual_result_id
     */
    public String getActualResultId() {
        return actualResultId;
    }

    /**
     * @param actualResultId
     */
    public void setActualResultId(String actualResultId) {
        this.actualResultId = actualResultId;
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
        sb.append(", actualResultId=").append(actualResultId);
        sb.append(", message=").append(message);
        sb.append("]");
        return sb.toString();
    }
}