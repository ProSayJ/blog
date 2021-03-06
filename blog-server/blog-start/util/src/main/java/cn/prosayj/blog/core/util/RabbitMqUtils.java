//package cn.prosayj.blog.core.util;
//
//import org.springframework.amqp.core.AmqpTemplate;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Component;
//
///**
// * RabbitMqUtils
// *
// * @author prosayj@gmail.com
// * @date 2020-05-21 下午 01:48
// * @since 1.0.0
// */
//@Component
//public class RabbitMqUtils {
//
//    @Autowired
//    private AmqpTemplate rabbitTemplate;
//
//    /**
//     * 发送到指定Queue
//     * @param queueName
//     * @param obj
//     */
//    public void send(String queueName, Object obj){
//        this.rabbitTemplate.convertAndSend(queueName, obj);
//    }
//}
