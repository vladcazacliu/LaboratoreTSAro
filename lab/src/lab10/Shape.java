package lab10;

public abstract class Shape{
    private double length;
    private double width;

    // constructor
    public Shape(){
        setLength(0.0f);
        setWidth(0.0f);
    }
    public Shape(double length){
        setLength(length);
        setWidth(0.0f);
    }
    public Shape(double length, double width){
        setLength(length);
        setWidth(width);
    }
    // SETTERS
    public void setLength(double len){
        if(len >= 0.0f)
            this.length = len;
        else
            throw new IllegalArgumentException("Length must be >= 0.0f");
    }
    public void setWidth(double wid){
        if(wid >= 0.0f)
            this.width = wid;
        else
            throw new IllegalArgumentException("Width must be >= 0.0f");
    }
    // GETTERS
    public double getLength(){
        return this.length;
    }
    public double getWidth(){
        return this.width;
    }
    // ABSTRACT METHODS
    // get area
    public abstract double getArea();
}
