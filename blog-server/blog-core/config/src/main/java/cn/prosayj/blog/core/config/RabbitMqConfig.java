//package cn.prosayj.blog.core.config;
//
//import cn.prosayj.blog.core.util.notice.mq.RabbitMqConstants;
//import org.springframework.amqp.core.Queue;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//
//
//
///**
// * RabbitMqConfig
// *
// * @author prosayj@gmail.com
// * @date 2020-05-21 下午 01:48
// * @since 1.0.0
// */
//@Configuration
//public class RabbitMqConfig {
//
//    @Bean
//    public Queue queue() {
//        return new Queue(RabbitMqConstants.REFRESH_ES_INDEX_QUEUE);
//    }
//}
