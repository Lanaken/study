


public class Line implements Comparable<Line> {
    private Point point1;
    private Point point2;
    private double length;
    private String group;

    Line(Point point1, Point point2){
        this.point1 = point1;
        this.point2 = point2;
        double x = point2.getX() - point1.getX();
        double y = point2.getY() - point1.getY();
        length = Math.sqrt(x*x + y*y);
        if (length < 10) {
            group = "1";
            return;
        }
        if (length < 20) {
            group = "2";
            return;
        }
        if (length < 30){
            group = "3";
            return;
        }
        group = "4";
    }

    public double getLength() {
        return length;
    }

    public Point getPoint1() {
        return point1;
    }

    public Point getPoint2() {
        return point2;
    }

    public String getGroup() {
        return group;
    }

    public boolean isTogether() {
        double x1 = point1.getX();
        double y1 = point1.getY();
        return (x1 != 0 && y1 != 0 && point2.getX() != 0 && point2.getY() != 0 &&
                ((x1 < 0 && y1 > 0 && point2.getX() > 0 && point2.getY() < 0) ||
                        (x1 > 0 && y1 < 0 && point2.getX() < 0 && point2.getY() > 0) ||
                        (x1 > 0 && y1 > 0 && point2.getX() < 0 && point2.getY() < 0) ||
                        (x1 < 0 && y1 < 0 && point2.getX() > 0 && point2.getY() > 0)));
    }

    @Override
    public String toString() {
        StringBuilder s = new StringBuilder();
        s.append(length);
        return s.toString();
    }

    @Override
    public int compareTo(Line o) {
        return (int) o.length - (int) this.length ;
    }
}
