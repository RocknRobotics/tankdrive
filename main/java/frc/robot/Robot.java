package frc.robot;
import edu.wpi.first.wpilibj.PS4Controller;
import edu.wpi.first.wpilibj.TimedRobot;
public class Robot extends TimedRobot {
  private Movement rob = new Movement(1, 2);
  private PS4Controller p4 = new PS4Controller(1);
  private double y1 = 0;    
  private double x1 = 0; 
  private double deadzone1=.2;
  private double deadzone2=.2;

  @Override
  public void robotInit() {

    rob.reset();
  }

  @Override
  public void teleopPeriodic() {
    y1 = p4.getLeftY();  
    x1 = p4.getRightX(); 
    if (p4.getRawButton(5)) {
        rob.estop();
    }
    if(Math.abs(x1)<deadzone1)
    {
     x1=0;
    }
    if(x1!=0)
    {
      System.out.println("YVelocity: "+y1);
    }
    if(Math.abs(y1)<deadzone2)
    {
     y1=0;
    }
    if(x1!=0)
    {
      System.out.println("XVelocity "+x1);
    }
    if(Math.abs(y1)>Math.abs(x1))
    {
    rob.rightForward(y1);
    rob.leftForward(y1*-1);
    }
    if(Math.abs(x1)>Math.abs(y1))
    {
      rob.rightForward(x1);
      rob.leftForward(x1*-1);
    }
  if (p4.getRawButton(4)) {
        rob.power(.75);
    }
    if (p4.getRawButton(3)) { 
      rob.power(.50);
  }
  if (p4.getRawButton(2)) { 
    rob.power(1);
}
if (p4.getRawButton(1)) { 
  rob.power(.25);
}

    if (p4.getRawButtonPressed(6)) { 
     rob.shoot();
      }
  }

  @Override
  public void disabledInit() {
    rob.nopower();
  }
}