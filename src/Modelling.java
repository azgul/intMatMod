
public class Modelling {
	public static void main(String[] args) {
		double[] px = {.1, .4, .5};
		double[] x = {0, 1, 2};
		double[] py = {.1, .4, .5};
		double[] y = {0, 1, 2};

		double EX = median(px, x);
		double EY = median(py, y);

		double VarX = variance(px, x, EX);
		double VarY = variance(py, y, EY);
		
		int a = 1;
		int b = -3;
		double VarXY = variance(a, b, VarX, VarY);
	}
	
	private static double median(double[] px, double[] x) {
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
