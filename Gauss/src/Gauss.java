import java.util.Scanner;

public class Gauss {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int N = in.nextInt();
        Fraction[][] matrix = new Fraction[N][N + 1];
        Fraction[] x = new Fraction[N];
        for (int i = 0; i < N; i++)
            for (int j = 0; j < N + 1; j++)
                matrix[i][j] = new Fraction(in.nextInt());
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (matrix[j][i].getNumerator() != 0)
                    break;
                if (j == N - 1) {
                    System.out.print("No solution");
                    System.exit(0);
                }
            }
        }
        for (int i = 0; i < N; i++) {
            boolean a = false;
            for (int j = N - 1; j > -1; j--) {
                if (matrix[i][N].getNumerator() == 0) {
                    a = true;
                    break;
                }
                if (matrix[i][j].getNumerator() != 0)
                    a = true;
            }
            if (!a) {
                System.out.print("No solution");
                System.exit(0);
            }

        }
        int column = 0;
        findMaximalFirstElem(matrix, N, 0, column);
        boolean b = false;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N - 1; j++)
                if (matrix[j][i].getNumerator() != 0) {
                    column = i;
                    b = true;
                    break;
                }
            if (b)
                break;
        }
        for (int i = 0; i < N; i++) {
            makeMinimalLine(matrix, N, i, column);
            for (int l = 0; l < N; l++) {
                boolean a = false;
                for (int j = N - 1; j > -1; j--) {
                    if (matrix[l][N].getNumerator() == 0) {
                        a = true;
                        break;
                    }
                    if (matrix[l][j].getNumerator() != 0)
                        a = true;
                }
                if (!a) {
                    System.out.print("No solution");
                    System.exit(0);
                }

            }
            column++;
            for (int k = i; k < N; k++) {
                for (int j = k; j < N + 1; j++) {
                    int a = Fraction.findGCD(matrix[k][j]);
                    matrix[k][j].setDenominator(matrix[k][j].getDenominator() / a);
                    matrix[k][j].setNumerator(matrix[k][j].getNumerator() / a);
                }
            }
        }
        findXs(matrix, x, N);
        for (int i = 0; i < N; i++) {
            int a = Fraction.findGCD(x[i]);
            x[i].setNumerator(x[i].getNumerator() / a);
            x[i].setDenominator(x[i].getDenominator() / a);
        }
        StringBuilder s = new StringBuilder();
        for (int i = 0; i < N; i++) {
            s.append(x[i] + "\n");
        }
        System.out.print(s);
    }

    public static void changeLines(Fraction[][] matrix, int n, int m, int N) {
        Fraction[] fractions = new Fraction[N + 1];
        for (int i = 0; i < N + 1; i++)
            fractions[i] = matrix[n][i];
        for (int i = 0; i < N + 1; i++)
            matrix[n][i] = matrix[m][i];
        for (int i = 0; i < N + 1; i++)
            matrix[m][i] = fractions[i];
    }

    public static void makeMinimalLine(Fraction[][] matrix, int N, int line, int column) {
        findMaximalFirstElem(matrix, N, line, column);
        while (matrix[line][column].getNumerator() == 0 && column < N + 1)
            column++;
        for (int i = line; i < N; i++) {
            Fraction fraction;
            fraction = matrix[i][column];
            if (fraction.getNumerator() != 0)
                for (int j = N; j >= column; j--)
                    matrix[i][j] = Fraction.divide(matrix[i][j], fraction);
        }
        for (int j = line + 1; j < N; j++)
            if (matrix[j][column].getNumerator() != 0)
                for (int i = column; i < N + 1; i++) {
                    matrix[j][i] = Fraction.difference(matrix[j][i], matrix[line][i]);
                }

    }

    public static void findMaximalFirstElem(Fraction[][] matrix, int N, int line, int column) {
        Fraction fraction;
        fraction = matrix[line][column];
        for (int i = line + 1; i < N; i++)
            if (fraction.compareTo(matrix[i][column]) < 0) {
                fraction = matrix[i][column];
                changeLines(matrix, line, i, N);
            }
    }

    public static void findXs(Fraction[][] matrix, Fraction[] x, int N) {
        Fraction[] y = new Fraction[N];
        for (int i = 0; i < N; i++) {
            y[i] = matrix[i][N];
        }
        for (int i = N - 1; i > -1; i--) {
            x[i] = y[i];
            for (int j = 0; j < i; j++) {
                y[j] = Fraction.difference(y[j], Fraction.multiplication(matrix[j][i], x[i]));
            }
        }
        for (int i = 0; i < N; i++) {
            if ((x[i].getNumerator() < 0 && x[i].getDenominator() < 0) || (x[i].getNumerator() == 0 && x[i].getDenominator() < 0)) {
                x[i].setDenominator(x[i].getDenominator() * (-1));
                x[i].setNumerator(x[i].getNumerator() * (-1));
            }
            if (x[i].getDenominator() < 0){
                x[i].setNumerator(x[i].getNumerator()*(-1));
                x[i].setDenominator(x[i].getDenominator()*(-1));
            }
        }
    }
}
