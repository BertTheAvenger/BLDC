public class CalibrationStep {
    String descriptor;
    double progress; //0-1, describes how done calibration is.
    public CalibrationStep(double progress, String descriptor)
    {
        this.descriptor = descriptor;
        this.progress = progress;
    }

}
