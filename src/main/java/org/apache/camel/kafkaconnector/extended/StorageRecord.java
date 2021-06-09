package org.apache.camel.kafkaconnector.extended;

// import org.apache.kafka.connect.header.Headers;
// import org.apache.kafka.connect.header.ConnectHeaders;

public class StorageRecord {
  public final String key;
  public final String body;
  public final StorageHeader[] headers;

  public StorageRecord(String key, String body, StorageHeader[] headers) {
    this.key = key;
    this.body = body;
    this.headers = headers;
  }
}
