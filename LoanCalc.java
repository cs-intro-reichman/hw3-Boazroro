public class LoanCalc {
	
	public class LoanCalc {
	
        static double epsilon = 0.001;
        static int iterationCounter;
        
        public static void main(String[] args) {		
            double loan = Double.parseDouble(args[0]);
            double rate = Double.parseDouble(args[1]);
            int n = Integer.parseInt(args[2]);
            System.out.println("Loan = " + loan + ", interest rate = " + rate + "%, periods = " + n);
    
            System.out.print("\nPeriodical payment, using brute force: ");
            System.out.println((int) bruteForceSolver(loan, rate, n, epsilon));
            System.out.println("number of iterations: " + iterationCounter);
    
            System.out.print("\nPeriodical payment, using bi-section search: ");
            System.out.println((int) bisectionSolver(loan, rate, n, epsilon));
            System.out.println("number of iterations: " + iterationCounter);
        }
    
        private static double endBalance(double loan, double rate, int n, double payment) {	
            double loan1 = loan, rate1 = (100 + rate) / 100, payment1 = payment;
            int x = n;
            for (int i = 1; i <= n; i++)
                loan1 = (loan1 - payment1) * rate1;
            return loan1;
        }
        
        public static double bruteForceSolver(double loan, double rate, int n, double epsilon) {
            double o = loan / n;
            double sum = (endBalance(loan, rate, n, o));
            double epsilon1 = epsilon;
            while (sum > 0) {
                iterationCounter++;
                o += epsilon1;
                sum = (endBalance(loan, rate, n, o));
            }
            return o;
        }
        
        public static double bisectionSolver(double loan, double rate, int n, double epsilon) {  
            double h = loan;
            double l = loan / n;
            double m = h + l / 2;
            iterationCounter = -1;
            double sum = (endBalance(loan, rate, n, m));
            while (h - l > epsilon) {
                iterationCounter++;
                sum = (endBalance(loan, rate, n, m));
            
                if (sum > 0) {
                    l = m;
                    m = (l + h) / 2;
                } else {
                    h = m;
                    m = (h + l) / 2;
                }
                sum = (endBalance(loan, rate, n, m));
            }
          
           return m;
        }
    }
}
    