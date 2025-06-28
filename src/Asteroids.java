import java.util.ArrayList;

public class Asteroids {
    int asteroidX, asteroidY,  asteroidSize, asteroidspeedX, asteroidspeedY;
    public Asteroids (int ax, int ay, int as, int axs, int ays) {
        asteroidX = ax;
        asteroidY = ay;
        asteroidSize = as;
        asteroidspeedX = axs;
        asteroidspeedY = ays;
    }
    public void move() {
        asteroidX += asteroidspeedX;
        asteroidY += asteroidspeedY;
    }
//    public static void offBounds(ArrayList<Asteroids> list) {
//        ArrayList<Integer> removelist = new ArrayList<>();
//        for (int i = list.size()-1; i > 0; i--) { //backwards cuz like it wont shift the whole thingy
//            if (list.get(i).asteroidX > 700 && list.get(i).asteroidY > 700) { //screen width variable?
//                removelist.add(i);
//            }
//        }
//        for (int j = removelist.size() - 1; j >= 0; j--) {
//            System.out.println(j);
//            list.remove(j);
//        }
//        removelist.clear();
//        System.out.println(list.size());
//
//    }
    public static boolean hit(int userx, int usery, int usersize, int astx, int asty, int astsize) {
        int userRadius = usersize/2;
        int astRadius = astsize/2;
        if (userx + userRadius > astx - astRadius && userx - userRadius < astx + astRadius) {
            if(usery + userRadius > asty - astRadius && usery - userRadius < asty + astRadius) {
                //add the actual logic later with maybe lives/ and or other stuff like gameover screen
                System.out.println("Game Over");
                return true;
            }
        }
        return false;
    }
}
