package com.codeflyz.gamefixedtimestep.roads;

import com.codeflyz.roadnet.Driver;
import com.codeflyz.roadnet.Link;
import com.codeflyz.roadnet.Car;
//import com.codeflyz.roadnet.RoadList;
import com.codeflyz.roadnet.LinkList;
import com.codeflyz.gamefixedtimestep.networks.SquarevilleNetworkGenerator;
import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayDeque;
import java.util.ArrayList;
import javax.swing.JPanel;

/**
 *
 * @author jameswilfong
 */
public class GamePanel extends JPanel {

    double interpolation;
    private GameState gameState = null;
    ArrayList<Car> cars = new ArrayList<Car>();
    ArrayList<Link> carLinks = new LinkList();
    LaunchPad launchPad = new LaunchPad();

    JRoadNetwork roadNetwork = new JRoadNetwork();

    double ballXVel, ballYVel;

    public GamePanel(GameState gameState) {
        //System.out.println("Load data");
        this.gameState = gameState;
        loadRoads(); // load roads before balls
        loadCars();

    }

    public GameState getGameState() {
        return gameState;
    }

    public void loadCars() {
        //System.out.println("load cars");
        //int space = 50;
        Color color = Color.RED;

        JRoadList startList = (JRoadList)roadNetwork.get(0);
        

        /*
        for (int idx = 0; idx < 1; idx++) {

            //cars.add((Car) new Car(idx, (double) sx, (double) sy).setRoad(startRoad).setSpeed(speed).setColor(color));
            //cars.add((Car) new Car(idx, startRoad).setColor(color));
            launchPad.addFirst((Car) new Car(idx, new Driver(), roadNetwork));
            launchPad.getFirst().setColor(color);
            //carLinks.add(new Link(cars.get(idx)));
            //color = Color.BLUE;

        }
         */

        for (int idx = 0; idx < roadNetwork.size(); idx++) {
          
            cars.add((Car) new JCar(idx, new Driver(), roadNetwork));
            ((JCar)cars.get(cars.size() - 1)).setColour(color);
            
        }

        for (Car car : cars) {
            System.out.println("car: " + car.toString());
        }
        System.out.println("load complete");
       
    }

    /*
     public void loadCars() {
        System.out.println("load cars");
        //int space = 50;
        Color color = Color.RED;

        RoadList startList = roadNetwork.get(0);


        for (int idx = 0; idx < 6; idx++) {

            //cars.add((Car) new Car(idx, (double) sx, (double) sy).setRoad(startRoad).setSpeed(speed).setColor(color));
            //cars.add((Car) new Car(idx, startRoad).setColor(color));
            cars.add((Car) new Car(idx, new Driver(),roadNetwork));
            cars.get(cars.size()-1).setColor(color);
            //carLinks.add(new Link(cars.get(idx)));
            //color = Color.BLUE;

        }
        

        for (Car car : cars) {
            System.out.println("car: " + car.toString());
        }

    }
    
     */
    public void loadRoads() {
        double segmentLength = 50;

        //roadNetwork = new DiamondNetworkGenerator(segmentLength);
        //roadNetwork = new  LongLineNetworkGenerator(segmentLength);
        //roadNetwork = new  LongRoadNetworkGenerator(segmentLength);
        //roadNetwork = new  ShortCutNetworkGenerator(segmentLength);  
        //roadNetwork = new  CrossNetworkGenerator(segmentLength);  
        roadNetwork = new SquarevilleNetworkGenerator(segmentLength);
        //roadNetwork = new  FishBoneNetworkGenerator(segmentLength);
        ///////////////////////////////////////////////////
        System.out.println("network: " + roadNetwork.toString());
    }

    public void setInterpolation(double interp) {
        interpolation = interp;
    }

    public void update() { // update positions

        //jball.update(this);
        for (Car car : cars) {
            ((JCar)car).update();
        }
    }

    public void paintComponent(Graphics g) {

        // draw road
        //System.out.println("-----------");
        roadNetwork.draw(g);

        //BS way of clearing out the old rectangle to save CPU.
        /*for (Car car : cars) {
            car
                    .setInterpolation(interpolation)
                    .draw(g);

        }*/
 /*if(launchPad.size()>0){
            Car car = launchPad.removeLast();
            this.cars.add(car);
            car
                    .setInterpolation(interpolation)
                    .draw(g);
            System.out.println("carz: " + car);
        }*/
        for (Car car : cars) {
            ((JCar)car)
                    .setInterpolation(interpolation);
            ((JCar)car).draw(g);

        }
       // System.out.println("...");
        gameState.setFrameCount(gameState.getFrameCount() + 1);
    }

    public class LaunchPad extends ArrayDeque<Car> {

    }
}
