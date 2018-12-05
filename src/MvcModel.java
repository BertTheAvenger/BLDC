class MvcModel {
    private String selectedSerialPort;
    private int baudRate;

    MvcModel()
    {
        selectedSerialPort = "";
        baudRate = 115200; //Default baudrate
    }
    void setSelectedSerialPort(String port){
        this.selectedSerialPort = port;
    }

    String getSelectedSerialPort() {
        return selectedSerialPort;
    }

    void setBaudRate(int baudRate) {
        this.baudRate = baudRate;
    }

    int getBaudRate() {
        return baudRate;
    }

}
