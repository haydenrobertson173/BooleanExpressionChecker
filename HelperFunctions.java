import java.util.*;

public class HelperFunctions {

    // Assumes we know paranthesis exist and they are valid
    public static String evaluateParanthesis(String booleanExpression) {
        
        boolean paranthRemain = true;
        while(paranthRemain) {
            // find all open paranthesis and count them
            int count = 0;
            for (int i = 0; i < booleanExpression.length(); ++i) {
                if (booleanExpression.charAt(i) == '(') {
                    ++count;
                }
            }

            if (count == 0) {
                paranthRemain = false;
                return booleanExpression;
            }
            
            //Grab last open paranthesis
            int openParIdx = -1;
            for (int j = 0; j < count; ++j) {
                openParIdx = booleanExpression.indexOf('(', (openParIdx + 1));
            }

            int closeParIdx = booleanExpression.indexOf(')', openParIdx);

            String paranthesizedExpress = booleanExpression.substring((openParIdx + 1), closeParIdx);
            // Call booleanResult
            boolean result = booleanResult(paranthesizedExpress); 
            char replacement = result ? '1' : '0';

            booleanExpression = booleanExpression.substring(0, openParIdx) + replacement + 
                booleanExpression.substring((closeParIdx + 1));
            
        } 
        return booleanExpression;
        
    }// End of evaluateParanthesis

        public static boolean booleanResult(String booleanExpression) {
        
        // Remove spaces using .replaceAll string method &
        booleanExpression = booleanExpression.replaceAll("\\s", "");

        //check for paranthesis
        boolean hasParanthesis = false;
        do {
            hasParanthesis = false;
            for (int i = 0; i < booleanExpression.length(); ++i) {
                if (booleanExpression.charAt(i) == '(') {
                    hasParanthesis = true;
                    // Added break statement for less loops
                    break;
                } 
            } 
            if (hasParanthesis) {
                booleanExpression = evaluateParanthesis(booleanExpression);
            }
            
        } while (hasParanthesis);
        
        // Split expression around +
        String[] splitExpress = booleanExpression.split("\\+");
        //initialize ending OR with zero (False);
        boolean expressionVal = false; 
        
        // Step through splitExpress[] of terms
        for (int i = 0; i < splitExpress.length; ++i) {
            // Initialize termVal to ON
            boolean termVal = true;
           
            for (int j = 0; j < splitExpress[i].length();) {
                char ch = splitExpress[i].charAt(j++); 

                // This assumes first char is always letter //
                boolean varVal = BooleanExpressionCheck.lookupVal(ch); 
                

                // Check for ' (NOT) 
                while (j < splitExpress[i].length() && splitExpress[i].charAt(j) == '\'' ) {
                    // flip NOT everytime ' is found
                    varVal = !varVal;
                    j++;
                }
                termVal &= varVal;
            }
            expressionVal = expressionVal || termVal;
        } 

        return expressionVal;

    }// End of booleanResult()

} // End of Helper Functions Class
