
package org.apache.camel.kafkaconnector.extended;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import java.util.Map;
import org.apache.kafka.common.config.ConfigDef;
import org.apache.kafka.connect.connector.ConnectRecord;
import org.apache.kafka.connect.data.Schema;
import org.apache.kafka.connect.transforms.Transformation;
import org.apache.kafka.connect.header.Headers;

public class StorageRecord {
    private final String key;
    private final String body;
    private final Headers headers;

    public ConnectRecord(String key, Integer kafkaPartition,
                         Schema keySchema, Object key,
                         Schema valueSchema, Object value,
                         Long timestamp) {
        this(topic, kafkaPartition, keySchema, key, valueSchema, value, timestamp, new ConnectHeaders());
    }
}
