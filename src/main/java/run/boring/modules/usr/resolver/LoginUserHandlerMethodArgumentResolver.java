package run.boring.modules.usr.resolver;

import run.boring.core.annotation.LoginUser;
import run.boring.handler.config.ResolverHandler;
import run.boring.modules.usr.interceptor.AuthorizationInterceptor;
import run.boring.modules.usr.entity.UserProfileEntity;
import run.boring.modules.usr.service.UserProfileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.MethodParameter;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

/**
 * 有@LoginUser注解的方法参数，注入当前登录用户
 */
@Component
public class LoginUserHandlerMethodArgumentResolver implements HandlerMethodArgumentResolver, ResolverHandler {
    @Autowired
    private UserProfileService userProfileService;

    @Override
    public boolean supportsParameter(MethodParameter parameter) {
        return parameter.getParameterType().isAssignableFrom(UserProfileEntity.class) && parameter.hasParameterAnnotation(LoginUser.class);
    }

    @Override
    public Object resolveArgument(MethodParameter parameter, ModelAndViewContainer container,
                                  NativeWebRequest request, WebDataBinderFactory factory) throws Exception {
        //获取用户ID
        Object object = request.getAttribute(AuthorizationInterceptor.USER_KEY, RequestAttributes.SCOPE_REQUEST);
        if (object == null) {
            return null;
        }

        //获取用户信息
        UserProfileEntity user = userProfileService.getByUuid((String) object);

        return user;
    }

    @Override
    public HandlerMethodArgumentResolver getResolver() {
        return this;
    }
}
