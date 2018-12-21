import Serial.RXCommand;
import Serial.TXCommands.*;

import java.util.Timer;
import java.util.TimerTask;

class HardwareDriver {
    private static byte[] sinArr;

    private static Timer calibrationTimer;

    private static int encoderStepsPerPhase = 600; //Integrate with model eventually!
    private static int sinWaveResolution = 48; //Integrate with model eventually!

    private static int calibrationStepDelay = 1000;

    private static int totalPhaseLoops = 1;

    private static int controlMode = 0;

    private static int currentPhaseStep = 0;

    static void init()
    {
        //Bind event handler.
        SerialHandler.addListener(HardwareDriver::serialEvent); //Bind serial command event listener.
        sinArr = new byte[sinWaveResolution];
        for(int i = 0; i < sinArr.length; i++)
        {
            sinArr[i] = (byte)(127 + 127*Math.sin((2.0*Math.PI*i)/sinWaveResolution));
        }

    }

    private static void serialEvent(RXCommand command)
    {
        switch(command.getCommand()){
            case ACK:
                break;
            case ADDSHORTS:
                break;
            case TOTALDATA:
                break;
            case NUMBERTEST:
                break;
        }
    }

    static void incrementCalibration()
    {

        currentPhaseStep++;
    }


    static void startCalibration()
    {
        System.out.println("Calibration Starting!");

        currentPhaseStep = 0;
        calibrationTimer = new Timer();
        SerialHandler.sendSerialCommand(new TXSETMODE((byte)1));
        calibrationTimer.schedule(new TimerTask() { //Init calibration timer.
            @Override
            public void run() {
                incrementCalibration();
            }
        }, 0, calibrationStepDelay);




    }

    static void stopCalibration()
    {
        calibrationTimer.cancel();
    }

    static void setEncoderSteps(int steps){
        encoderStepsPerPhase = steps;
    }
}
