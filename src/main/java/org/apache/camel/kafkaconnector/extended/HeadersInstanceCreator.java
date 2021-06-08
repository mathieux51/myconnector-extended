package org.apache.camel.kafkaconnector.extended;

import com.google.gson.InstanceCreator;
import java.lang.reflect.Type;
import org.apache.kafka.connect.header.Headers;

class HeadersInstanceCreator implements InstanceCreator {
  @Override
  public Headers createInstance(Type type) {
    return new Headers();
  }
}
