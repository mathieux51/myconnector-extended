package org.apache.camel.kafkaconnector.extended;


public class ConnectRecordAdapter  implements JsonSerializer<A>, JsonDeserializer<A> {
  @Override
  public JsonElement serialize(A src, Type typeOfSrc, JsonSerializationContext context) {
      JsonObject result = new JsonObject();
      result.add("type", new JsonPrimitive(src.getClass().getSimpleName()));
      result.add("properties", context.serialize(src, src.getClass()));
      return result;
  }


  @Override
  public A deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context)
        throws JsonParseException {
    JsonObject jsonObject = json.getAsJsonObject();
    String type = jsonObject.get("type").getAsString();
    JsonElement element = jsonObject.get("properties");

    try {
        String thepackage = "my.package.name.";
        return context.deserialize(element, Class.forName(thepackage + type));
    } catch (ClassNotFoundException cnfe) {
        throw new JsonParseException("Unknown element type: " + type, cnfe);
    }
  }
}
