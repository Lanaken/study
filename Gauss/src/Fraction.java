public class Fraction implements Comparable<Fraction> {
    private int numerator;
    private int denominator = 1;

    Fraction(int numerator) {
        this.numerator = numerator;
    }

    public int getDenominator() {
        return denominator;
    }

    public int getNumerator() {
        return numerator;
    }

    public void setDenominator(int denominator) {
        if (denominator < 0)
            this.setNumerator(this.numerator * -1);
        this.denominator = Math.abs(denominator);
    }

    public void setNumerator(int numerator) {
        this.numerator = numerator;
    }

    public static Fraction sum(Fraction x, Fraction y) {
        Fraction fraction = new Fraction(0);
        if (x.denominator == y.denominator) {
            fraction.setNumerator(x.getNumerator() + y.getNumerator());
            fraction.setDenominator(x.denominator);
        } else {
            fraction.setDenominator(x.denominator * y.denominator);
            fraction.setNumerator(x.numerator * y.denominator + y.numerator * x.denominator);
        }
        return fraction;
    }

    public static Fraction difference(Fraction x, Fraction y) {
        Fraction fraction = new Fraction(0);
        if (x.denominator == y.denominator) {
            fraction.setNumerator(x.getNumerator() - y.getNumerator());
            fraction.setDenominator(x.denominator);
        } else {
            fraction.setDenominator(x.denominator * y.denominator);
            fraction.setNumerator(x.numerator * y.denominator - y.numerator * x.denominator);
        }
        int a = Fraction.findGCD(fraction);
        fraction.setNumerator(fraction.getNumerator() / a);
        fraction.setDenominator(fraction.getDenominator() / a);
        return fraction;
    }

    public static Fraction divide(Fraction x, Fraction y) {
        Fraction fraction = new Fraction(0);
        if (x.numerator == 0)
            return fraction;
        if (y.numerator == 0)
            return fraction;
        if (x.denominator == y.denominator) {
            if (x.numerator == y.numerator)
                fraction.setNumerator(1);
            else {
                fraction.setNumerator(x.numerator);
                fraction.setDenominator(y.numerator);
            }
        } else {
            if (x.numerator == y.numerator) {
                fraction.setNumerator(y.denominator);
                fraction.setDenominator(x.denominator);
            } else {
                fraction.setNumerator(x.numerator * y.denominator);
                fraction.setDenominator(x.denominator * y.numerator);
            }
        }
        int a = Fraction.findGCD(fraction);
        fraction.setNumerator(fraction.getNumerator() / a);
        fraction.setDenominator(fraction.getDenominator() / a);
        return fraction;
    }

    public static Fraction multiplication(Fraction x, Fraction y) {
        Fraction fraction = new Fraction(0);
        if (x.numerator == 0 || y.numerator == 0)
            return fraction;
        if (x.getNumerator() == y.getDenominator()) {
            if (x.getDenominator() == y.getNumerator())
                fraction.setNumerator(1);
            else {
                fraction.setNumerator(y.getNumerator());
                fraction.setDenominator(x.getDenominator());
            }
        } else if (x.getDenominator() == y.getNumerator()) {
            fraction.setNumerator(x.getNumerator());
            fraction.setDenominator(y.getDenominator());
        } else {
            fraction.setNumerator(x.getNumerator() * y.getNumerator());
            fraction.setDenominator(x.getDenominator() * y.getDenominator());
        }
        int a = Fraction.findGCD(fraction);
        fraction.setNumerator(fraction.getNumerator() / a);
        fraction.setDenominator(fraction.getDenominator() / a);
        return fraction;
    }

    public static int findGCD(Fraction fraction) {
        int a = Math.abs(fraction.numerator);
        int b = Math.abs(fraction.denominator);
        while (a != 0 && b != 0) {
            if (a > b)
                a %= b;
            else
                b %= a;
        }
        return a + b;
    }

    @Override
    public String toString() {
        return (numerator + "/" + denominator);
    }

    @Override
    public int compareTo(Fraction o) {
        return (int) Math.ceil(Math.abs(this.numerator * o.denominator) - Math.abs(o.numerator * this.denominator));
    }
}
