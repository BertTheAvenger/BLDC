import java.util.ArrayList;

public class SerialPacket {
    ArrayList<Command> commandBuffer;
    ArrayList<Integer> buffer;
    public SerialPacket()
    {

    }

    abstract class Command
    {
        Command(){ }

        abstract int getCommandByte();

        abstract int[] getByteArray();

        public abstract String toString();
    }

    public class HANDSHAKE extends Command
    {
        @Override
        int getCommandByte() {
            return 1;
        }

        @Override
        public int[] getByteArray() {

            return new int[0];
        }

        @Override
        public String toString() {
            return "";
        }
    }
}
