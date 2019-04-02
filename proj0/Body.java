public class Body
{
  private final static double G = 6.67e-11;
  //create instance variables
  public double xxPos;
  public double yyPos;
  public double xxVel;
  public double yyVel;
  public double mass;
  public String imgFileName;
  //make constructors
  public Body(double xP, double yP, double xV, double yV, double m, String img)
  {
    xxPos = xP;
    yyPos = yP;
    xxVel = xV;
    yyVel = yV;
    mass = m;
    imgFileName = img;
  }

  public Body(Body b)
  {
    xxPos = b.xxPos;
    yyPos = b.yyPos;
    xxVel = b.xxVel;
    yyVel = b.yyVel;
    mass = b.mass;
    imgFileName = b.imgFileName;
  }

  public double calcDistance(Body b)
  {
    double xSide = b.xxPos - this.xxPos;
    double ySide = b.yyPos - this.yyPos;
    double distance = Math.sqrt(xSide*xSide + ySide*ySide);
    return distance;
  }

  public double calcForceExertedBy(Body b)
  {
    double distance = this.calcDistance(b);
    double force = (G*this.mass*b.mass)/(distance*distance);
    return force;
  }

  public double calcForceExertedByX(Body b)
  {
    double force = this.calcForceExertedBy(b);
    double dX = b.xxPos - this.xxPos;
    double distanceR = this.calcDistance(b);
    return (force*dX)/distanceR;
  }

  public double calcForceExertedByY(Body b)
  {
    double force = this.calcForceExertedBy(b);
    double dY = b.yyPos - this.yyPos;
    double distanceR = this.calcDistance(b);
    return (force*dY)/distanceR;
  }

  public double calcNetForceExertedByX(Body[] array)
  {
    double netForceX = 0.0;
    for(int i=0, n=array.length; i < n; i++)
    {
      if(this.equals(array[i]))
      {
        continue;
      }
      netForceX += this.calcForceExertedByX(array[i]);
    }
    return netForceX;
  }

  public double calcNetForceExertedByY(Body[] array)
  {
    double netForceY = 0.0;
    for(int i=0, n = array.length; i<n; i++)
    {
      if(this.equals(array[i]))
      {
        continue;
      }
      netForceY += this.calcForceExertedByY(array[i]);
    }
    return netForceY;
  }

  public void update(double dT, double fX, double fY)
  {
    double accelNetX = fX/this.mass;
    double accelNetY = fY/this.mass;
    this.xxVel = this.xxVel + (dT*accelNetX);
    this.yyVel = this.yyVel + (dT*accelNetY);
    this.xxPos = this.xxPos + (dT*this.xxVel);
    this.yyPos = this.yyPos + (dT*this.yyVel);
  }

  public void draw()
  {
    String destination = "images/";
    destination = destination + this.imgFileName;
    StdDraw.picture(this.xxPos, this.yyPos, destination);
  }


}
