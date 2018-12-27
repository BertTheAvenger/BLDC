import Serial.RXCommand;

public interface SerialRXCommandListener {
    void serialRXEvent(RXCommand event);
}
