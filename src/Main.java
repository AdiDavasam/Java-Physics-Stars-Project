import processing.core.PApplet;
import processing.core.PImage;

import java.util.ArrayList;


public class Main extends PApplet {
    ArrayList<Stars> allStars;
    ArrayList<Asteroids> allAsteroids;
    int shipx, shipy, shipxs, shipys, ships;
    //boolean up, down, left, right;
    final int STAR_SCREEN = 0;
    final int ASTEROID_SCREEN = 1;
    final int LIGHT_SPECTRA_SCREEN = 2;
    final int INFO_SCREEN = 3;
    final int SCREEN_WIDTH = 700;
    final int SCREEN_HEIGHT = 700;
    int displayingMainScreen;
    int displayingInfoScreen;
    final int INFO_HR_DIAGRAM_SCREEN = 1;
    final int INFO_LIGHT_SPECTRA_SCREEN = 2;
    final int INFO_COMPARING_STARS_SCREEN = 3;
    final int INFO_STAR_LIFECYCLE_SCREEN = 4;

    int infoButtonX, infoButtonY, infoButtonSize;

    PImage IMG_STAR_LIFECYCLE;
    PImage IMG_WHITE_DWARF_SPECTRA;
    PImage IMG_MAIN_SEQUENCE_SPECTRA;
    PImage IMG_GIANTS_SPECTRA;
    PImage IMG_SUPERGIANTS_SPECTRA;
    PImage IMG_ASTEROID, IMG_SHIP, IMG_STARLIGHT;

    char starClass;
    int level;

    public void settings() {
        size(700, 700);

    }

    public void setup() {
        //textSize(30);
        allStars = new ArrayList<>();
        allAsteroids = new ArrayList<>();
        settingAllStars(allStars);
        mouseX = 350;
        mouseY = 350;
        ships = 20;
        shipx = SCREEN_WIDTH / 2;
        shipy = SCREEN_HEIGHT / 2;
        shipxs = 4;
        shipys = 4;
        displayingMainScreen = STAR_SCREEN;
        displayingInfoScreen = INFO_HR_DIAGRAM_SCREEN;
        level = 1;

        infoButtonX = 30;
        infoButtonY = 30;
        infoButtonSize = 25;

        noCursor();

        //images formated/ wtvr here
        IMG_STAR_LIFECYCLE = loadImage("Lifecycle of a Star Infographic.png");
        IMG_STAR_LIFECYCLE.resize(700, 390);
        IMG_WHITE_DWARF_SPECTRA = loadImage("White Dwarf Star Light Spectra.png");
        IMG_WHITE_DWARF_SPECTRA.resize(700, 300);
        IMG_MAIN_SEQUENCE_SPECTRA = loadImage("Main Sequence Star Light Spectra.png"); //fix img
        IMG_MAIN_SEQUENCE_SPECTRA.resize(700, 300);
        IMG_GIANTS_SPECTRA = loadImage("Giant Star Light Spectra.png"); // fix img
        IMG_GIANTS_SPECTRA.resize(700, 300);
        IMG_SUPERGIANTS_SPECTRA = loadImage("Supergiant Star Light Spectra.png");
        IMG_SUPERGIANTS_SPECTRA.resize(700, 300);

        IMG_ASTEROID = loadImage("Asteroid Image.png");
        IMG_SHIP = loadImage("Spaceship.png");
        IMG_SHIP.resize(30, 30);
    }

    public void draw() {
        if (displayingMainScreen == STAR_SCREEN) {
            background(0);
            //ships movemnet
            fill(108, 112, 14);
            ellipse(infoButtonX, infoButtonY, infoButtonSize, infoButtonSize);
            fill(255);
            textAlign(CENTER, CENTER);
            textSize(16);
            text('i', 30, 28);
            //replace with img
//        fill(0);
//        ellipse(100,200, 23,23);
//        ellipse(500,90, 40,40);
//        ellipse(130,570, 16, 16);
//        ellipse(400,250, 33, 33);
            for (int i = 0; i < allStars.size(); i++) {
//            stroke(allStars.get(i).starColor);
                fill(allStars.get(i).starColor);
                ellipse(allStars.get(i).starX, allStars.get(i).starY, allStars.get(i).starSize, allStars.get(i).starSize);
            }
        } else if (displayingMainScreen == ASTEROID_SCREEN) {
            background(0);
            fill(214, 255, 0);
            textSize(30);
            textAlign(RIGHT, TOP);
            text("Level: " + level, 690, 20);
            //asteroids :O

//            Asteroids.offBounds(allAsteroids);
            boolean allOffScreen = true;
            for (int i = 0; i < allAsteroids.size(); i++) {

                fill(135, 142, 146);
//                System.out.println(allAsteroids.get(i).asteroidX);
                allAsteroids.get(i).move();
                ellipse(allAsteroids.get(i).asteroidX, allAsteroids.get(i).asteroidY, allAsteroids.get(i).asteroidSize, allAsteroids.get(i).asteroidSize);
                image(IMG_ASTEROID, allAsteroids.get(i).asteroidX - allAsteroids.get(i).asteroidSize / 2, allAsteroids.get(i).asteroidY - allAsteroids.get(i).asteroidSize / 2);
                if (Asteroids.hit(shipx, shipy, ships, allAsteroids.get(i).asteroidX, allAsteroids.get(i).asteroidY, allAsteroids.get(i).asteroidSize)) {
                    allAsteroids.clear();
                    displayingMainScreen = STAR_SCREEN;
                    level = 1;
                }
//                else if (allAsteroids.size() == 1) {
//                    //System.out.println("HI"); //RANDOMLY DISAPPEARING ASTEROIDS AFTER 1ST ROUND
//                    //add here where main sequence have 4 levels, white dwarf has 1 level etc
//                    level++;
//                    //System.out.println(level);
//                    allAsteroids.clear();
//                    settingAllAsteroids(allAsteroids);
//                }
                if (!allAsteroids.isEmpty() && allAsteroids.get(i).asteroidX < 700 && allAsteroids.get(i).asteroidY < 700) { //screen width
                    allOffScreen = false;
                }


            }
            if (allOffScreen) {
                level++;
                allAsteroids.clear();
                settingAllAsteroids(allAsteroids);
                //System.out.println("Level is: " + level);
                //System.out.println(starClass);
                if (starClass == 'W' && level == 2) {
                    level = 1;
                    displayingMainScreen = LIGHT_SPECTRA_SCREEN;
                } else if (starClass == 'M' && level == 3) {
                    level = 1;
                    displayingMainScreen = LIGHT_SPECTRA_SCREEN;
                } else if (starClass == 'G' && level == 4) {
                    level = 1;
                    displayingMainScreen = LIGHT_SPECTRA_SCREEN;
                } else if (starClass == 'S' && level == 5) {
                    level = 1;
                    displayingMainScreen = LIGHT_SPECTRA_SCREEN; // instead of mainscreen go to other screen where they will learn abt emmission spectra
                }
            }
        } else if (displayingMainScreen == LIGHT_SPECTRA_SCREEN) {
            background(0);
            if (starClass == 'W') {
                image(IMG_WHITE_DWARF_SPECTRA, 0, SCREEN_HEIGHT / 2 - IMG_WHITE_DWARF_SPECTRA.pixelHeight / 2);
                fill(255);
                textAlign(CENTER, CENTER);
                text("Elements: Hydrogen and Helium", SCREEN_WIDTH / 2, SCREEN_HEIGHT - 150);
            } else if (starClass == 'M') {
                image(IMG_MAIN_SEQUENCE_SPECTRA, 0, SCREEN_HEIGHT / 2 - IMG_MAIN_SEQUENCE_SPECTRA.pixelHeight / 2);
                fill(255);
                textAlign(CENTER, CENTER);
                text("Elements: Hydrogen and Helium\n(with trace elements)", SCREEN_WIDTH / 2, SCREEN_HEIGHT - 150);
            } else if (starClass == 'G') {
                image(IMG_GIANTS_SPECTRA, 0, SCREEN_HEIGHT / 2 - IMG_GIANTS_SPECTRA.pixelHeight / 2);
                fill(0);
                rect(0, 422, 80, 44);
                fill(255);
                textAlign(CENTER, CENTER);
                text("Elements: Hydrogen to Silicon", SCREEN_WIDTH / 2, SCREEN_HEIGHT - 150);
            } else if (starClass == 'S') {
                image(IMG_SUPERGIANTS_SPECTRA, 0, SCREEN_HEIGHT / 2 - IMG_SUPERGIANTS_SPECTRA.pixelHeight / 2);
                fill(255);
                textAlign(CENTER, CENTER);
                text("Elements: Hydrogen to Iron", SCREEN_WIDTH / 2, SCREEN_HEIGHT - 150);
            }



        } else if (displayingMainScreen == INFO_SCREEN) {
            background(0);

            fill(108, 112, 14);
            ellipse(infoButtonX, infoButtonY, infoButtonSize, infoButtonSize);
            fill(255);
            textAlign(CENTER, CENTER);
            textSize(16);
            text('i', 30, 28);

            textSize(20);
            if (displayingInfoScreen == INFO_HR_DIAGRAM_SCREEN) {
                fill(255, 255, 0);
                textAlign(RIGHT, BOTTOM);
                text('1', SCREEN_WIDTH - 30, SCREEN_HEIGHT - 40);
                fill(255);
                textAlign(CENTER, CENTER);
                text("What is a HR_Diagram? Well, it is a way of organizing stars \n\nby Temperature and Luminosity. The more to the right it is, \n\nthe colder the temperature. The more to the left, the hotter \n\nthe temperature of the star. The higher up it is, the greater \n\nthe luminosity. The lower it is, the more dim the star is.", SCREEN_WIDTH / 2, SCREEN_HEIGHT / 2);
            } else if (displayingInfoScreen == INFO_LIGHT_SPECTRA_SCREEN) {
                fill(255, 255, 0);
                textAlign(RIGHT, BOTTOM);
                text('2', SCREEN_WIDTH - 30, SCREEN_HEIGHT - 40);
                fill(255);
                textAlign(CENTER, CENTER);
                text("Stars are burning balls of gas that release light. When they \n\nrelease this light, it can be seen as a colorful rainbow \n\nspectra. However, on the outer layers of the star, away from the\n\n core, there lie elements that the star is fusing. These elements \n\nabsorb the light, leaving little black lines in its place. \n\nEach element has its own pattern, and the lines missing from the \n\nspectra can be used to identify the elements a star is fusing", 350, 350);
            } else if (displayingInfoScreen == INFO_COMPARING_STARS_SCREEN) {
                fill(255, 255, 0);
                textAlign(RIGHT, BOTTOM);
                text('3', SCREEN_WIDTH - 30, SCREEN_HEIGHT - 40);
                fill(255);
                textAlign(CENTER, CENTER);
                text("There are different types of stars, each with its own properties. \n\nSome stars like Main Sequence Stars mostly fuse Hydrogen \n\nto Helium. There are bigger and brighter stars which are Giants \n\nand Supergiants. Giants are around 100 times the brightness of \n\nour sun, fusing elements up to Oxygen. Supergiants are a lot \n\nbrighter with some being over 250,000 the brightness of our sun. \n\nThese fuse elements up to iron, before exploding in a supernova. \n\nThere are also white dwarfs which are \"dead stars\" as \n\nthey don't fuse any more elements", 350, 350);
            } else if (displayingInfoScreen == INFO_STAR_LIFECYCLE_SCREEN) {
                fill(255, 255, 0);
                textAlign(RIGHT, BOTTOM);
                text('4', SCREEN_WIDTH - 30, SCREEN_HEIGHT - 40);
                fill(255);
                textAlign(CENTER, CENTER);
                //insert picture / diagram

                image(IMG_STAR_LIFECYCLE, 0, SCREEN_HEIGHT / 2 - IMG_STAR_LIFECYCLE.pixelHeight / 2);

                fill(74, 134, 232);
                text("Link", 100, 600);


            }
        }
        shipx = mouseX;
        shipy = mouseY;
        image(IMG_SHIP, shipx - ships / 2, shipy - ships / 2);

    }


    public static void main(String[] args) {

        PApplet.main("Main");

    }

    public void mouseReleased() {
        if (displayingMainScreen == INFO_SCREEN && displayingInfoScreen == INFO_STAR_LIFECYCLE_SCREEN) {
            if (shipx < 130 && shipx > 70 && shipy > 585 && shipy < 615) {
                link("https://cdn1.byjus.com/wp-content/uploads/2023/04/Life-Cycle-of-a-Star.png");

            }
        }
    }

    public void keyPressed() {
    }

    public void keyReleased() {

        if (key == ' ' && displayingMainScreen == STAR_SCREEN) {
            //System.out.println("space pressed");
            for (int i = 0; i < allStars.size(); i++) {
                if (Stars.inBounds(shipx, shipy, allStars.get(i).starX, allStars.get(i).starY, allStars.get(i).starSize)) {
                    starClass = allStars.get(i).typeOfStar;
//                    //System.out.println(allStars.get(i).typeOfStar);
                    settingAllAsteroids(allAsteroids);
                    //System.out.println("b4 ast screen");
                    displayingMainScreen = ASTEROID_SCREEN;
                    //System.out.println("Afdter ast screen");
                    break;
                }
            }
            //System.out.println("no star clicked on");

        }
        if (key == ' ' && touchingInfoButton()) {
            if (displayingMainScreen == STAR_SCREEN) {
                //System.out.println("switched to info screen");
                displayingMainScreen = INFO_SCREEN;
            } else if (displayingMainScreen == INFO_SCREEN) {
                //System.out.println("switched to star screen");
                displayingMainScreen = STAR_SCREEN;
            }
        }
        if (key == ' ' && displayingMainScreen == LIGHT_SPECTRA_SCREEN) {
            displayingMainScreen = STAR_SCREEN;
        }


        if (keyCode == RIGHT && displayingMainScreen == INFO_SCREEN) {
            //System.out.println("right");
            if (displayingInfoScreen < 4) {
                displayingInfoScreen += 1;
                //System.out.println("dispay number is: " + displayingInfoScreen);
            }
        }
        if (keyCode == LEFT && displayingMainScreen == INFO_SCREEN) {
            if (displayingInfoScreen > 1) {
                displayingInfoScreen -= 1;
                //System.out.println("dispay number is: " + displayingInfoScreen);

            }
        }


    }

    public void settingAllStars(ArrayList<Stars> list) {
        //Supergiants
        list.add(new Stars(225, 110, 39, color(102, 179, 199), 'S'));
        list.add(new Stars(350, 120, 40, color(163, 213, 224), 'S'));
        list.add(new Stars(500, 90, 41, color(245, 246, 76), 'S'));
        list.add(new Stars(600, 100, 43, color(239, 58, 3), 'S'));
        //Giants
        list.add(new Stars(400, 250, 32, color(246, 229, 18), 'G'));
        list.add(new Stars(580, 275, 33, color(218, 98, 22), 'G'));
        list.add(new Stars(500, 260, 32, color(246, 209, 14), 'G'));
        //Main Sequence
        list.add(new Stars(100, 200, 23, color(55, 65, 134), 'M'));
        list.add(new Stars(140, 290, 25, color(29, 69, 154), 'M'));
        list.add(new Stars(250, 370, 22, color(106, 180, 213), 'M'));
        list.add(new Stars(425, 440, 23, color(253, 250, 80), 'M'));
        list.add(new Stars(560, 500, 24, color(244, 103, 11), 'M'));
        list.add(new Stars(650, 600, 23, color(246, 44, 4), 'M'));
        list.add(new Stars(300, 400, 22, color(200, 235, 254), 'M'));

        //White Dwarfs
        list.add(new Stars(130, 570, 16, color(33, 108, 175), 'W'));
        list.add(new Stars(275, 625, 17, color(107, 169, 190), 'W'));
        list.add(new Stars(340, 610, 15, color(177, 211, 220), 'W'));

    }

    public void settingAllAsteroids(ArrayList<Asteroids> list) {

        for (int i = 0; i < 120; i++) {
            if (i < 25) {
                int startingx = (int) (Math.random() * 400 - 800);
                int startingy = (int) (Math.random() * 400 - 800);
                int speed = (int) (Math.random() * 4 + 1);
                list.add(new Asteroids(startingx, startingy, 15, speed, speed));
            } else if (i < 50) {
                int startingx = (int) (Math.random() * 600 - 1200);
                int startingy = (int) (Math.random() * 600 - 1200);
                int speed = (int) (Math.random() * 3 + 2);
                list.add(new Asteroids(startingx, startingy, 15, speed, speed));
            } else if (i < 75) {
                int startingx = (int) (Math.random() * 800 - 1600);
                int startingy = (int) (Math.random() * 800 - 1600);
                int speed = (int) (Math.random() * 3 + 3);
                list.add(new Asteroids(startingx, startingy, 15, speed, speed));
            } else if (i < 100) {
                int startingx = (int) (Math.random() * 800 - 1600);
                int startingy = (int) (Math.random() * 800 - 1600);
                int speed = (int) (Math.random() * 5 + 2);
                list.add(new Asteroids(startingx, startingy, 15, speed, speed));
            } else {
                int speed = (int) (Math.random() * 4 + 2);
                int startingx1 = (int) (Math.random() * 6 - 595);
                int startingy1 = (int) (Math.random() * 6 - 85);
                list.add(new Asteroids(startingx1, startingy1, 15, speed, speed));
                int startingx2 = (int) (Math.random() * 6 - 85);
                int startingy2 = (int) (Math.random() * 6 - 595);
                list.add(new Asteroids(startingx2, startingy2, 15, speed, speed));
            }
        }

    }

    public boolean touchingInfoButton() {
        if (shipx + ships / 2 >= infoButtonX - infoButtonSize / 2 && shipx - ships / 2 <= infoButtonX + infoButtonSize / 2) {
            if (shipy + ships / 2 >= infoButtonY - infoButtonSize / 2 && shipy - ships / 2 <= infoButtonY + infoButtonSize / 2) {
                return true;
            }
        }
        return false;
    }
}