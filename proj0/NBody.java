public class NBody
{
  public static void main(String[] args)
  {
    String strT = args[0];
    String strdT = args[1];
    double T = Double.parseDouble(strT);
    double dT = Double.parseDouble(strdT);
    String filename = args[2];
    double radius = readRadius(filename);
    Body[] planets = readBodies(filename);
    //draw the background
    StdDraw.setScale(-radius, radius);
    StdDraw.clear();
    //animate the planets
    StdDraw.enableDoubleBuffering();
    double phase = 0.0;
    while(phase < T)
    {
      double[] xForces = new double[planets.length];
      double[] yForces = new double[planets.length];
      for(int i=0; i<planets.length; i++)
      {
        double netXForcesOnI=planets[i].calcNetForceExertedByX(planets);
        double netYForcesOnI=planets[i].calcNetForceExertedByY(planets);
        xForces[i] = netXForcesOnI;
        yForces[i] = netYForcesOnI;
      }
      for(int i=0; i<planets.length;i++)
      {
        planets[i].update(dT, xForces[i], yForces[i]);
      }
      StdDraw.picture(0, 0, "images/starfield.jpg");
      for(int i=0; i<planets.length; i++)
      {
        planets[i].draw();
      }
      StdDraw.show();
      StdDraw.pause(20);
      phase += dT;
    }
    StdOut.printf("%d\n", planets.length);
    StdOut.printf("%.2e\n", radius);
    for (int i = 0; i < planets.length; i++)
    {
      StdOut.printf("%11.4e %11.4e %11.4e %11.4e %11.4e %12s\n",
                  planets[i].xxPos, planets[i].yyPos, planets[i].xxVel,
                  planets[i].yyVel, planets[i].mass, planets[i].imgFileName);
    }
  }

  public static double readRadius(String fileName)
  {
    In inFile = new In(fileName);
    int num = inFile.readInt();
    double radius = inFile.readDouble();
    return radius;
  }
  public static Body[] readBodies(String fileName)
  {
    In inFile = new In(fileName);
    int arraySize = inFile.readInt();
    double discardVal = inFile.readDouble();
    Body[] bArray = new Body[arraySize];
    for(int i=0; i < arraySize; i++)
    {
      double xxPos = inFile.readDouble();
      double yyPos = inFile.readDouble();
      double xxVel = inFile.readDouble();
      double yyVel = inFile.readDouble();
      double mass = inFile.readDouble();
      String img = inFile.readString();
      bArray[i] = new Body(xxPos, yyPos, xxVel, yyVel, mass, img);
    }
    return bArray;
  }
}
