package com.RapidPharma;

import java.util.*;
import java.mail.*;
import javax.mail.internet.*;
import javax.activation.*;
import javax.swing.*;
import java.io.FileNotFoundException;
import java.io.File;
import java.sql.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import com.RapidPharma.lista_aitima;

public class xristis{

  public static void Verifyxristis(String onoma_xristi,String Kodikos) {
        try {
            Connection myConn = DriverManager.getConnection("jdbc:mysql://localhost:3306/RapidPharma", "root", "root");
            Statement myStmt = myConn.createStatement();
            ResultSet myRs = myStmt.executeQuery("select id,email,password from xristis");
            String identify_1 = null;
            String identify_2 = null;

            if (onoma_xristi.equals(myRs.getString("name"))) {
                ResultSet myRs_1 = myStmt.executeQuery("select id from xristis where onoma_xristi="+onoma_xristi+);
                identify_1 = myRs_1.getString("id");
            } else
                System.out.println("Σφάλμα στην ταυτοποίηση του ονόματος");

            if (Kodikos.equals(myRs.getString("password"))) {
                ResultSet myRs_2 = myStmt.executeQuery("select id from xristis where password="+Kodikos+);
                identify_2 = myRs_2.getString("id");
            } else
                System.out.println("Σφάλμα στην ταυτοποίηση του κωδικού");
            while (identify_1!=null&&identify_2!=null) {
                if (identify_1.equals(identify_2))
                    System.out.println("Σφάλμα στην ταυτοποίηση του χρήστη");
                else
                    System.out.println("Επιτυχής ταυτοποίηση του χρήστη");

            }
        }
        catch (Exception exc) {
            exc.printStackTrace();
        }
    }

   public static boolean checkUserStatus(int Id_xristi){
     Connection myConn = DriverManager.getConnection("jdbc:mysql://localhost:3306/RapidPharma", "root", "root");
     Statement myStmt = myConn.createStatement();
     ResultSet myRs = myStmt.executeQuery("select id_dromologiti from dromologiti where id_dromologiti = " + Id_xristi + ";");
     if(myRs != null){
       return true;
     }
     else{
       return false;
     }
   }

   public static void insertcert() throws FileNotFoundException{ // eisagei ta pistopoiitika tou

        File text = new File("C:/certificate.txt");
        Scanner scanner = new Scanner(text);
        System.out.println("Εισαγωγή των πιστοποιητικών σας:");
        int lineNumber = 1;
        while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                System.out.println("line " + lineNumber + " :" + line);
                lineNumber++;
            }
    }

    public static void insertchar(){
        Scanner scanner = new Scanner(System.in);
        String email_xristi;
        String Kodikos;
        String onoma_xristi;

        System.out.println("Εισάγετε την οδό σας:");
        String odos = scanner.next();
        System.out.println("Εισάγετε τον αριθμό της οδού σας:");
        int arithmos = scanner.nextInt();
        System.out.println("Εισάγετε την πόλη σας:");
        String poli = scanner.next();
        System.out.println("Εισάγετε το νομό σας:");
        String nomos = scanner.next();
        System.out.println("Εισάγετε τον ταχυδρομικό σας κώδικα:");
        int tk = scanner.nextInt();

            while (true) {
                System.out.println("Εισάγετε το email σας:");
                email_xristi = scanner.next().trim();
                if (email_xristi.matches("[A-Za-z].*?@gmail\\.com"))
                    break;
                System.out.println("Σφάλμα στην συμπλήρωση του email");
            }
            while (true) {
                System.out.println("Εισάγετε το όνομα σας:");
                onoma_xristi = scanner.nextLine().trim();
                if (onoma_xristi.matches("^([A-Z].[a-z]).(?=.\\S+$)"))
                    break;
                System.out.println("Το όνομα δεν έχει την κατάλληλη μορφή");
            }
            while (true) {
                System.out.println("Εισάγετε κωδικό χρήστη:");
                Kodikos = scanner.next();
                if (isPasswordValid(Kodikos))
                    break;
                System.out.println("Ο κωδικός δεν έχει την κατάλληλη μορφή");
            }
            Verifyxristis(onoma_xristi,Kodikos);
    }

	public static boolean isPasswordValid(String password) {


        String regExpn =
                "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\S+$).{8,}$";

        CharSequence inputStr = password;


        Pattern pattern = Pattern.compile(regExpn,Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(inputStr);

        if(matcher.matches())
            return true;
        else
            return false;
    }

    public static void Proboli_aitimatos(String id_xristi){

        try {
            public static ObservableList<Aitima> aitima;
            aitima = lista_aitima.getaitimata(id_xristi);
            int i;
           for(i=0;i<aitima.length();i++) {
               if (aitima[i].status == 1) {
                   System.out.println("*****Δρομολογημένα*****");
               }
               if (aitima[i].status == 2) {
                   System.out.println("*****Ανοιχτά*****");
               }
               if(aitima[i].status==3) {
                   System.out.println("*****Ολοκληρωμένα*****");
               }
               if(aitima[i].status==4) {
                   System.out.println("*****Προς Δρομολόγηση*****");
               }
           }
        }
        catch (Exception exc) {
            exc.printStackTrace();
        }
    }

}
