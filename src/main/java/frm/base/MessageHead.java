package frm.base;

import java.util.Map;

import lombok.Data;

@Data
public class MessageHead {
	public String channel;
	public String checksum;
	public Map exts;
	public String msg_id;
	public String msg_type;
	public String receiver;
	public String recv_sysname;
	public String send_sysname;
	public String send_time;
	public String sender;
	public String signature;

}
