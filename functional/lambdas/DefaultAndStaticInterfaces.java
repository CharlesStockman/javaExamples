/**
 * Start by making a normal Java-7-style application with these features:
 * An interface called RegularPolygon with two abstract methods: getNumSides and getSideLength.
 * A class EquilateralTriangle that implements the interface, has getNumSides return 3 and getSideLength 
 * return an instance variable that is set by the constructor.
 * 2. Add a static totalSides method, that given a RegularPolygon[], returns the sum of the number of
 *    sides of all the elements.
 * 3. Add two default methods:
 *    getInteriorAngle ( (n-2)π/n in radians 
 */

package functional.lambdas;

interface RegularPolygon {
     
    /**
     * @return Return the number of sides of the polygon
     */
    public int getNumberOfSides();

    /**
     * @return Return the length of each side
     */
    public int getLengthOfSides();

    /**
     * Calculate the total of all sides
     * 
     * Interesting Fact: You cannot use the nameo of class to use this function, but must use the interface name
     * 
     * @param polygon       The polygon where the information will be retrieved
     * @return              The total length of all the sides
     */
    public static int totalOfAllSides(RegularPolygon polygon) {
        return polygon.getNumberOfSides() * polygon.getLengthOfSides();
    }

    /**
     * Get the Interior Angle
     * 
     * @return The result as a <code>Double</code>
     */
    default double getInteriorAngle() {
        double degrees = Math.toRadians( getNumberOfSides() * Math.PI / getNumberOfSides() );
        return degrees;
    }
}

public class DefaultAndStaticInterfaces {

    public static class EquilaterialTriangle implements RegularPolygon {

        private int numberOfSides; 
        private int lengthOfSide;

        public EquilaterialTriangle(int numberOfSides, int lengthOfSide) {
            this.numberOfSides = 3;
            this.lengthOfSide = 12;
        }

        @Override
        public int getNumberOfSides() {
            return numberOfSides;
        }

        @Override
        public int getLengthOfSides() {
            return lengthOfSide;
        }

        @Override
        public String toString() {
            StringBuffer buffer = new StringBuffer("");
            buffer.append("Polygon has " + numberOfSides + " and each side is " + lengthOfSide);
            return buffer.toString();
        }
    }

    public static void main(String... args) {

        DefaultAndStaticInterfaces container = new DefaultAndStaticInterfaces();
        
        RegularPolygon triangle = new DefaultAndStaticInterfaces.EquilaterialTriangle(3, 12);
        System.out.println(triangle.toString());
        System.out.println("The length of all sides is " + RegularPolygon.totalOfAllSides(triangle));
        System.out.println("The degrees of the interior angle is " + triangle.getInteriorAngle());
    }
}
