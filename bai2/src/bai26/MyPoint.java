package bai26;

public class MyPoint {
	    private int x = 0;
	    private int y = 0;

	    public MyPoint() {
	    }

	    public MyPoint(int x, int y) {
	        this.x = x;
	        this.y = y;
	    }

	    public int getX() {
	        return x;
	    }

	    public void setX(int x) {
	        this.x = x;
	    }

	    public int getY() {
	        return y;
	    }

	    public void setY(int y) {
	        this.y = y;
	    }

	    public int[] getXY() {
	        return new int[]{x, y};
	    }

	    public void setXY(int x, int y) {
	        this.x = x;
	        this.y = y;
	    }

	    @Override
	    public String toString() {
	        return "(" + x + ", " + y + ")";
	    }

	    public double distance(int x, int y) {
	        int xDiff = this.x - x;
	        int yDiff = this.y - y;
	        return Math.sqrt(xDiff * xDiff + yDiff * yDiff);
	    }

	    public double distance(MyPoint another) {
	        int xDiff = this.x - another.x;
	        int yDiff = this.y - another.y;
	        return Math.sqrt(xDiff * xDiff + yDiff * yDiff);
	    }

	    public double distance() {
	        return Math.sqrt(x * x + y * y);
	    }

	    public static void main(String[] args) {
	        MyPoint p1 = new MyPoint();
	        System.out.println(p1);

	        p1.setX(8);
	        p1.setY(6);
	        System.out.println("x is: " + p1.getX());
	        System.out.println("y is: " + p1.getY());

	        p1.setXY(3, 0);
	        System.out.println(p1.getXY()[0]);
	        System.out.println(p1.getXY()[1]);
	        System.out.println(p1);

	        MyPoint p2 = new MyPoint(0, 4);
	        System.out.println(p2);

	        System.out.println(p1.distance(p2));
	        System.out.println(p2.distance(p1));
	        System.out.println(p1.distance(5, 6));
	        System.out.println(p1.distance());

	        MyPoint[] points = new MyPoint[10];
	        for (int i = 0; i < points.length; i++) {
	            points[i] = new MyPoint(i + 1, i + 1);
	            System.out.println(points[i]);
	        }
	    }
	}


