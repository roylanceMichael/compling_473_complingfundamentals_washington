import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;

public class skeleton3
{
    public static void main(String[] args) throws IOException 
    {
        System.out.println("<html><meta http-equiv='Content-Type' content='text/html; charset=UTF-8' /><body>");

        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String line;
        
        while ((line = reader.readLine()) != null) {
           System.out.print(FST.process(line));
           System.out.println("<br/>");
        }
        
        System.out.println("</body></html>");
    }

    static class FST
    {
        final static String V1 = "\u0E40\u0E41\u0E42\u0E43\u0E44";
        final static String C1 = "\u0E01\u0E02\u0E03\u0E04\u0E05\u0E06\u0E07\u0E08\u0E09\u0E0A\u0E0B\u0E0C\u0E0D\u0E0E\u0E0F"
                + "\u0E10\u0E11\u0E12\u0E13\u0E14\u0E15\u0E16\u0E17\u0E18\u0E19\u0E1A\u0E1B\u0E1C\u0E1D\u0E1E\u0E1F"
                + "\u0E20\u0E21\u0E22\u0E23\u0E24\u0E25\u0E26\u0E27\u0E28\u0E29\u0E2A\u0E2B\u0E2C\u0E2D\u0E2E";
        final static String C2 = "\u0E23\u0E25\u0E27\u0E19\u0E21";
        final static String V2 = "\u0E34\u0E35\u0E36\u0E37\u0E38\u0E39\u0E31\u0E47";
        final static String T  = "\u0E48\u0E49\u0E4A\u0E4B";
        final static String V3 = "\u0E32\u0E2D\u0E22\u0E27";
        final static String C3 = "\u0E07\u0E19\u0E21\u0E14\u0E1A\u0E01\u0E22\u0E27";

        static public String process(String s_in)
        {
            // some kind of Finite State Transducer goes here

            return s_in;
        }
    }

}
