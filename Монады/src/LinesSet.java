import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class LinesSet {
    private static double lengths = 0;
    private static TreeSet<Line> lines = new TreeSet<>();

    private LinesSet(){}

    public static void addLine(Line line) {
        lines.add(line);
        lengths += line.getLength();
    }

    public static double getLengths() {
        return lengths;
    }

    public static TreeSet<Line> getLines() {
        return lines;
    }

    public static Stream<Line> makeStream() {
        return  lines.stream().filter(Line::isTogether);
    }

    public static Optional<Line> makeOptional() {
        Line n = lines.first().getLength() > lengths - lines.first().getLength()? lines.first() : null;
        return Optional.ofNullable(n);
    }
}
