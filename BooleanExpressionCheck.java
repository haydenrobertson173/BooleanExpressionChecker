import java.util.*;

public class BooleanExpressionCheck {
    // Global arrays for holding Hi / Lo values of each input
    private static char[] inputVars;
    private static boolean[] inputVarsHiLo;

    // Class static methods
    /******************************************************************************************/

    public static boolean lookupVal(char c) {
        // If char is 1 or 0, return T or F
        if (c == '1') {
            return true;
        } else if (c == '0') {
            return false;
        }
        // Look for matching c in inputs array, if found, return boolean from parallel array
        for (int i = 0; i < inputVars.length; ++i) {
            if (Character.toUpperCase(c) == inputVars[i]) {
                return inputVarsHiLo[i];
            }
        }
        // Anything else, throw exception
        throw new IllegalArgumentException("Unknown Variable: " + c);

    }// End of lookupVal

    /******************************************************************************************/

    public static boolean booleanExpressionValidity(String booleanExpression, int numInputs) {
            char maxVar = (char) ('A' + (numInputs - 1));
            boolean requireVar = true; 
            boolean validExp = true;

            // Check for equal number or open/close paranthesis and in the right order
            int openParCt = 0;
            for(int i = 0; i < booleanExpression.length(); ++i) {

                if (booleanExpression.charAt(i) == '(') {
                    openParCt++;
                } else if (booleanExpression.charAt(i) == ')') {
                    if (openParCt > 0) {
                        openParCt--;
                        
                    } else {
                        validExp = false;
                        break;                
                    }
                }
            }
        
            if (openParCt != 0) {
                validExp = false;
            }

            // return false if it doesnt pass first check
            if(!validExp) {
                return validExp;
            }                          

            // If it passes paranthesis check, check for valid characters
            for (int i = 0; i < booleanExpression.length(); ++i) {
                char ch = Character.toUpperCase(booleanExpression.charAt(i));

                if (ch == '+') {
                    if (requireVar) {
                        validExp = false;
                        break;
                    }
                    requireVar = true;
                    continue;
                    // Setting this to true will stop back to back +'s
                } else if (ch == '\'') { 
                    if (requireVar) {
                        validExp = false;
                        break;
                    }
                    continue;
                } else if (ch == '(') {
                    requireVar = true;
                    continue;
                } else if (ch == ')') {
                    continue;
                } else if (ch < 'A' || ch > maxVar) {
                    validExp = false;
                    break;
                }
                // requireVar is true when the expression starts, after '+', and after '('.
                requireVar = false;
            }

            return validExp;

    } // End of booleanExpressionValidity()

//***********************************************************************************************/

    public static String getBooleanExpression(Scanner scnr, int numInputs) {
        //get boolean exp from input
        boolean isValidExp = true;
        String thisBooleanExpression;
        do {
            System.out.println("Enter boolean expression. \nUse ' for NOT.");
            System.out.print("X = ");
            thisBooleanExpression = scnr.nextLine();
            System.out.println();
            //Remove spaces
            thisBooleanExpression = thisBooleanExpression.replaceAll("\\s", "");
            
            //call booleanExpressionValidity check method
            isValidExp = booleanExpressionValidity(thisBooleanExpression, numInputs);

            //if not valid, print error and get input again
            if (!isValidExp) {
                System.out.println("Your expression is not valid. Input variables are A - " + ( (char)('A' + (numInputs - 1)) )+ ".");
                System.out.println("Use SOP only, ' for NOT, and + for OR.");
                System.out.println("Adjacent variables will be interpretted as AND.\n");
            }
        } while (!isValidExp);
        return thisBooleanExpression;
    } // End of getBooleanExpression

    /****************************************************************************************/

    public static void getAutoResults(String booleanExpression) {
        // Get number of inputs based on Array length created with numInputs 
        int thisNumInputs = inputVars.length;
        boolean result;
        // Generate numbers from 0  to (2^numInputs)
        for (int i = 0; i < Math.pow(2, thisNumInputs); ++i) {

            String varValString = Integer.toBinaryString(i);

            // append 0's to binary string to acct for all input values
            while (varValString.length() < thisNumInputs) {
                varValString = "0" + varValString;
            }
            
            // Fill input variable-value array based on 0 or 1's
            for (int j = 0; j < varValString.length(); ++j) {
                if (varValString.charAt(j) == '1') {
                    inputVarsHiLo[j] = true;
                } else if (varValString.charAt(j) == '0') {
                    inputVarsHiLo[j] = false;
                }
            }

            result = HelperFunctions.booleanResult(booleanExpression);
            System.out.println(i + ": " + (result ? "ON" : "OFF"));

        }
    
    } // End of getAutoResults()

    /**********************************************************************************************/

    public static void getManualResults(Scanner scnr, String booleanExpression) {
        int thisNumInputs = inputVars.length;
        boolean result;

        System.out.println("Input boolean value for each input (1 or 0).");
        for (int i = 0; i < thisNumInputs;) {
            int userBoolVal;
            System.out.print("Input " + inputVars[i] + ": ");
            userBoolVal = scnr.nextInt();
            System.out.println();

            if (userBoolVal == 1) {
                inputVarsHiLo[i] = true;
                ++i;
            } else if (userBoolVal == 0) {
                inputVarsHiLo[i] = false;
                ++i;
            } else {
                System.out.println("Not valid.");
                continue;
            }
        }

        result = HelperFunctions.booleanResult(booleanExpression);
        System.out.println((result ? "ON" : "OFF"));
    } // End of getManualResults()

    /**********************************************************************************************/
    /**********************************************************************************************/

    // Class main
    public static void main(String[] args) {
        Scanner scnr = new Scanner(System.in);
        int numInputs;
        String boolExpression;
        String userInput = "";

        //input num inputs
        System.out.println("How many inputs?");
        numInputs = scnr.nextInt(); 
        scnr.nextLine();
        
        // Create parallel arrays for input and matching ON / OFF
        inputVars = new char[numInputs];
        inputVarsHiLo = new boolean[numInputs];

        // Fill inputVars array with A [char j] + numInputs
        char j = 'A';
        for (int i = 0; i < numInputs; ++i, ++j) {
            inputVars[i] = j;
        }
        // Loop as long as user agrees
        do {
            // Input boolean expression
            boolExpression = getBooleanExpression(scnr, numInputs);

            System.out.println("auto or manual?");
            String userInputSelect = scnr.next();
            scnr.nextLine();
            System.out.println();

            if ("auto".equalsIgnoreCase(userInputSelect)) {
                getAutoResults(boolExpression);

            } else if ("manual".equalsIgnoreCase(userInputSelect)) {
                getManualResults(scnr, boolExpression);
               
            }

            // Continue to input new expression, q to exit
            System.out.println();
            System.out.print("Continue? ");
            System.out.println("Press Y to continue, Q to quit.");
            userInput = scnr.next();
            scnr.nextLine();
           
        } while (!("Q".equalsIgnoreCase(userInput))); 

    }// End main

} // End of class



 