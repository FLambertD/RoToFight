package fr.umlv.rotofight.Read;

import java.util.Arrays;

public class Command {
	/**
	 * Private constructor of Command, should not be used.
	 */
	private Command() {
		throw new RuntimeException("Cannot instantiate the Command class.");
	}
	/**
	 * Searches for the option "--level" in the String array given.
	 * @param args 
	 * 		array of args to search in.
	 * @return 
	 * 		the index of the level's path in args, or -1 if not found
	 */
    private static int optionLvlName(String args[]){
        if (args.length<2){
            return -1;
        }
        for (int i = 0; i < args.length-1; i++) {
            if (args[i].equals("--level")){
                return i+1;
            }
        }
        return -1;
    }
    /**
     * returns a level's path to load.
     * @param args
     * 		array of args to search in.
     * @return
     * 		the path to the given file in args (after option "--level"), or the default level
     */
    public static String lvlName(String args[]){
        String test;
        int i = optionLvlName(args);
        if (i ==-1)
            test = "src/default-level.txt";
        else
            test = "src/" + args[i];
        return test;
    }
    
    /**
     * Searches for the option "--level" in the String array given.
     * @param args
     * 		array of args to search in.
     * @return
     * 		the index of the swords count in args, or -1 if not found.
     */

    private static int optionSwordValue(String args[]){
        if (args.length<2){
            return -1;
        }
        for (int i = 0; i < args.length-1; i++) {
            if (args[i].equals("--swords")){
                return i+1;
            }
        }
        return -1;
    }
    /**
     * returns the maximum amount of swords that each players can throw.
     * @param args
     * 		array of args to search in.
     * @return
     * 		the maximum amount of swords given in args (after option "--swords"), or the default value 20.
     */
    public static int swordValue(String args[]){
        int test;
        int i = optionSwordValue(args);
        if (i ==-1)
            test = 20;
        else {
            try {
                test = Integer.parseInt(args[i]);
            }catch (NumberFormatException error){
                System.out.println("Error for sword value argument, Default value 20 has been set");
                test = 20;
            }
        }
        return test;
    }
    /**
     * Searches for the option "--gravity" in the String array given.
     * @param args
     * 		array of args to search in.
     * @return
     * 		the index of the gravity type in args, or -1 if not found.
     */

    private static int optionGravity(String args[]){
        if (args.length<2){
            return -1;
        }
        for (int i = 0; i < args.length-1; i++) {
            if (args[i].equals("--gravity")){
                return i+1;
            }
        }
        return -1;
    }
    /**
     * returns the gravity value chosen.
     * @param args
     * 		array of args to search in.
     * @return
     * 		the gravity value found in args (after option "--gravity"), or the default value 2.
     */
    public static int gravityValue(String args[]){
        int test;
        int i = optionGravity(args);
        if (i ==-1)
            test = 2;
        else {
            try {
                test = Integer.parseInt(args[i]);
                if (!Arrays.asList(1,2,3,4,360).contains(test))
                    test = 2;
            }catch (NumberFormatException error){
                System.out.println("Error for gravity mode value argument, Default value 2 has been set");
                test = 2;
            }
        }
        return test;
    }
    /**
     * Searches for the option "--gravity-phase" in the String array given.
     * @param args
     * 		array of args to search in.
     * @return
     * 		the index of the gravity phase in args, or -1 if not found.
     */
    private static int optionGravityPhase(String args[]){
        if (args.length<2){
            return -1;
        }
        for (int i = 0; i < args.length-1; i++) {
            if (args[i].equals("--gravity-phase")){
                return i+1;
            }
        }
        return -1;
    }
    /**
     * returns the gravity phase value chosen.
     * @param args
     * 		array of args to search in.
     * @return
     * 		the gravity phase value found in args (after option "--gravity-phase"), or the default value 10000.
     */
    public static int gravityPhaseValue(String args[]){
        int test;
        int i = optionGravityPhase(args);
        if (i ==-1)
            test = 10000;
        else {
            try {
                test = Integer.parseInt(args[i]);
            }catch (NumberFormatException error){
                System.out.println("Error for gravity phase value argument, Default value 10 000 has been set");
                test = 10000;
            }
        }
        return test;
    }
    
}
