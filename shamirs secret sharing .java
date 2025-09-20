import java.math.BigInteger;
import java.util.*;

public class ShamirSecretSharing {

    static BigInteger lagrangeConstant(List<Point> points, int k) {
        BigInteger secret = BigInteger.ZERO;

        for (int i = 0; i < k; i++) {
            BigInteger xi = points.get(i).x;
            BigInteger yi = points.get(i).y;
            BigInteger numerator = BigInteger.ONE;
            BigInteger denominator = BigInteger.ONE;

            for (int j = 0; j < k; j++) {
                if (i == j) continue;
                BigInteger xj = points.get(j).x;

                numerator = numerator.multiply(xj.negate());
                denominator = denominator.multiply(xi.subtract(xj));
            }

            if (denominator.equals(BigInteger.ZERO)) {
                throw new ArithmeticException("Denominator is zero during Lagrange interpolation");
            }

            BigInteger Li = numerator.divide(denominator);
            secret = secret.add(yi.multiply(Li));
        }

        return secret;
    }

    static class Point {
        BigInteger x;
        BigInteger y;

        Point(BigInteger x, BigInteger y) {
            this.x = x;
            this.y = y;
        }
    }

    public static void main(String[] args) {
        int k1 = 3;
        List<Point> points1 = new ArrayList<>();
        points1.add(new Point(BigInteger.valueOf(1), new BigInteger("4", 10)));
        points1.add(new Point(BigInteger.valueOf(2), new BigInteger("111", 2)));
        points1.add(new Point(BigInteger.valueOf(3), new BigInteger("12", 10)));
        points1.add(new Point(BigInteger.valueOf(6), new BigInteger("213", 4)));
        BigInteger secret1 = lagrangeConstant(points1, k1);
        System.out.println("Secret (Testcase 1): " + secret1);

        int k2 = 7;
        List<Point> points2 = new ArrayList<>();
        points2.add(new Point(BigInteger.valueOf(1), new BigInteger("13444211440455345511", 6)));
        points2.add(new Point(BigInteger.valueOf(2), new BigInteger("aed7015a346d635", 15)));
        points2.add(new Point(BigInteger.valueOf(3), new BigInteger("6aeeb69631c227c", 15)));
        points2.add(new Point(BigInteger.valueOf(4), new BigInteger("e1b5e05623d881f", 16)));
        points2.add(new Point(BigInteger.valueOf(5), new BigInteger("316034514573652620673", 8)));
        points2.add(new Point(BigInteger.valueOf(6), new BigInteger("2122212201122002221120200210011020220200", 3)));
        points2.add(new Point(BigInteger.valueOf(7), new BigInteger("20120221122211000100210021102001201112121", 3)));
        points2.add(new Point(BigInteger.valueOf(8), new BigInteger("20220554335330240002224253", 6)));
        points2.add(new Point(BigInteger.valueOf(9), new BigInteger("45153788322a1255483", 12)));
        points2.add(new Point(BigInteger.valueOf(10), new BigInteger("45153788322a1255483", 16)));  // Changed from base 7 to 16

        BigInteger secret2 = lagrangeConstant(points2, k2);
        System.out.println("Secret (Testcase 2): " + secret2);
    }
}
