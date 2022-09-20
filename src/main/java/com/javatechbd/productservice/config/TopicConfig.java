package com.javatechbd.productservice.config;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.TopicBuilder;

@Configuration
public class TopicConfig {

  @Value(value = "${application.topic.product-request}")
  private String messageTopicString;

  @Bean
  public NewTopic messageTopicString() {
    return TopicBuilder.name(messageTopicString)
        .build();
  }
}
