Iot Zookeeper and Kafka demo application

The application demonstrate how a list of producers send messages using Kafka and how a list of consumers consume them.
The application is written partially using Spring Boot and partially (intentionally) using plain java in order to serve as demo code to Kafka/Zookeeper training course.
The application requires a working Zookeeper and Kafka cluster.
The tests were conducted using a cluster of three AWS EC2 machines where Zookeeper and Kafka was deployed.
