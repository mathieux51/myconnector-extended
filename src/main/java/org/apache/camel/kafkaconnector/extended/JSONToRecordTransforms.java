package org.apache.camel.kafkaconnector.extended;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import java.util.Map;
import org.apache.kafka.common.config.ConfigDef;
import org.apache.kafka.connect.connector.ConnectRecord;
import org.apache.kafka.connect.header.ConnectHeaders;
import org.apache.kafka.connect.header.Headers;
import org.apache.kafka.connect.transforms.Transformation;
// import org.slf4j.Logger;
// import org.slf4j.LoggerFactory;

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
    String str = new String((byte[]) record.value());
    GsonBuilder gsonBuilder = new GsonBuilder();
    Gson gson = gsonBuilder.create();
    StorageRecord storageRecord = gson.fromJson(str, StorageRecord.class);
    Headers headers = new ConnectHeaders();
    for (int i = 0; i < storageRecord.headers.length; i++) {
      headers.add(storageRecord.headers[i].key, storageRecord.headers[i].value, null);
    }
    headers.forEach(h -> record.headers().add(h));
    return record.newRecord(
        record.topic(),
        record.kafkaPartition(),
        record.keySchema(),
        (Object) storageRecord.key,
        record.valueSchema(),
        (Object) storageRecord.body,
        record.timestamp(),
        headers);
  }

  @Override
  public void close() {}

  @Override
  public ConfigDef config() {
    return CONFIG_DEF;
  }
}
