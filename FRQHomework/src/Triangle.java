
	public class Triangle implements Comparable< Triangle> {
		private double legA;
		private double legB;
		private double hypotenuse;
		private double perimeter;
		private double area;
		
		public Triangle(double a, double b){		// (a)
			this.legA = a;
			this.legB = b;
			this.hypotenuse = Math.sqrt((legA*legA) + (legB*legB));
			this.perimeter = (legA+legB+hypotenuse);
			this.area = (legA/2*legB);
		}
		
		public double getPerimeter() {
			return perimeter;
		}
	 
		public void setPerimeter(double perimeter) {
			this.perimeter = perimeter;
		}
	 
		public double getArea() {
			return area;
		}
	 
		public void setArea(double area) {
			this.area = area;
		}
	 
		public double getLegA() {
			return legA;
		}
	 
		public void setLegA(int legA) {
			this.legA = legA;
		}
	 
		public double getLegB() {
			return legB;
		}
	 
		public void setLegB(int legB) {
			this.legB = legB;
		}
	 
		public double getHypotenuse() {
			return hypotenuse;
		}
	 
		public void setHypotenuse(double hypotenuse) {
			this.hypotenuse = hypotenuse;
		}
		
		public String toString(){	// (b)
			return "side A: " + legA + "\nside B: " + legB + "\nHypotenuse: " + hypotenuse + "\nPerimeter " + perimeter + "\nArea: " + area ;
		}
		
		public int compareTo(Triangle other){		// (c)
			if(this.getPerimeter() > other.getPerimeter()) return 1;
			if(this.getPerimeter() <  other.getPerimeter()) return -1;
			return 0;
		}
		
		public static Triangle randomTriangle(){	// (d) 
			Triangle t = new Triangle(getRandomLength(), getRandomLength());
			return t;
		}
		
		public static Triangle randomTriangle(int a, int b){	// (e)
			Triangle t = new Triangle(getRandomLength(a), getRandomLength(b));
			return t;
		}
	 
		private static double getRandomLength() {
			double length = Math.random()*1;
			return length;
		}
		
		private static double getRandomLength(int a) {
			double length = Math.random()*a;
			return length;
		}
	}
	 
	/*
	 * Tested it. (f)
	 */
	 
	********** (2) **********
	If code block A executes, we can conclude that student1 is ‘bigger’ than student2. If code block B executes, we can conclude that either the two students are ‘equal’ or that student1 is ‘smaller’ than student2.
	 
	********** (3) **********
	package hw10_8;
	 
	public class Student implements Comparable< Student> {
	 
		private int age;
		private String name;
	 
		public Student(String name, int age) {
			this.name = name;
			this.age = age;
		}
	 
		public Student() {
	 
		}
	 
		public int getAge() {
			return age;
		}
	 
		public void setAge(int age) {
			this.age = age;
		}
	 
		public String getName() {
			return name;
		}
	 
		public void setName(String name) {
			this.name = name;
		}
	 
		public String toString() {
			return name + ": " + age;
		}
	 
		public int compareTo(Student other) {
			if (this.getAge() > other.getAge())
				return 1;
			if (this.getAge() <  other.getAge())
				return -1;
			return 0;
		}
	}
	 
	********** (4) **********
	package hw10_8;
	 
	public class Interval implements Comparable< Interval> {
		private int min;
		private int max;
	 
		public Interval(int a, int b) {
			this.max = Math.max(a, b);
			this.min = Math.min(a, b);
		}
	 
		public int getleftEndpoint() {
			return min;
		}
	 
		public int getRightEndpoint() {
			return max;
		}
	 
		public String toString() {
			return "[" + min + ", " + max + "]";
		}
	 
		public boolean overlaps(Interval b) {
			if (this.min > b.min && this.min <  b.max)
				return true;
			if (this.max <  b.max && this.max > b.min)
				return true;
			if (b.min > this.min && b.min <  this.max)
				return true;
			if (b.max <  this.max && b.max > this.min)
				return true;
			return false;
		}
	 
		public static boolean overlaps(Interval a, Interval b) {
			if (a.min > b.min && a.min <  b.max)
				return true;
			if (a.max <  b.max && a.max > b.min)
				return true;
			if (b.min > a.min && b.min <  a.max)
				return true;
			if (b.max <  a.max && b.max > a.min)
				return true;
			return false;
		}
	 
		public int compareTo(Interval a) {
			return this.min - a.min;
		}
	}
	 
	********** (5) **********
	
	 
	public class ComplexNumber {
		private double re;
		private double im;
	 
		public ComplexNumber(double re, double im) {
			this.re = re;
			this.im = im;
		}
	 
		public int abs() {
			return Math.sqrt((re * re) + (im * im));
		}
	 
		public void add(ComplexNumber a) {
			re = re + a.re;
			im = im + a.im;
		}
	 
		public void multiply(ComplexNumber a) {
			im = (re * a.im) + (im * a.re);
			re = (re * a.re) + (im * a.im);
		}
	 
		public ComplexNumber add(ComplexNumber a, ComplexNumber b) {
			ComplexNumber c = new ComplexNumber((a.re + b.re), (a.im + b.im));
			return c;
		}
	 
		public ComplexNumber multiply(ComplexNumber a, ComplexNumber b) {
			ComplexNumber c = new ComplexNumber(((b.re * a.re) + (b.im * a.im)), ((b.re * a.im) + (b.im * a.re)));
			return c;
		}
	 
		public String toString() {
			return "(" + re + " + " + im + "i)";
		}
	}
	 
	**********(6)**********
	public int mand(Complex z0, int max) { 
	Complex z = z0; 
	Complex c = z0;  
	int iterations = 0;
	 
	for (int i = 0; i <  Integer..MAX_VALUE; i++){
	z = z.multiply(z);
	z= z.add(c);
	 
	if (z.abs() <  2) iterations++;
	else return iterations;
	if (iterations = Integer.MAX_VALUE) return Integer.MAX_VALUE;
	}
	}
}
