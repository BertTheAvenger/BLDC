package Serial;

public interface Command {
    String toReadableString();
    //Returns readable string for command EX: SETPHASE returns "Set phase <phase> to <pwm>"
     int getLength();
    //returns total command length in bytes, including command itself.
    // EX {1} (handshake command) returns 1, {2, 0, 255} (Setphase) returns 3.
}
