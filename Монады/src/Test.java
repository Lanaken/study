import java.util.*;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Test {
    public static void main(String[] args) {
        Point[] points = {
                new Point(-2,3),
                new Point(-2,-2),
                new Point(1,5),
                new Point(3,-6),
                new Point(100,100)
        };
        Line[] lines = {
                new Line(points[0],points[3]),
                new Line(points[1],points[2]),
                new Line(points[1],points[4])
        };
        LinesSet.addLine(lines[0]);
        LinesSet.addLine(lines[1]);
        LinesSet.addLine(lines[2]);
        Random generator = new Random();
        Supplier<Stream<Point>> pointStream = () -> Stream.generate(() ->
                new Point(100 - generator.nextInt(), 100 - generator.nextInt())).limit(5);
        Supplier<Stream<Line>> lineStream = () -> Stream.generate(() ->
                new Line(pointStream.get().findAny().get(), pointStream.get().findAny().get())).limit(10);
        Map<String, List<Line>> map = LinesSet.makeStream().collect(
                Collectors.groupingBy(Line::getGroup)
        );
        System.out.println(map);

        Optional<Line>  i = LinesSet.makeOptional();
        i.ifPresent(System.out::println);
    }
}
