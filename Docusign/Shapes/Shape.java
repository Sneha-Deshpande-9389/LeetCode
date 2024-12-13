package Docusign.Shapes;

public abstract class Shape {
    protected double x; // X-coordinate of the shape's position
    protected double y; // Y-coordinate of the shape's position
    protected String defaultColor;
    protected String eventColor;
    protected boolean isHovered;

    public Shape(double x, double y, String color) {
        this.x = x;
        this.y = y;
        this.defaultColor = color;

    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    public String getColor() {
        return defaultColor;
    }

    public void setColor(String color) {
        this.defaultColor = color;
    }


    // Abstract methods for area and perimeter
    public abstract double getArea();
    public abstract double getPerimeter();
}
