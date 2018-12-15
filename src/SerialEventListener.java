import Serial.RXCommand;

public interface SerialEventListener {
    void serialEvent(RXCommand event);

}
