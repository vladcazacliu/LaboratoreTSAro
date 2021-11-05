package lab10;

public abstract class ThreeDimensionalShape extends Shape{
    private double height;

    // constructor
    public ThreeDimensionalShape(){
        super();
        setHeight(0);
    }
    public ThreeDimensionalShape(double width){
        super(width);
    }
    public ThreeDimensionalShape(double length, double width){
        super(length, width);
    }
    public ThreeDimensionalShape(double length, double width, double height){
        super(length, width);
        setHeight(height);
    }
    // SETTERS
    public void setHeight(double h){
        this.height = (h > 0.0f) ? h : 0.0f;
    }
    // GETTERS
    public double getHeight(){
        return this.height;
    }
    // ABSTRACT METHODS
    // get area
    public abstract double getArea();
    // get volume
    public abstract double getVolume();
}
