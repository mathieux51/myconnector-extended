package org.apache.camel.kafkaconnector.extended;

import com.google.gson.Gson;
// import com.google.gson.reflect.TypeToken;
// import org.slf4j.Logger;
// import org.slf4j.LoggerFactory;
import java.util.Map;
import org.apache.kafka.common.config.ConfigDef;
import org.apache.kafka.connect.connector.ConnectRecord;
import org.apache.kafka.connect.transforms.Transformation;

public class JSONToRecordTransforms<R extends ConnectRecord<R>> implements Transformation<R> {
  public static final String FIELD_KEY_CONFIG = "key";
  public static final ConfigDef CONFIG_DEF =
      new ConfigDef()
          .define(
              FIELD_KEY_CONFIG,
              ConfigDef.Type.STRING,
              null,
              ConfigDef.Importance.MEDIUM,
              "Add the key and the header to the record value");
  // private static final Logger LOG = LoggerFactory.getLogger(JSONToRecordTransforms.class);

  @Override
  public void configure(Map<String, ?> configs) {}

  @Override
  public R apply(R record) {
    Gson gson = new Gson();
    String str = new String((byte[]) record.value());

    StorageRecord storageRecord = gson.fromJson(str, StorageRecord.class);
    return record.newRecord(
        record.topic(),
        record.kafkaPartition(),
        record.keySchema(),
        storageRecord.key,
        record.valueSchema(),
        storageRecord.body,
        record.timestamp(),
        storageRecord.headers);
  }

  @Override
  public void close() {}

  @Override
  public ConfigDef config() {
    return CONFIG_DEF;
  }
}
