package frm.base;

import lombok.Data;

/**
 * 系统的返回数据结构
 * @param <T>
 */
@Data
public class ResponseMessage<T> {
    private Integer errCode;
    private String errMessage;
    private T data;
}
