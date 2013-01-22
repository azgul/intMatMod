
public class Modelling {
	public static void main(String[] args) {
		double[] px = {.1, .4, .5};
		double[] x = {0, 1, 2};
		
		double EX = median(px, x);
		
		double VarX = variance(px, x, EX);
		
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
		
		System.out.println(res + "= " + result);
		
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

		System.out.println(res + "= " + result);
		
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
