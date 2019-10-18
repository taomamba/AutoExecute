package frm.base;

import lombok.Getter;

@Getter
public class BlogException extends RuntimeException{
    private Integer errCode;
    public BlogException(){
        
    }


}
