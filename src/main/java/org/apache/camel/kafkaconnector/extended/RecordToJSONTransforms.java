package org.apache.camel.kafkaconnector.extended;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import java.util.ArrayList;
import java.util.Map;
import org.apache.kafka.common.config.ConfigDef;
import org.apache.kafka.connect.connector.ConnectRecord;
import org.apache.kafka.connect.data.Schema;
import org.apache.kafka.connect.header.Header;
import org.apache.kafka.connect.header.Headers;
import org.apache.kafka.connect.transforms.Transformation;

public class RecordToJSONTransforms<R extends ConnectRecord<R>> implements Transformation<R> {
  public static final String FIELD_KEY_CONFIG = "key";
  public static final ConfigDef CONFIG_DEF =
      new ConfigDef()
          .define(
              FIELD_KEY_CONFIG,
              ConfigDef.Type.STRING,
              null,
              ConfigDef.Importance.MEDIUM,
              "Add the key and the header to the record value");

  @Override
  public void configure(Map<String, ?> configs) {}

  @Override
  public R apply(R record) {
    Headers headers = record.headers();
    ArrayList<StorageHeader> headerList = new ArrayList<StorageHeader>(headers.size());
    for (Header h : headers) {
      headerList.add(new StorageHeader(h.key(), (String) h.value()));
    }
    StorageHeader[] storageHeaders = new StorageHeader[headers.size()];
    StorageRecord storageRecord =
        new StorageRecord(
            (String) record.key(), (String) record.value(), headerList.toArray(storageHeaders));
    GsonBuilder gsonBuilder = new GsonBuilder();
    Gson gson = gsonBuilder.create();
    String storageRecordJSON = gson.toJson(storageRecord, StorageRecord.class);
    return record.newRecord(
        record.topic(),
        record.kafkaPartition(),
        null,
        record.key(),
        Schema.STRING_SCHEMA,
        storageRecordJSON,
        record.timestamp());
  }

  @Override
  public void close() {}

  @Override
  public ConfigDef config() {
    return CONFIG_DEF;
  }
}
