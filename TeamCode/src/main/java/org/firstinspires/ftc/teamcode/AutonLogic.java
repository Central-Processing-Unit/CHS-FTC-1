package org.firstinspires.ftc.teamcode;

import java.util.*;
import org.firstinspires.ftc.robotcore.external.ClassFactory;
import org.firstinspires.ftc.robotcore.external.hardware.camera.WebcamName;
import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;
import org.firstinspires.ftc.robotcore.external.navigation.VuforiaLocalizer;
import org.firstinspires.ftc.robotcore.external.tfod.Recognition;
import org.firstinspires.ftc.robotcore.external.tfod.TFObjectDetector;

public class AutonLogic extends Base
{
    private WebcamName webcameName;
    private VuforiaLocalizer vuforiaLocalizer;
    private TFObjectDetector objectDetector;

    public void initVuforia()
    {
        VuforiaLocalizer.Parameters vuParams = new VuforiaLocalizer.Parameters();
        vuParams.vuforiaLicenseKey = "AS0ENI3/////AAABmRrhaZtkGkSMi4tGQFf9azI3tZlg7Xv8GCAFy/EtV7oDQmsVBBNgiQNq035C7ShFgSt1Y9dtgOUrPHhlgoI/8sqhoBUnr3WRm/ex/gPsScPYlpy4mqBUZEIQxI2hndIuFrxPSc5gCMC4kyay2RWUWthzUygnp/22kgrq2u7xyKLwsUIctziWB1T3xreY6LcdSuqgPx6qMeiOmPkqLrIm+BbJovtmoVA7d/PqPoIeoo6O/CurFZVUeJq7zkPRB9OzsoF3Iyxyd3jGi1xlPes828QsbIcx1UYQIyR+q52fLVAt69FPPQ6AO8YMfgc0z+qF7pSA1Vee1LIyF+HCMh67gXj3YntVhvlnSeflrFtVB7vl";
        vuParams.cameraName = hardwareMap.get(WebcamName.class, "MainCamera");

        vuforiaLocalizer = ClassFactory.getInstance().createVuforia(vuParams);

        TFObjectDetector.Parameters tfParams = new TFObjectDetector.Parameters();
        tfParams.minResultConfidence = 0.7f;
        objectDetector = ClassFactory.getInstance().createTFObjectDetector(tfParams, vuforiaLocalizer);
        //objectDetector.loadModelFromFile();
    }

    public List<Recognition> getRecognitions()
    {
        List<Recognition> recognitions = objectDetector.getUpdatedRecognitions();
        if (recognitions != null)
            return recognitions;
        return null;
    }

    public elementLocation getElementLocation()
    {
        List<Recognition> recognitions = getRecognitions();
        Recognition recognition = recognitions.get(0);
        double angleToElement;

        if (recognitions.size() > 1) //Robot sees more than one element. Need to determine element that has highest confidence.
        {
            float highestConfidence = 0.0f;
            for (Recognition element : recognitions)
            {
                float confidence = element.getConfidence();
                if (confidence > highestConfidence)
                {
                    highestConfidence = confidence;
                    recognition = element;
                }

            }
        }

        angleToElement = recognition.estimateAngleToObject(AngleUnit.RADIANS);


    }

    public void go(double xtarget, double ytarget)
    {

    }

    public enum elementLocation{
        Left, Center, Right
    }
}