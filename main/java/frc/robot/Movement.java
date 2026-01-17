package frc.robot;

import com.revrobotics.spark.SparkMax;
import com.revrobotics.spark.config.SparkMaxConfig;
import com.revrobotics.spark.SparkBase.ResetMode;   
import com.revrobotics.spark.SparkBase.PersistMode; 
import com.revrobotics.spark.SparkLowLevel.MotorType;
import edu.wpi.first.wpilibj.Relay;
import edu.wpi.first.wpilibj.Relay.Direction;
import edu.wpi.first.wpilibj.Relay.Value;
import edu.wpi.first.wpilibj.motorcontrol.Talon;

public class Movement {
    private SparkMax myMotor1;
    private SparkMax myMotor3;
    private Talon myMotor2;
    private Relay myMotor4;
    private int shootCounter = 0;
    public Movement(int max1, int max2) {
        // Motor Initialization
        myMotor1 = new SparkMax(max1, MotorType.kBrushless);
        myMotor3 = new SparkMax(max2, MotorType.kBrushless);
        myMotor2 = new Talon(2); 
        
        // Correct 2026 Constant: kBothDirections
        myMotor4 = new Relay(0, Direction.kBoth);

        // REVLib 2026 Configuration
        SparkMaxConfig config = new SparkMaxConfig();
        config.openLoopRampRate(0.7); 
        // Apply config using 2026 required 3-argument signature
        myMotor1.configure(config, ResetMode.kResetSafeParameters, PersistMode.kPersistParameters);
        myMotor3.configure(config, ResetMode.kResetSafeParameters, PersistMode.kPersistParameters);
    }

    public void power(double percent) {
        myMotor2.set(percent);// exlosion
    }

    public void nopower() {
        myMotor2.set(0.0);
    }

    /**
     * Corrected Reset: Removed conflicting Direction change.
     * Simply sets the Relay to Reverse while keeping it in bidirectional mode.
     */
    public void reset() {
        myMotor4.set(Value.kOff);
    }

    public void leftForward(double speed) { 
        myMotor3.set(speed);
    }

    public void rightForward(double speed) { 
        myMotor1.set(speed);
    }
public void shoot() {
    if (shootCounter % 2 == 0) {
        myMotor4.set(Relay.Value.kForward); 
    } else {
        myMotor4.set(Relay.Value.kReverse);
    }
    shootCounter++;
}

    public void estop() {
        myMotor1.set(0.0);
        myMotor2.set(0.0);
        myMotor3.set(0.0);
        myMotor4.set(Value.kOff);
    }
}