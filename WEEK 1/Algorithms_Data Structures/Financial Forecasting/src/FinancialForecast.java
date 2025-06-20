public class FinancialForecast {

    // Recursive method 
    public static double futureValueRecursive(double principal, double rate, int years) {
        if (years == 0) {
            return principal;
        }
        return futureValueRecursive(principal, rate, years - 1) * (1 + rate);
    }

    // Iterative method (optimized)
    public static double futureValueIterative(double principal, double rate, int years) {
        double result = principal;
        for (int i = 1; i <= years; i++) {
            result *= (1 + rate);
        }
        return result;
    }

    public static void main(String[] args) {
        double principal = 1000.0; 
        double rate = 0.05;         
        int years = 10;            

        double recursiveResult = futureValueRecursive(principal, rate, years);
        double iterativeResult = futureValueIterative(principal, rate, years);

        System.out.printf("Future value (Recursive) after %d years: %.2f\n", years, recursiveResult);
        System.out.printf("Future value (Iterative) after %d years: %.2f\n", years, iterativeResult);
    }
}
