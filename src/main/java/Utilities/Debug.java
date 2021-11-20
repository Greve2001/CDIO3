package Utilities;

public class Debug {

    private static final Debug instance = new Debug();

    private Debug(){}

    public static Debug getInstance(){
        return instance;
    }

    boolean showPrints = true;

    // Handles prints, will not print when boolean is false.
    // Should only be used for debugging
    public void print(String str){
        if (showPrints){
            System.out.println(str);
        }
    }
}
