package frm.base;

import lombok.Data;

import java.util.Map;

@Data
public class MessageBody {
	  public Map exts;
      public String rtnCode;
      public String rtnMesg;
      public String rtnTime;
      public String trnsId;
      public String trnsType;
      public String version;
}
