package org.apache.camel.kafkaconnector.extended;

// import org.apache.kafka.connect.header.Headers;
import org.apache.kafka.connect.header.ConnectHeaders;

public class StorageRecord {
  public final String key;
  public final String body;
  public final ConnectHeaders connectHeaders;

  public StorageRecord(String key, String body, ConnectHeaders connectHeaders) {
    this.key = key;
    this.body = body;
    this.connectHeaders = connectHeaders;
  }
}
