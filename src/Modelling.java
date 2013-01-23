import java.util.ArrayList;


public class Modelling {
	public static void main(String[] args) {
		// change these arrays
		double[] px = {.1, .4, .5};
		double[] x = {0, 1, 2};
		double[] py = {.1, .4, .5};
		double[] y = {0, 1, 2};

		double EX = mean(px, x);
		double EY = mean(py, y);

		double VarX = variance(px, x, EX);
		double VarY = variance(py, y, EY);
		
		
		int a = 1; // change this
		int b = -3; // change this
		double VarXY = variance(a, b, VarX, VarY); // calculates combined variance
		
		probability(0, ">", px, x); // change operator
		probability(0, "<", px, x, "<=", py, y); // change operators

		likelihoodFunction(px, x, py, y);
		
		double[] pz = {.19, .16, .4, .25};
		double[] z = {0, 1, 2, 4};
		
		double EZ = mean(pz, z);
		
		likelihoodFunction(pz, z, px, x);
		
		double pzx[] = {.271, .064, .24, .3, .125};
		double zx[] = {0, 1, 2, 4, 8};
		
		double EZX = mean(pzx, zx);
	
		covariance(EZX, EZ, EX);
	}
	
	private static void likelihoodFunction(double[] px, double[] x, double[] py, double[] y) {		
		ArrayList<Integer> combinations = new ArrayList<Integer>();
		
		for (int j = 0; j < 5; j++)
			for (int k = 0; k < 5; k++) {
				try {
					int res = (int) (x[j] * x[k]);
					if (!combinations.contains(res))
						combinations.add(res);
				} catch (ArrayIndexOutOfBoundsException e) {
					continue;
				}
			}
		
		for (Integer i : combinations) {
			double probability = 0;
			String intermediate = "";
			for (int j = 0; j < px.length; j++)
				for (int k = 0; k < py.length; k++)
					if (x[j] * y[k] == i) {
						probability += px[j] * py[k];
						intermediate += String.format("%s * %s + ", px[j], py[k]);
					}
			
			try {
				intermediate = intermediate.substring(0, intermediate.length()-3);
			} catch (StringIndexOutOfBoundsException e) {continue;}
			
			System.out.println(String.format("Probability for %s (%s): %s", i, intermediate, probability));
		}
	}
	
	private static double covariance(double EXY, double EX, double EY) {
		double result = EXY - EX*EY;
		
		System.out.println(String.format("Cov(X,Y) = E(XY) - EX * EY = %s - %s * %s = %s", EXY, EX, EY, result));
		
		return result;
	}
	
	private static double probability(int limit, String operator1, double[] px, double[] x, String operator2, double[] py, double[] y) {
		double result = 0.0;
		
		String res = "";
		
		System.out.println(String.format("Finding the probability of P(%s %s X %s Y)", limit, operator1, operator2));
		
		for (int i = 0; i < px.length; i++)
			for (int j = 0; j < py.length; j++)
				if (operator1.equals("<") && operator2.equals("<=")) {
					if (limit < x[i] && x[i] <= y[j]) {
						result += px[i] * py[j];
						res += String.format("%s * %s + ", px[i], py[j]);
					}
				} else if (operator1.equals("<") && operator2.equals("<")) {
					if (limit < x[i] && x[i] < y[j]) {
						result += px[i] * py[j];
						res += String.format("%s * %s + ", px[i], py[j]);
					}
				}
		
		res = res.substring(0, res.length()-2);
		
		System.out.println(String.format("%s= %s\n", res, result));
		
		return result;
	}
	
	private static double probability(int limit, String operator, double[] px, double[] x) {
		double result = 0;
		
		String res = "";

		System.out.println(String.format("Finding the probability of P(X%s%s)", operator, limit));
		
		for (int i = 0; i < px.length; i++) {
			if (operator.equals(">"))
				if (x[i] > limit) {
					result += px[i];
					res += px[i] + " + ";
				}
			if (operator.equals("<"))
				if (x[i] < limit) {
					result += px[i];
					res += px[i] + " + ";
				}
			if (operator.equals("<="))
				if (x[i] <= limit) {
					result += px[i];
					res += px[i] + " + ";
				}
			if (operator.equals(">="))
				if (x[i] >= limit) {
					result += px[i];
					res += px[i] + " + ";
				}
		}
		
		res = res.substring(0, res.length()-2);
		
		System.out.println(String.format("%s= %s\n", res, result));
		
		return result;
	}
	
	private static double mean(double[] px, double[] x) {
		double result = 0;
		String res = "";
		
		System.out.println(String.format("Calculating median of: px: %s -- x: %s", doubleArrayToString(px), doubleArrayToString(x)));
		
		for (int i = 0; i < px.length; i++) {
			result += px[i] * x[i];
			res += px[i] + "*" + x[i] + " + ";
		}
		
		res = res.substring(0, res.length()-2);
		
		System.out.println(res + "= " + result + "\n");
		
		return result;
	}
	
	private static double variance(int a, int b, double VarX, double VarY) {
		double result = 0;
		
		System.out.println(String.format("Calculating combined variance of: Var(%sX + %sY)", a, b));
		
		result = Math.pow(a, 2) * VarX + Math.pow(b, 2) * VarY;
		System.out.println(String.format("%s^2 * Var(X) + %s^2 * Var(Y) = %s\n", a, b, result));
		
		return result;
	}
	
	private static double variance(double[] px, double[] x, double EX) {
		double result = 0;
		String res = "";
	
		System.out.println(String.format("Calculating variance of: px: %s -- x: %s -- EX: %s", doubleArrayToString(px), doubleArrayToString(x), EX));
		
		for (int i = 0; i < px.length; i++) {
			result += Math.pow((x[i] - EX), 2) * px[i];
			res += String.format("(%s - %s)^2 * %s", x[i], EX, px[i]);
		}

		System.out.println(res + "= " + result + "\n");
		
		return result;
	}
	
	private static String doubleArrayToString(double[] xs) {
		String str = "{";
		
		for (double x : xs) {
			str += x + ",";
		}
		str = str.substring(0, str.length()-1);
		
		str += "}";
		
		return str;
	}
}
