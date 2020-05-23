//package cn.prosayj.blog.core.util.notice.mq.aspect;
//
//import cn.prosayj.blog.core.util.RabbitMqUtils;
//import cn.prosayj.blog.core.util.notice.mq.RabbitMqConstants;
//import cn.prosayj.blog.core.util.notice.mq.annotation.RefreshEsMqSender;
//import org.aspectj.lang.ProceedingJoinPoint;
//import org.aspectj.lang.annotation.Around;
//import org.aspectj.lang.annotation.Aspect;
//import org.aspectj.lang.annotation.Pointcut;
//import org.aspectj.lang.reflect.MethodSignature;
//import org.springframework.stereotype.Component;
//
//import javax.annotation.Resource;
//import java.lang.reflect.Method;
//
///**
// * RefreshEsMqAspect
// *
// * @author prosayj@gmail.com
// * @date 2020-05-21 下午 01:48
// * @since 1.0.0
// */
//@Aspect
//@Component
//public class RefreshEsMqAspect {
//
//    @Resource
//    private RabbitMqUtils rabbitMqUtils;
//
//    @Pointcut("@annotation(cn.prosayj.blog.core.util.notice.mq.annotation.RefreshEsMqSender)")
//    public void pointCut() {
//
//    }
//
//    @Around("pointCut()")
//    public Object around(ProceedingJoinPoint point) throws Throwable {
//        //执行方法
//        Object result = point.proceed();
//        MethodSignature signature = (MethodSignature) point.getSignature();
//        Method method = signature.getMethod();
//        RefreshEsMqSender senderAnnotation = method.getAnnotation(RefreshEsMqSender.class);
//        // 发送刷新信息
//        rabbitMqUtils.send(RabbitMqConstants.REFRESH_ES_INDEX_QUEUE, senderAnnotation.sender() + " " + senderAnnotation.msg());
//        return result;
//    }
//}
