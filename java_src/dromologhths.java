package com.RapidPharma;

import java.util.*;
import java.mail.*;
import javax.mail.internet.*;
import javax.activation.*;
import javax.swing.*;

import java.sql.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import com.RapidPharma.lista_aitima;

public class Dromologhths extends xristis{

  //Επιστρέφονται όλα τα αιτήματα που αφορουν το δρομολογητη
      public static ObservableList<Aitima> getaitimata(String ID_DROMOLOGITI) {
        private static Statement statement;
          aitimata = FXCollections.observableArrayList();

          try {
              statement = DataConnection.getConnection("jdbc:mysql://localhost:3306/RapidPharma", "root", "root").createStatement();

              ResultSet myRs = statement.executeQuery("select * from Aitima inner join xristis a on ID_dromologiti=a.id where a.id=" + ID_DROMOLOGITI + ";");

              if (myRs != null)
                  while (myRs.next()) {
                      Aitima aitima_1 = new Aitima();
                      aitima_1.setid(myRs.getInt(1));
                      aitima_1.setImerominia(myRs.getInt(2));
                      aitima_1.setApo_Topothesia(myRs.getString(3));
                      aitima_1.setPros_Topothesia(myRs.getString(4));
                      aitima_1.setEidos_Metaforas(myRs.getString(5));
                      aitima_1.setRequired_Europaletes(myRs.getInt(6));
                      aitima_1.setstatus(myRs.getInt(7));
                      aitimata.add(aitima_1);
                  }
          } catch (Exception e) {
              e.printStackTrace();
          }

          return aitimata;
      }

      public static void Diaxirisi_insert(String id_aitimatos,String id_dromologiti){
        Aitima aitima = new Aitima();
              try {
                  Connection myConn = DriverManager.getConnection("jdbc:mysql://localhost:3306/RapidPharma", "root", "root");
                  Statement myStmt = myConn.createStatement();
                  Scanner scanner = new Scanner(System.in);
                  System.out.println("Εισαγωγή ευροπαλετών:");
                  int europaletes = scanner.nextInt();
                  System.out.println("Εισαγωγή ημερομηνίας:");
                  String imerominia = scanner.nextLine();
                  ResultSet myRs = myStmt.executeQuery("select sunolo_paletwn from Aitima where id_aitimatos="+id_aitimatos);
                  anakateuthinsi_CheckBox = new javax.swing.JCheckBox();//checkbox που επιλέγει ο χρήστης ανακατατεύθυνση
                  boolean checked = anakateuthinsi_CheckBox.getState();

                  if (anakateuthinsi_CheckBox.getState()) {
                      System.out.println("Δώσε την περιγραφή του αιτήματος που ανακατευθύνεται:");
                      String Perigrafi_anakateuthinomenou = scanner.next();
                      if(Perigrafi_anakateuthinomenou!=null){ //εαν δηλαδή θέλω να ανακατευθύνω ένα τμήμα του αιτήματος

                          System.out.println("Εισαγωγή αιτιολογίας ανακατεύθυνσης:");//αυτό θα αντικατασταθεί απο το GUI
                          String Aitiologia = scanner.nextLine();
                          System.out.println("Εισαγωγή κωδικού νεου δρομολογητή:");
                          int allagi_kwdikou = scanner.nextInt();

                          ResultSet myRs_1 = myStmt.executeQuery("Update Aitima set perigrafh="+Perigrafi_anakateuthinomenou+" where id_aitimatos=" + id_aitimatos);
                          aitima.Update_status(id_aitimatos,1);
                          aitima.updateRouter(allagi_kwdikou,id_aitimatos,Aitiologia,id_dromologiti);


                          System.out.println("Δώσε το ID του νέου αιτήματος:");
                          String ID_new_Aitimatos = scanner.next();
                          System.out.println("Δώσε την περιγραφή του αιτήματος που δρομολογείται:");
                          String Perigrafi_dromologimenou = scanner.next();

                          ResultSet myRs_2 = myStmt.executeQuery("select * from Aitima where id_aitimatos=" + id_aitimatos);
                          String Apo = myRs_2.getString("apo");
                          String Pros = myRs_2.getString("pros");
                          String Eidos_metaforas = myRs_2.getString("eidos_metaforas");
                          Date Imerominia_dhmiourgias = myRs_2.getDate("imerominia_dhmiourgias");
                          Date Imerominia_dromologiou = myRs_1.getDate("imerominia_dromologiou");
                          int Sunolo_paletwn = myRs_2.getInt("sunolo_paletwn");
                          int Status = myRs_2.getInt("status");
                          Boolean Inroute = myRs_2.getBoolean("inroute");
                          int Route_id = myRs_2.getInt("route_id");
                          String Id_dimiourgou = myRs_2.getString("ID_dimiourgou");
                          String Id_dromologiti = myRs_2.getString("ID_dromologiti");

                          //Το κομμάτι του αιτήματος που θα δρομολογεί τώρα (αυτό που μένει και δεν ανακατευθύνθηκε)

                          //Εδω δημιουργουμε το νεο αιτημα το οποιο έχει τα ίδια πεδία με το προηγούμενο αίτημα εκτός απο τα perigrafh(εδώ ο δρομολογητής συμπληρώνει αυτά που δεν έχουν ανακατευθυνθεί) ,id_aitimatos(το οποίο είναι
                          // το νέο id που θέτει ο δρομολογητής για το κομμάτι του αιτήματος που δρομολογεί τώρα)

                          ResultSet myRs_3 = myStmt.executeQuery("Insert into Aitima set imerominia_dromologiou="+Imerominia_dromologiou+",id_aitimatos="+ID_new_Aitimatos+",perigrafh="+Perigrafi_dromologimenou+
                                  ",apo="+Apo+",pros="+Pros+",eidos_metaforas="+Eidos_metaforas+",imerominia_dhmiourgias="+Imerominia_dhmiourgias+",sunolo_paletwn="+Sunolo_paletwn+
                                  ",status="+Status+",aitiologia="+Aitiologia+",inroute="+Inroute+",route_id="+Route_id+",ID_dimiourgou="+Id_dimiourgou+",ID_dromologiti="+Id_dromologiti);

                          ResultSet myRs_4 = myStmt.executeQuery("Update Aitima set imerominia_dromologiou=" + imerominia + " where id_aitimatos=" + ID_new_Aitimatos);
                          aitima.Update_status(ID_new_Aitimatos,1);

                      }else {
                          System.out.println("Εισαγωγή αιτιολογίας ανακατεύθυνσης:");
                          String Aitiologia = scanner.nextLine();
                          System.out.println("Εισαγωγή κωδικού νεου δρομολογητή:");
                          int allagi_kwdikou = scanner.nextInt();
                          aitima.updateRouter(allagi_kwdikou,id_aitimatos,Aitiologia,id_dromologiti);
                      }


                  } else {
                      if (europaletes==myRs.getInt("sunolo_paletwn")) {
                          ResultSet myRs_1 = myStmt.executeQuery("Update Aitima set imerominia_dromologiou=" + imerominia + " where id_aitimatos=" + id_aitimatos);
                          aitima.Update_status(id_aitimatos,1);
                      }
                      if (europaletes<myRs.getInt("sunolo_paletwn")){

                          //Το κομμάτι του αιτήματος που δρομολογείται τωρα
                          System.out.println("Δώσε την περιγραφή του αιτήματος που δρομολογείται:");
                          String Perigrafi_old = scanner.next();
                          ResultSet myRs_2 = myStmt.executeQuery("Update Aitima set imerominia_dromologiou="+imerominia+", perigrafh="+Perigrafi_old+" where id_aitimatos=" + id_aitimatos);
                          aitima.Update_status(id_aitimatos,1);

                          System.out.println("Δώσε το ID του νέου αιτήματος:");
                          String ID_new_Aitimatos = scanner.next();
                          System.out.println("Δώσε την περιγραφή του νέου αιτήματος:");
                          String Perigrafi_new = scanner.next();

                          ResultSet myRs_1 = myStmt.executeQuery("select * from Aitima where id_aitimatos=" + id_aitimatos);
                          String Apo = myRs_1.getString("apo");
                          String Pros = myRs_1.getString("pros");
                          String Eidos_metaforas = myRs_1.getString("eidos_metaforas");
                          Date Imerominia_dhmiourgias = myRs_1.getDate("imerominia_dhmiourgias");
                          Date Imerominia_dromologiou = myRs_1.getDate("imerominia_dromologiou");
                          int Sunolo_paletwn = myRs_1.getInt("sunolo_paletwn");
                          int Status = myRs_1.getInt("status");
                          String Aitiologia = myRs_1.getString("aitiologia");
                          Boolean Inroute = myRs_1.getBoolean("inroute");
                          int Route_id = myRs_1.getInt("route_id");
                          String Id_dimiourgou = myRs_1.getString("ID_dimiourgou");
                          String Id_dromologiti = myRs_1.getString("ID_dromologiti");

                          //Το κομμάτι του αιτήματος που θα δρομολογηθεί αργότερα

                          //Εδω δημιουργουμε το νεο αιτημα το οποιο έχει τα ίδια πεδία με το προηγούμενο αίτημα εκτός απο τα perigrafh(εδώ ο δρομολογητής συμπληρώνει αυτά που δεν έχουν δρομολογηθεί) ,id_aitimatos(το οποίο είναι
                          // το νέο id που θέτει ο δρομολογητής για το κομμάτι του αιτήματος που δε δρομολογείται τώρα)

                          ResultSet myRs_3 = myStmt.executeQuery("Insert into Aitima set imerominia_dromologiou="+Imerominia_dromologiou+",id_aitimatos="+ID_new_Aitimatos+",perigrafh="+Perigrafi_new+
                                  ",apo="+Apo+",pros="+Pros+",eidos_metaforas="+Eidos_metaforas+",imerominia_dhmiourgias="+Imerominia_dhmiourgias+",sunolo_paletwn="+Sunolo_paletwn+
                                  ",status="+Status+",aitiologia="+Aitiologia+",inroute="+Inroute+",route_id="+Route_id+",ID_dimiourgou="+Id_dimiourgou+",ID_dromologiti="+Id_dromologiti);
                          aitima.Update_status(ID_new_Aitimatos,4);
                      }
                  }
              }
              catch(Exception exc) {
                  exc.printStackTrace();
              }
          }
}
