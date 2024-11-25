// Implements algebraic operations and the square root function without using 
// the Java operations a + b, a - b, a * b, a / b, a % b, and without calling 
// Math.sqrt. All the functions in this class operate on int values and
// return int values.

public class Algebra {
	public static void main(String args[]) {
		// Tests some of the operations
		System.out.println(plus(2, 3)); // 2 + 3 Completed&&
		System.out.println(minus(7, 2)); // 7 - 2 Completed&&
		System.out.println(minus(2, 7)); // 2 - 7 Completed&&
		System.out.println(times(3, 4)); // 3 * 4 Completed&&
		System.out.println(plus(2, times(4, 2))); // 2 + 4 * 2 Completed&&
		System.out.println(pow(5, 3)); // 5^3 Completed&&
		System.out.println(pow(3, 5)); // 3^5 Completed&&
		System.out.println(div(12, 3)); // 12 / 3 Completed&&
		System.out.println(div(5, 5)); // 5 / 5 Completed&&
		System.out.println(div(25, 7)); // 25 / 7 Completed&&
		System.out.println(mod(25, 7)); // 25 % 7 Completed$$
		System.out.println(mod(120, 6)); // 120 % 6 Completed$$
		System.out.println(sqrt(36));
		System.out.println(sqrt(263169));
		System.out.println(sqrt(76123));
	}

	// Returns x1 + x2 Completed&&
	public static int plus(int x1, int x2) {
		int sum = x1;
		if (x2 > 0)
			for (int i = 0; i < Math.abs(x2); i++) {

				sum++;
			}
		else {

			for (int j = 0; j < x2 * -1; j++) {
				sum--;
			}
		}
		return sum;
	}

	// Returns x1 - x2 Completed&&
	public static int minus(int x1, int x2) {
		int sum = x1;
		if (x2 < 0) {

			for (int i = 0; i < x2 * -1; i++) {
				sum++;
			}
		} else {
			for (int j = 0; j < x2; j++) {
				sum--;
			}
		}
		return sum;
	}

	// Returns x1 * x2 Completed&&
	public static int times(int x1, int x2) {
		int sum = 0;

		// Handle the zero case
		if (x1 == 0 || x2 == 0) {
			return sum;
		}

		if (x1 < 0) {
			x1 = -x1;
			x2 = -x2;
		}

		if (x2 < 0) {
			for (int i = 0; i < x1; i++) {
				sum = plus(sum, x2);
			}
			return sum;
		} else {

			for (int i = 0; i < x1; i++) {
				sum = plus(sum, x2);
			}
			return sum;
		}
	}

	// Returns x^n (for n >= 0) Completed&&
	public static int pow(int x, int n) {
		int sum = 1;
		if (x < 0 && n % 2 == 0) {
			x = -x;
		}

		for (int i = 1; i <= n; i++) {
			sum = times(sum, x);

		}
		return sum;
	}

	// Returns the integer part of x1 / x2 Completed&&
	public static int div(int x1, int x2) {
		int sum = 0;
		if (x1 == 0) {
			return sum;

		}
		for (int i = 0; i < x2; i++) {
			if (x1 > 0 && x2>0) {
				x1 = minus(x1, x2);

				sum++;

			}
			else{
				x1 = minus(x1, x2*-1);
				sum++;
			}

		}
		return sum;
	}

	// Returns x1 % x2
	public static int mod(int x1, int x2) {
		int sum = 0;
		int m = 1;
		for (int i = 0; i < x2; i++) {
			while (x1 >= x2) {
				x1 = minus(x1, x2);

				sum++;

			}

		}
		return x1;

	}

	// Returns the integer part of sqrt(x)
	public static double sqrt(double x) {
		double epslion = 0.01, l = 1.0, H = x;

		double g = (l + H) / 2.0;

		while (Math.abs((g * g) - x) >= epslion) {
			if (g * g < x) {
				l = g;
			} else {
				H = g;
				g = (l + H) / 2.0;
			}

		}
		return g;
	}

}