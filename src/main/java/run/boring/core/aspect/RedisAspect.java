package run.boring.core.aspect;

import run.boring.core.exception.AException;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;

/**
 * Redis切面处理类
 */
//@Aspect
//@Component
@Deprecated
public class RedisAspect {
    private Logger logger = LoggerFactory.getLogger(getClass());
    //是否开启redis缓存  true开启   false关闭
    @Value("${boring.redis.open: false}")
    private boolean open;

    public boolean isOpen() {
        return open;
    }

    @Around("execution(* run.boring.core.utils.RedisUtils.*(..))")
    public Object around(ProceedingJoinPoint point) throws Throwable {
        Object result = null;
        if(open){
            try{
                result = point.proceed();
            }catch (Exception e){
                logger.error("redis error", e);
                throw new AException("Redis服务异常");
            }
        }
        return result;
    }
}
