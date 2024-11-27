public class LoanCalc {

    static double epsilon = 0.001;
    static int iterationCounter;

    public static void main(String[] args) {
        double loan = Double.parseDouble(args[0]);
        double rate = Double.parseDouble(args[1]);
        int n = Integer.parseInt(args[2]);

        double payment = calculatePayment(loan, rate, n);

        System.out.println("Loan = " + loan + ", interest rate = " + rate + "%, periods = " + n);
        System.out.println("Calculated periodical payment: " + payment);

        System.out.println("Ending balance with calculated payment: " + endBalance(loan, rate, n, payment));

        System.out.print("\nPeriodical payment, using brute force: ");
        double bruteForcePayment = bruteForceSolver(loan, rate, n, epsilon);
        System.out.println(bruteForcePayment);
        System.out.println("Number of iterations (brute force): " + iterationCounter);

        System.out.print("\nPeriodical payment, using bisection search: ");
        double bisectionPayment = bisectionSolver(loan, rate, n, epsilon);
        System.out.println(bisectionPayment);
        System.out.println("Number of iterations (bisection): " + iterationCounter);
    }

    private static double endBalance(double loan, double rate, int n, double payment) {
        for (int i = 0; i < n; i++) {
            loan = (loan - payment) * (1 + rate / 100);
        }
        return loan;
    }

    public static double bruteForceSolver(double loan, double rate, int n, double epsi) {
        iterationCounter = 0;
        double payment = loan / n;
        double balance = endBalance(loan, rate, n, payment);

        while (Math.abs(balance) > epsi) {
            payment += epsi;
            balance = endBalance(loan, rate, n, payment);
            iterationCounter++;
        }
        return payment;
    }

    public static double bisectionSolver(double loan, double rate, int years, double epsilon) {
        iterationCounter = 0;
        double low = loan / years;
        double high = loan * Math.pow(1 + rate / 100, years) / years;
        double mid = 0;

        while ((high - low) > epsilon) {
            mid = (low + high) / 2;
            double balance = endBalance(loan, rate, years, mid);

            if (balance > 0) {
                low = mid;
            } else {
                high = mid;
            }
            iterationCounter++;
        }
        return mid;
    }

    private static double calculatePayment(double loan, double rate, int n) {
        double low = loan / n;
        double high = loan * Math.pow(1 + rate / 100, n) / n;
        return (low + high) / 2;  
    }
}