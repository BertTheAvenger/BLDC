class MvcController {
    private static MvcModel model;
    private static int controlMode = 0;

    static void passObjects(MvcModel model) {
        MvcController.model = model;
    }

    static void startInterface()
    {
        MvcView.constructGui();
        MotorDriver.init();
    } //Starts interface

    static void setSerialPort(String serialPort){model.setSelectedSerialPort(serialPort);} //Sets model serial port.
    static void refreshViewSerialPorts(){MvcView.updateSerialMenu(SerialHandler.getPorts(), model.getSelectedSerialPort());} //Refreshes view's serial ports, available and selected
    static void setBaudRate(int baudRate){model.setBaudRate(baudRate);} //Sets model baud rate
    static void refreshViewBaudRates(){MvcView.updateBaudRates(SerialHandler.getAvailableBaudrates(), model.getBaudRate());} //Refreshes view's baud rates, available and selected
    static void serialConnectActionPreformed(){ //Called when serial connect button is clicked. Handles logic to connect to serial.
        //SerialHandler.connectToPort(model.getSelectedSerialPort(), model.getBaudRate());

        boolean result = SerialHandler.openSerialConnection(model.getSelectedSerialPort(), model.getBaudRate());
        if(result)
        {
            MvcView.setSerialActionDisconnect();
            System.out.println("Connection Successful");
        }
        else
        {
            System.out.println("CONNECTION FAILED!");
        }
    }
    static void serialDisconnectActionPreformed() { //Called when serial disconnect button is clicked. Handles logic to reset serial stuff.
        SerialHandler.closeSerialConnection();
        MvcView.setSerialActionConnect();
    }
}
