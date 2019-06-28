




public class Point {
    private double x;
    private double y;
    Point(double x,double y){
        this.x = x;
        this.y = y;
    }

    public double getY() {
        return y;
    }

    public double getX() {
        return x;
    }

    @Override
    public String toString() {
        StringBuilder s = new StringBuilder();
        s.append(x);
        s.append(y);
        return s.toString();
    }
}
