package lab10;

public class Tetrahedron extends ThreeDimensionalShape{
    // constructor
    // height = sqrt(3/2) * length
    public Tetrahedron(double length){
        super(length);
        super.setHeight(Math.sqrt(3.0f / 2.0f) * length);
    }
    // GETTERS
    @Override
    public double getArea(){
        return Math.sqrt(3.0f) * Math.pow(super.getLength(), 2);
    }
    @Override
    public double getVolume(){
        return Math.pow(super.getLength(), 3) / 12.0f * Math.sqrt(2);
        //return Math.sqrt(2.0f) / 12 * Math.pow(getArea(), 3);
    }
    // String representation of object
    @Override
    public String toString(){
        return String.format("%s: %.2f\n%s:%.2f\n",
                "Area", getArea(), "Volume", getVolume());
    }
}
