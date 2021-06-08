package org.apache.camel.kafkaconnector.extended;

import org.apache.kafka.connect.header.ConnectHeaders;

private class ConnectHeadersInstanceCreator implements InstanceCreator<ConnectHeaders> {
  private Context context;

  public ConnectHeadersInstanceCreator(Context context) {
    this.context = context;
  }

  @Override
  public ConnectHeaders createInstance(Type type) {
    ConnectHeaders ConnectHeaders = new ConnectHeaders(context);
    return ConnectHeaders;
  }
}
