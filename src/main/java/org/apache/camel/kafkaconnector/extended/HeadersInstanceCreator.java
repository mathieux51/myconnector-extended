package org.apache.camel.kafkaconnector.extended;

import com.google.gson.InstanceCreator;
import java.lang.reflect.Type;
// import org.apache.kafka.connect.header.Headers;
import org.apache.kafka.connect.header.ConnectHeaders;

class HeadersInstanceCreator implements InstanceCreator<ConnectHeaders> {
  @Override
  public ConnectHeaders createInstance(Type type) {
    return new ConnectHeaders();
  }
}
