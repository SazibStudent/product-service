# product-service


### ssh into docker container
docker exec -it kafka bash

### list down topics
kafka-topics.sh --bootstrap-server localhost:9092 --list

### delete topic
kafka-topics.sh --bootstrap-server localhost:9092 --delete --topic demo-kafka-topic

### Showing Value from Kafka Console
kafka-console-consumer.sh --bootstrap-server localhost:9092 --topic t_multi_partitions --offset earliest --partition 0