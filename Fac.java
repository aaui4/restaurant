public class Factorial {
    public static void main(String[] args) {
        int fact = 1;

        for (int i = 1; i <= 8; i++) {
            fact *= i; // حساب العاملي
            System.out.print(i + "!=" + fact + "  "); // طباعة في نفس السطر
        }
    }
}
