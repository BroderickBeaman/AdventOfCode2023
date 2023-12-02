public class Game {

    private int id;
    private int red;
    private int green;
    private int blue;

    public Game(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public int getRed() {
        return red;
    }

    public void setRed(int red) {
        this.red = red;
    }

    public int getGreen() {
        return green;
    }

    public void setGreen(int green) {
        this.green = green;
    }

    public int getBlue() {
        return blue;
    }

    public void setBlue(int blue) {
        this.blue = blue;
    }

    public boolean isGameValid() {
        return red <= 12 && green <= 13 && blue <= 14;
    }

    public int getPower() {
        return red * green * blue;
    }
}
