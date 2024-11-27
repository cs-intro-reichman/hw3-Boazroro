// Computes the periodical payment necessary to pay a given loan.
public class LoanCalc {

	static double epsilon = 0.001;
	static int iterationCounter;

	// Expects to get three command-line arguments: loan amount (double),
	// interest rate (double, as a percentage), and number of payments (int).
	public static void main(String[] args) {

		double loan = Double.parseDouble(args[0]);
		double rate = Double.parseDouble(args[1]);
		int n = Integer.parseInt(args[2]);
		double payment = Double.parseDouble(args[3]);
		System.out.println("Loan = " + loan + ", interest rate = " + rate + "%, periods = " + n);
		System.out.println(endBalance(loan, rate, n, payment));

		System.out.print("\nPeriodical payment, using brute force: ");
		System.out.println((int) bruteForceSolver(loan, rate, n, epsilon));
		System.out.println("number of iterations: " + iterationCounter);

		System.out.print("\nPeriodical payment, using bi-section search: ");
		System.out.println((int) bisectionSolver(loan, rate, n, epsilon));
		System.out.println("number of iterations: " + iterationCounter);
	}

	private static double endBalance(double loan, double rate, int n, double payment) {

		for (int i = 0; i < n; i++) {
			loan = (loan - payment) * (1 + rate / 100);

		}
		return loan;
	}

	// that will bring the ending balance of a loan close to 0.
	// Given: the sum of the loan, the periodical interest rate (as a percentage),
	// the number of periods (n), and epsilon, the approximation's accuracy
	// Side effect: modifies the class variable iterationCounter.
	public static double bruteForceSolver(double loan, double rate, int n, double epsi) {
		double payment = loan / n;
		double balance = endBalance(loan, rate, n, payment);

		iterationCounter = 0;
		while (Math.abs(balance) > epsi) {
			payment += epsi;
			balance = endBalance(loan, rate, n, payment);
			iterationCounter++;
		}

		System.out.println("Number of iterations: " + iterationCounter);
		return payment;
	}

	public static double bisectionSolver(double loan, double rate, int years, double epsilon) {
		double low = loan / years;
		double high = loan * Math.pow(1 + rate / 100, years) / years;
		iterationCounter = 0;
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

		System.out.println("Number of iterations: " + iterationCounter);
		return mid;

	}
}