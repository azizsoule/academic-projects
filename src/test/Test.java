package test;

//import com.github.royken.converter.FrenchNumberToWords;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Test {
    public static void main(String [] args){

//        System.out.println("24-12-1999 s'écrit: "+FrenchNumberToWords.convert(24)+" Décembre "+FrenchNumberToWords.convert(19999999));

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String dateStr = simpleDateFormat.format(new Date());
        System.out.println(dateStr);
    }
}


