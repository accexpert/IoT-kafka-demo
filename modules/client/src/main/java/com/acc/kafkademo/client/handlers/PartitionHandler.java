package com.acc.kafkademo.client.handlers;

import org.apache.kafka.clients.producer.Partitioner;
import org.apache.kafka.common.Cluster;
import org.apache.kafka.common.PartitionInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.Map;

public class PartitionHandler implements Partitioner {
    private static final Logger LOGGER = LoggerFactory.getLogger(PartitionHandler.class);

    @Override
    public int partition(String topic, Object key, byte[] keyInBytes, Object message, byte[] MessageInBytes, Cluster cluster) {
        List<PartitionInfo> partitionInfo = cluster.availablePartitionsForTopic(topic);
        int numberOfPartitions = partitionInfo.size();
        Integer msgKey = ((Integer)key);
        LOGGER.info("Partition size: "+numberOfPartitions+"; msg key: "+msgKey);

        return 0;
    }

    @Override
    public void close() {

    }

    @Override
    public void configure(Map<String, ?> map) {

    }
}
