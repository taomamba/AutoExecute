package frm.base;

import java.util.List;

import lombok.Data;

/**
 *
 * auth:taoyc
 */
@Data
public class ResponseBody {
	public MessageHead head;
	public List<MessageBody> bodys;

}
