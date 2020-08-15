package test;

import com.github.royken.converter.FrenchNumberToWords;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;

public class Test {
    public static void main(String [] args){

        ProcessBuilder builder = new ProcessBuilder();
        builder.command("evince","src/extrait.pdf");
        try {
            builder.start();
        } catch (IOException e) {
            e.printStackTrace();
        }

        //Date date = new Date(2020, 11,7);

        //String [] month = {"janvier", "février", "mars", "avril", "mai", "juin", "juillet", "aout", "septembre", "octobre", "novembre", "décembre"};



        /*System.out.println(
                "Le "+FrenchNumberToWords.convert(date.getDate())+" "+ month[date.getMonth()]+ " " + FrenchNumberToWords.convert(date.getYear())
        );*/

        //SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        //String dateStr = simpleDateFormat.format(new Date());
        //System.out.println(dateStr);
    }
}


