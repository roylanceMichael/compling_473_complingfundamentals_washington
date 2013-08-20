import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;

public class skeleton3_utf8
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
        final static String V1 = "เแโใไ";
        final static String C1 = "กขฃคฅฆงจฉชซฌญฎฏฐฑฒณดตถทธนบปผฝพฟภมยรฤลฦวศษสหฬอฮ";
        final static String C2 = "รลวนม";
        final static String V2 = "ิีึืุูั็";
        final static String T  =  "\u0E48\u0E49\u0E4A\u0E4B";
        final static String V3 = "าอยว";
        final static String C3 = "งนมดบกยว";

        static public String process(String s_in)
        {
            // some kind of Finite State Transducer goes here

            return s_in;
        }
    }

}
