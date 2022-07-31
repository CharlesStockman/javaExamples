/**
 * Using the and method of Predicate [hard!]. Now, the point of all of this is that filter takes a single
 * Predicate, not multiple Predicates. The goal of this problem is to make filtering more flexible by
 * making similar filtering code, but that accepts any number of Predicates instead of a single Predicate. 
 * 
 * To accomplish this, 
 * first make a method called pass that accepts any number of generically typed Predicates (recall how to 
 * use varargs with “...”), and returns a single Predicate that tests if the argument passes all of the input 
 * Predicates. 
 * 
 * Second, make a method called firstMatch that takes a Stream and any number of correspondingly-typed 
 * Predicates, and returns the first entry when test with the predicate is true. Your code will simply make the 
 * combined Predicate, then call code like that at the top of the page. 
 * 
 * For example, if an and predicaet is bei8ng used then the following would find the first word that both contains an “o” 
 * and has length greater than 5. <code>FunctionUtils.firstMatch(words.stream(), word -> word.contains("o"), 
 * word -> word.length() > 5);</code>
 * 
 * Assuming that you use varargs in your solutions, note that you will receive an odd-sounding warning (not error) 
 * about potential heap pollution. It is safe to ignore this error for now, but in the file IO
 * lecture we will briefly explain the warning and show how to suppress it with @SafeVarargs.
 */
import java.util.List;
import java.util.ArrayList;
import java.util.function.BiFunction;
import java.util.function.Predicate;
import java.util.stream.Stream;

import javax.management.openmbean.OpenMBeanParameterInfoSupport;

import java.util.Arrays;
import java.util.Optional;

/**
 * A class that associates a name with a predicate
 */
class NamedPredicate<T> {
    String name;
    Predicate<T> predicate;

    /**
     * Creates an instance of Named Predicate
     * @param name
     * @param predicates
     */
    NamedPredicate(final String name, final Predicate<T> predicate) {
        this.name = name;
        this.predicate = predicate;
    }

    /**
     * @return  The name associated with the predicate
     */
    public String getName() {
        return name;
    }

    /**
     * @return The predicate ( may contain that will perform the test on the object(s)
     */
    public Predicate<T> getPredicate() {
        return predicate;
    }

    @Override
    public String toString() {
        return String.format("Current NamedPredicate : Name = %s and Predicate = %s ", this.name, this.predicate.toString());
    }
}

/**
 * An enumeration that allow the user to know if the Predicate is using the And or the Or member function
 */
enum OPERATORS { 
    AND,
    OR;
}

public class FunctionalComposition {

    /**
     * Create a predicate using function composition ( and method )
     * 
     * @param predicates            A list of the conditions that will combined using the and method
     * @param opertaor              The operation can either be "and" or "or"
     * 
     * @IllegalArgumentException    The operator was not "and" or "or"
     * 
     * @return A predicate with all the conditions
     */
    @SafeVarargs
    public static <T> Predicate<T> passPredicate(String operator, final Predicate<T>... predicates) {

        Optional<OPERATORS> selectedOperator = Optional.of(OPERATORS.valueOf(operator.toUpperCase()));
        if ( selectedOperator.isPresent() == false ) {
            throw new IllegalArgumentException(String.format("The value of the operator is %s paramter should be either \"And\" or \"Or\" ", selectedOperator.toString()));
        }

        Predicate<T> returnPredicate = ( selectedOperator.get() == OPERATORS.AND ) ? (e) -> true : (e) -> false;

        for (Predicate predicate : predicates) {
            switch(selectedOperator.get()) {
                case AND:
                    returnPredicate = returnPredicate.and(predicate);
                    break;
                case OR:
                    returnPredicate = returnPredicate.or(predicate);
                    break;
            }
        }

        return returnPredicate;
    }

    /**
     * Find the first match that matches all the predicates in the function composition
     * 
     * @param chainedPredicate      A predicate with one or more conditions anded together ( Functional Composition)
     * @param words                 The list of words that will be tested with the chained predicate
     */
    public static <T> T firstMatch(final Stream<T> words, final Predicate<T> andPredicates) {
        final T result = words.filter(andPredicates).findFirst().orElse(null);
        return result;
    }

    /**
     * A routine to mutate the word list using the list modification lambda and then return the firstMatch and prints the results
     * 
     * @param listModification      The lambda function that mutates the words list
     * @param NamedPredicate        The instance that allow the user to assocaiate a name with a predicate
     * @param words                 The list of words that will be tested with the chained predicate
     * @param extraWord             The word that will added to the words using the listModification Lambda
     * 
     */
    public static void conveinanceFunction(BiFunction<List<String>,String,List<String>> listModification, NamedPredicate namePredicate, List<String> words, String extraWord) { 
        Stream<String> wordsStream = ( extraWord.length() != 0 ) ? listModification.apply(words, extraWord).stream() : words.stream();
        String result = FunctionalComposition.firstMatch(wordsStream, namePredicate.getPredicate());
        System.out.printf("For the input The firstAllMatch is using %s for the list %s and the result is %s\n", namePredicate.getName(), words.toString(), result);
    }
    public static void main(final String... args) {

        final List<String> words = new ArrayList<>( Arrays.asList("toaster", "cat", "looking"));

        final Predicate<String>  test1 = ( word ) -> word.contains("o"); 
        final Predicate<String>  test2 = ( word ) -> word.length() > 5;
        
        final NamedPredicate<String> andChainedPredicate = new NamedPredicate("andPredicate", FunctionalComposition.passPredicate(OPERATORS.AND.name(), test1, test2));
        final NamedPredicate<String> orChainedPredicate  = new NamedPredicate("orPredicate" , FunctionalComposition.passPredicate(OPERATORS.OR.name(), test1, test2));

        FunctionalComposition.conveinanceFunction( (inWords, word) -> { words.add(0,word) ; return words; }, andChainedPredicate, words, "");
        FunctionalComposition.conveinanceFunction( (inWords, word) -> { words.add(0,word) ; return words; }, andChainedPredicate, words, "I");
        FunctionalComposition.conveinanceFunction( (inWords, word) -> { words.add(0, word) ; return words; }, orChainedPredicate, words, "");
        FunctionalComposition.conveinanceFunction( (inWords, word) -> { words.add(0, word) ; return words; }, orChainedPredicate, words, "Charles");
    }
}