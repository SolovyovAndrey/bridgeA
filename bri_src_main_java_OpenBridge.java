import javax.swing.*;
import java.awt.*;
import java.io.IOException;

import static java.lang.Thread.sleep;

public class OpenBridge extends JPanel {

    public static void main(String[] args) throws InterruptedException {
        new OpenBridge();
    }

    private Car car;
    private Bridge bridge;
    private Building building;


    public OpenBridge() throws InterruptedException {

        JFrame frame = new JFrame();
        frame.setLocation(100, 100);
        frame.setMinimumSize(new Dimension(1200,600));
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.getContentPane().add(this);
        frame.pack();
        frame.setVisible(true);

        bridge = new Bridge();
        building = new Building();
        startCar();

        while(true) {
            repaint();
            sleep(1000/60);

        }
    }


    private void startCar(){

        car = new Car(10,300);

        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    moveCar();
                } catch (Exception e) {
                }
            }
        }).start();
        doOpenBridge();
    }


    private void doOpenBridge() {

        new Thread(new Runnable() {
            @Override
            public void run() {
                try{
                    synchronized (bridge){
                        bridge.wait();
                    }
                    while (!bridge.dawn){
                        System.out.println("bridge dawn ---");
                        animateBridge();
                    }

                    synchronized (car) {
                        sleep(1000);
                        car.notify();
                        System.out.println("car come to home");
                    }

                }catch (Exception e){

                }
            }
        }).start();
    }


    private void upBridge() {

        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    int i = 4;
                    while (i > -1) {
                        System.out.println("Bridge up : " + i);
                        bridge.getImage(i);
                        repaint();
                        sleep(1000 / 60);
                        i--;
                    }
                    sleep(1000);
                } catch (Exception e) {

                }
            }
        }).start();
    }


    private void animateBridge() throws InterruptedException {

        int i = 0;
        while (i < 5){
            System.out.println("Bridge dawn : " + i);
            bridge.getImage(i);
            repaint();
            sleep(100);
            i++;
        }
        sleep(1000);
    }


    private void moveCar() throws InterruptedException {

        while (car.getX() <= 850) {
            if (!bridge.dawn && car.getX() == 380) {
                synchronized (bridge) {
                    bridge.notify();
                    System.out.print("Car come to bridge");
                }
                synchronized (car) {
                    System.out.println(" and waiting bridge to down --" + bridge.dawn);
                    car.wait();
                }
            }
            if (car.getX() == 600) {
                upBridge();
            }

            car.setX(car.getX() + 1);
            sleep(1000 / 120);

            if (car.getX() == 850) {
                System.out.println("new car");
                bridge.dawn = false;
                startCar();
            }
        }
    }


    protected void paintComponent(Graphics g){
        super.paintComponent(g);

        try {
            building.draw(g);
            bridge.draw(g);
            car.draw(g);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
