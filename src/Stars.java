public class Stars {
    int starX, starY, starSize, starColor;
    char typeOfStar;
    public Stars(int starx, int stary, int stars, int starc, char typeOfStar) {
        starX = starx;
        starY = stary;
        starSize = stars;
        starColor = starc;
        this.typeOfStar = typeOfStar;
    }

    public static boolean inBounds(int userx, int usery, int xOfStar, int yOfStar, int sOfStar) {
        int starRadius = sOfStar/2;
        if (userx > xOfStar - starRadius && userx < xOfStar + starRadius) {
            if (usery > yOfStar - starRadius && usery < yOfStar + starRadius) {
                return true;
            }
        }
        return false;
    }
}
