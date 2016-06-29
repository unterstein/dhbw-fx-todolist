package ba.java.todolist.serialization;

public enum SerializationMode {
  BINARY(new BinarySerializer()), //
  JAXB(new JAXBSerializer()), //
  XML_ENCODE(new XmlEncodeSerializer()), //
  OWN_LINE_BASED(new OwnLineBasedSerializer()), //
  ;

  private Serializer serializer;

  SerializationMode(Serializer serializer) {
    this.serializer = serializer;
  }

  public Serializer getSerializer() {
    return serializer;
  }
}
