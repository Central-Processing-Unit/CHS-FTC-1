package org.firstinspires.ftc.teamcode;

import com.qualcomm.hardware.bosch.BNO055IMU;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;

public class Base extends OpMode
{
    DcMotor leftfront, rightfront, leftback, rightback;
    BNO055IMU imu;
    BNO055IMU.Parameters parameters = new BNO055IMU.Parameters();

    public void loop(){}

    public void init()
    {
        leftfront = hardwareMap.dcMotor.get("leftFrontMotor");
        leftfront.setDirection(DcMotorSimple.Direction.FORWARD);

        rightfront = hardwareMap.dcMotor.get("rightFrontMotor");
        rightfront.setDirection(DcMotorSimple.Direction.FORWARD);

        leftback = hardwareMap.dcMotor.get("leftBackMotor");
        leftback.setDirection(DcMotorSimple.Direction.FORWARD);

        rightback = hardwareMap.dcMotor.get("rightBackMotor");
        rightback.setDirection(DcMotorSimple.Direction.FORWARD);

        parameters.mode = BNO055IMU.SensorMode.IMU;
        parameters.angleUnit = BNO055IMU.AngleUnit.RADIANS;
        parameters.loggingEnabled = false;

        imu = hardwareMap.get(BNO055IMU.class, "imu");
        imu.initialize(parameters);
    }

    public void move(double posinput, double neginput)
    {
        leftfront.setPower(posinput);
        rightfront.setPower(-neginput);
        leftback.setPower(neginput);
        rightback.setPower(-posinput);
    }

    public void pivot(double input)
    {
        leftfront.setPower(-input);
        rightfront.setPower(-input);
        leftback.setPower(-input);
        rightback.setPower(-input);
    }
}