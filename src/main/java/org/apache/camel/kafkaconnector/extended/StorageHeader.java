package org.apache.camel.kafkaconnector.extended;

public class StorageHeader {
  public final String key;
  public final String value;

  public StorageHeader(String key, String value) {
    this.key = key;
    this.value = value;
  }
}
