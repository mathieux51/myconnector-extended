// package org.apache.camel.kafkaconnector.extended;
//
// import org.apache.kafka.connect.header.ConnectHeaders;
// import org.apache.kafka.connect.header.Headers;
//
// public class StorageRecord {
//   public final String key;
//   public final String body;
//   public final Headers headers;
//
//   public StorageRecord(String key, String body, Headers headers) {
//     this.key = key;
//     this.body = body;
//     if (headers instanceof ConnectHeaders) {
//       this.headers = (ConnectHeaders) headers;
//     } else {
//       this.headers = new ConnectHeaders(headers);
//     }
//   }
// }
