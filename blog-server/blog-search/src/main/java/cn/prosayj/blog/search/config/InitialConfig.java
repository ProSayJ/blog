//package cn.prosayj.blog.search.config;
//
//import cn.prosayj.blog.core.util.RabbitMqUtils;
//import cn.prosayj.blog.core.util.notice.mq.RabbitMqConstants;
//import com.rabbitmq.client.ConnectionFactory;
//import org.elasticsearch.client.ElasticsearchClient;
//import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
//import org.springframework.context.annotation.Configuration;
//
//import javax.annotation.PostConstruct;
//import javax.annotation.Resource;
//
///**
// * InitialConfig
// *
// * @author prosayj@gmail.com
// * @date 2020-05-21 下午 01:48
// * @since 1.0.0
// */
//@Configuration
//@ConditionalOnBean(ElasticsearchClient.class)
//public class InitialConfig {
//
//    @Resource
//    private RabbitMqUtils rabbitMqUtils;
//
//    /**
//     * 项目启动时重新导入索引
//     */
//    @PostConstruct
//    public void initEsIndex(){
//        rabbitMqUtils.send(RabbitMqConstants.REFRESH_ES_INDEX_QUEUE,"dbblog-search init index");
//        ConnectionFactory connectionFactory = new ConnectionFactory();
//        connectionFactory.setAutomaticRecoveryEnabled(false);
//    }
//}
