package org.apache.camel.kafkaconnector.extended;

import com.google.gson.*;
import java.lang.reflect.Type;
// import org.apache.kafka.connect.connector.ConnectRecord;

public class HeaderAdapter<R> implements JsonSerializer<R>, JsonDeserializer<R> {
  @Override
  public JsonElement serialize(R src, Type typeOfSrc, JsonSerializationContext context) {
    JsonObject result = new JsonObject();
    result.add("className", new JsonPrimitive(src.getClass().getSimpleName()));
    result.add("data", context.serialize(src, src.getClass()));

    return result;
  }

  @Override
  public R deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context)
      throws JsonParseException {
    JsonObject jsonObject = json.getAsJsonObject();
    String type = jsonObject.get("className").getAsString();
    JsonElement element = jsonObject.get("data");

    try {
      return context.deserialize(
          element, Class.forName("org.apache.camel.kafkaconnector.extended." + type));
    } catch (ClassNotFoundException cnfe) {
      throw new JsonParseException("Unknown element type: " + type, cnfe);
    }
  }
}
