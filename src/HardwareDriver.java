import Serial.RXCommand;
import Serial.TXCommands.*;

import java.util.ArrayList;
import java.util.Timer;

class HardwareDriver {
    private static int[] sinArr;

    private static Timer calibrationTimer;

    private static int encoderStepsPerPhase = 600; //Integrate with model eventually!
    private static int sinWaveResolution = 48; //Integrate with model eventually!

    private static int calibrationStepDelay = 1000;

    private static int totalPhaseLoops = 1;

    private static int controlMode = 0;

    private static int currentPhaseStep = 0;

    private static ArrayList<CalibrationStepListener> calibrationStepListeners = new ArrayList<>();

    static void init()
    {
        //Bind event handler.
        SerialHandler.addListener(HardwareDriver::serialEvent); //Bind serial command event listener.
        sinArr = new int[sinWaveResolution];
        for(int i = 0; i < sinArr.length; i++) //Init stock sin array with a wave.
        {
            sinArr[i] = (int)(127 + 127*Math.sin(2.0*Math.PI*((double)i/sinWaveResolution)));
            //System.out.println(sinArr[i]);
        }
        MvcView.setSinWave(sinArr);

    }

    private static void serialEvent(RXCommand command)
    {
        switch(command.getCommandEnum()){
            case ACK:
                break;
            case ADDSHORTS:
                break;
            case TOTALDATA:
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
        /*

        currentPhaseStep = 0;
        calibrationTimer = new Timer();
        SerialHandler.sendSerialCommand(new TXSETMODE((byte)1));
        calibrationTimer.schedule(new TimerTask() { //Init calibration timer.
            @Override
            public void run() {
                incrementCalibration();
            }
        }, 0, calibrationStepDelay);
        */

        fireCalibrationStepListeners(new CalibrationStep(.75, "ayy lmao"));
        SerialHandler.sendSerialCommand(new TXACK());
        SerialHandler.sendSerialCommand(new TXSETMODE((byte)1));
        //SerialHandler.sendSerialCommand(new TXACK());



    }

    static void stopCalibration()
    {
        calibrationTimer.cancel();
    }

    static void addCalibrationStepListener(CalibrationStepListener l){calibrationStepListeners.add(l);}

    private static void fireCalibrationStepListeners(CalibrationStep step)
    {
        for(CalibrationStepListener l : calibrationStepListeners)
        {
            l.calibrationStepped(step);
        }
    }

    static void setEncoderSteps(int steps){
        encoderStepsPerPhase = steps;
    }
}
