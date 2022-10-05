package com.javatechbd.productservice.config;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.TopicBuilder;

@Configuration
public class TopicConfig {

  @Value(value = "${application.topic.product-request}")
  private String productTopicString;

  @Value(value = "${application.topic.brand-request}")
  private String brandTopicString;

  @Value(value = "${application.topic.customer-request}")
  private String customerTopicString;



  @Bean
  public NewTopic productTopicString() {
    return TopicBuilder.name(productTopicString)
        .build();
  }


  @Bean
  public NewTopic brandTopicString() {
    return TopicBuilder.name(brandTopicString)
            .build();
  }
  @Bean
  public NewTopic customerTopicString() {
    return TopicBuilder.name(customerTopicString)
            .build();
  }


}
