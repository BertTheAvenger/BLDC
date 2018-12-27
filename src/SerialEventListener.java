import Serial.SerialEventEnums;

public interface SerialEventListener {
    void eventPreformed(SerialEventEnums eventEnum);
}
