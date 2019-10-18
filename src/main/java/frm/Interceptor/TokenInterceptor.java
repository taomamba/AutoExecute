package frm.Interceptor;

import frm.annotation.Token;
import frm.util.TokenProcessor;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.Method;

/**
 * 拦截注解中含有@Token
 * @author taoyucan
 */
@Component
public class TokenInterceptor extends HandlerInterceptorAdapter {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        if (handler instanceof HandlerMethod){
            HandlerMethod handlerMethod = (HandlerMethod)handler;
            Method method = handlerMethod.getMethod();
            Token annotation = method.getAnnotation(Token.class);
            if (null != annotation){
                boolean needSaveSession = annotation.save();
                if (needSaveSession){
                    request.getSession(false).setAttribute("token", TokenProcessor.getInstance().generateToken());
                }
                boolean needRemoveSession = annotation.remove();
                if (needRemoveSession){
                    if (TokenProcessor.getInstance().RepeatSubmit(request)){
                        return false;
                    }
                    request.getSession(false).removeAttribute("token");
                }
            }
            return true;
        }
        return super.preHandle(request, response, handler);
    }
}
