/**
 * Travel Directions Reduction. 
 * 
 * Given an array of characters representing the four directions, return a simplified array that gets you to the same place
 */

package InterviewProblems.CompassDirectionReduction;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class CompassDirectionReduction {

    // Did not use record since it immutable.  I prefer to LOMBOK since it s more adaptable
    // public record Counts(Integer northCount, Integer southCount, Integer eastCount, Integer westCount) {};

    public static void main(String[] args) {

        // Assumption -- All the Characters are capitalized and the only characters are N,S,W,E
        // Threw an exception since ignoring the assumption could lead to incorrect counts

        char[] originalDirections = new char[]{ 'N', 'N', 'E', 'W', 'S', 'W', 'N', 'N', 'E', 'E', 'E'};

        // Choose a map since I want to provide a direction to make it more readable.  for keys strings are good for the immutability
        final String NorthOrSouth="northOrSouth";
        final String EastOrWest="eastOrWest";

        Map<String, Integer> counts = new HashMap<>();

        // Test #2  -- Did not use a stream
        //    1. Stream do not work directly with Character Streams
        //    2. It would be less readable since I would have two map ( one returns Counts and the other would return the output
        //    3. Separation of Concerns

        counts.putIfAbsent(NorthOrSouth, 0);
        counts.putIfAbsent(EastOrWest, 0);

        Integer direction;
        for ( Character character : originalDirections) {


            switch ( character ) {
                case 'N' : direction = 1;  counts.put( NorthOrSouth , counts.get(NorthOrSouth) + direction); break;
                case 'S' : direction =-1;  counts.put( NorthOrSouth, counts.get(NorthOrSouth) + direction);  break;
                case 'E' : direction = 1;  counts.put( EastOrWest, counts.get(EastOrWest) + direction);      break;
                case 'W' : direction = -1; counts.put( EastOrWest, counts.get(EastOrWest) + direction);      break;

                default:
                    throw new IllegalArgumentException(
                            String.format("Error input %s contains the invalid character %c",
                                    Arrays.toString(originalDirections), character));
            }
        }

        char[] shortenString = new char[ counts.get(NorthOrSouth) + counts.get(EastOrWest) ];
        Arrays.fill(shortenString, 0, counts.get(NorthOrSouth) ,  ( counts.get(NorthOrSouth) > 0 ) ? 'N' : 'S');
        Arrays.fill(shortenString, counts.get(NorthOrSouth) , shortenString.length , ( counts.get(EastOrWest) > 0 ) ? 'E' : 'W');

    }
}
