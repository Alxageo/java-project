package com.RapidPharma;

import java.util.*;
import java.mail.*;
import javax.mail.internet.*;
import javax.activation.*;
import javax.swing.*;

import java.sql.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.sql.*;


public class Aitima{

//fields

private static ObservableList<Aitima> aitimata;

public static void sendemail(int status,String id_aitimatos) {
           try{
               Connection myConn = DriverManager.getConnection("jdbc:mysql://localhost:3306/RapidPharma", "root", "root");
               Statement myStmt = myConn.createStatement();
               ResultSet myRs = myStmt.executeQuery("select email,name from dromologitis inner join Αitima on id_dromologiti=ID_dromologiti where id_ait=id_aitimatos");
               ResultSet myRs_1 = myStmt.executeQuery("select email,namefrom dimiourgos inner join Αitima on id_dimiourgos=ID_dromologiti where id_ait=id_aitimatos");

               String to = myRs_1.getString("email"); // email αποδέκτη
               String from = myRs.getString("email"); // email αποστολέα
               String host = myRs.getString("name"); // Όνομα αποστολέα

               Properties properties = System.getProperties();
               properties.setProperty("mail.smtp.host", host);
               Session session = Session.getDefaultInstance(properties);

               try {

                   if (status==1) {
                       MimeMessage message = new MimeMessage(session);
                       message.setFrom(new InternetAddress(from));
                       message.addRecipient(Message.RecipientType.TO, new InternetAddress(to));
                       message.setSubject("To αίτημα με αριθμό " + id_aitimatos + " τροποποιήθηκε");
                       message.setText("Το αιτημά σας είναι σε διαδικασία δρομολόγησης απο τον " + myRs.getString("name")
                               + " δρομολογητή μπορείτε να παρακολουθήσετε το αιτημά σας στην εφαρμογή μας, Ευχαριστούμε");
                       Transport.send(message);
                       System.out.println("Το μήνυμα στάλθηκε επιτυχώς....");
                   }
                   if (status==2) {
                       MimeMessage message = new MimeMessage(session);
                       message.setFrom(new InternetAddress(from));
                       message.addRecipient(Message.RecipientType.TO, new InternetAddress(to));
                       message.setSubject("To αίτημα με αριθμό " + id_aitimatos + " τροποποιήθηκε");
                       message.setText("Το αιτημά σας είναι σε διαδικασία διαχείρισης απο τον " + myRs.getString("name")
                               + " ο οποίος είναι ο νέος δρομολογητής σας μπορείτε να παρακολουθήσετε το αιτημά σας στην εφαρμογή μας, Ευχαριστούμε");
                       Transport.send(message);
                       System.out.println("Το μήνυμα στάλθηκε επιτυχώς....");
                   }
                   if (status==3) {
                       MimeMessage message = new MimeMessage(session);
                       message.setFrom(new InternetAddress(from));
                       message.addRecipient(Message.RecipientType.TO, new InternetAddress(to));
                       message.setSubject("To αίτημα με αριθμό " + id_aitimatos + " τροποποιήθηκε");
                       message.setText("Το αιτημά σας ολοκληρώθηκε απο τον " + myRs.getString("name") +
                               " δρομολογητή μπορείτε να παρακολουθήσετε το αιτημά σας στα ολοκληρωμένα αιτήματα στην εφαρμογή μας, Ευχαριστούμε");
                       Transport.send(message);
                       System.out.println("Το μήνυμα στάλθηκε επιτυχώς....");
                   }
                   if (status==4) {
                       MimeMessage message = new MimeMessage(session);
                       message.setFrom(new InternetAddress(from));
                       message.addRecipient(Message.RecipientType.TO, new InternetAddress(to));
                       message.setSubject("To αίτημα με αριθμό " + id_aitimatos + " τροποποιήθηκε");
                       message.setText("Το αιτημά σας έγινε αποδεκτό απο τον " + myRs.getString("name") +
                               " δρομολογητή χωρίς όμως να έχει δρομολογηθεί λόγω κάποιας έλλειψης μπορείτε να παρακολουθήσετε το αιτημά σας στην " +
                               "εφαρμογή μας, Ευχαριστούμε");
                       Transport.send(message);
                       System.out.println("Το μήνυμα στάλθηκε επιτυχώς....");
                   }
               } catch (MessagingException mex) {
                   mex.printStackTrace();
               }
           }
           catch (Exception exc) {
               exc.printStackTrace();
           }

private void updateButtonActionPerformed(java.awt.event.ActionEvent evt) {
        int row = tableAitimata.getSelectedRow();

        String aitimaId = tableAitimata.getModel().getValueAt(row, 0).toString();
        Date imerominia = (Date) tableAitimata.getModel().getValueAt(row, 1);
        String to = tableAitimata.getModel().getValueAt(row, 2).toString();
        String from = tableAitimata.getModel().getValueAt(row, 3).toString();
        String eidosMetaforas = tableAitimata.getModel().getValueAt(row, 4).toString();
        String proteraiotita = tableAitimata.getModel().getValueAt(row, 5).toString();
        String plithosPallets = tableAitimata.getModel().getValueAt(row, 6).toString();
        boolean prosTropopoiisi = (boolean) tableAitimata.getModel().getValueAt(row, 7);
        boolean prosAkirwsi = (boolean) tableAitimata.getModel().getValueAt(row, 8);

        aitimaUpdate aitimaupdate = new aitimaUpdate();
        aitimaupdate.setVisible(true);
        aitimaupdate.pack();
        aitimaupdate.setLocationRelativeTo(null);

        aitimaupdate.aitimaidField.setText(aitimaId);
        aitimaupdate.imerominiaField.setDate(imerominia);
        aitimaupdate.toField.setText(to);
        aitimaupdate.fromField.setText(from);
        aitimaupdate.eidosmetaforasField.setText(eidosMetaforas);
        aitimaupdate.proteraiotitaField.setText(proteraiotita);
        aitimaupdate.plithospalletsField.setText(plithosPallets);
        aitimaupdate.prostropopoiisiCheckBox.setSelected(prosTropopoiisi);
        aitimaupdate.prosakirwsiCheckBox.setSelected(prosAkirwsi);
    }

private void deleteAitimaButtonActionPerformed(java.awt.event.ActionEvent evt) {

        for (int row : tableAitimata.getSelectedRows()) {
            int aitimaID = Integer.parseInt(tableAitimata.getModel().getValueAt(row, 0).toString());

            try{
            PreparedStatement statement;
            ResultSetImpl rs;

            String query = "DELETE FROM `aitima` WHERE `aitimaid` = " +aitimaID;

            statement = (PreparedStatement) DataConnection.getConnection().prepareStatement(query);

            rs = (ResultSetImpl) statement.executeQuery();

            tableAitimata.setModel(DbUtils.resultSetToTableModel(rs));

            JOptionPane.showInternalMessageDialog(null, "Το αίτημα διαγράφηκε επιτυχώς!");
            filltable();
            }
            catch(SQLException ex){
                Logger.getLogger(tropopoiisiAitimatos.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

    }



    public void filltable()){

        try{
            Date fromDate = fromDateField.getDate();
            Date toDate = toDateField.getDate();

            PreparedStatement statement;
            ResultSetImpl rs;

            String query = "SELECT * FROM `aitima`"
                           + " (`fromdate` BETWEEN 'fromDate' AND 'toDate') OR \n"
                           + " (`todate` BETWEEN 'fromDate' AND 'toDate') OR \n"
                           + " (`fromdate` <= 'fromDate' AND `todate` >= 'toDate')";

            statement = (PreparedStatement) DataConnection.getConnection().prepareStatement(query);

            rs = (ResultSetImpl) statement.executeQuery();

            tableAitimata.setModel(DbUtils.resultSetToTableModel(rs));

        }
        catch(SQLException ex){
            Logger.getLogger(tropopoiisiAitimatos.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

private void tropopoiisiButtonActionPerformed(java.awt.event.ActionEvent evt) {
       try{
       PreparedStatement statement;
       ResultSetImpl rs;

       Date Imerominia = imerominiaField.getDate();
       String To = toField.getText();
       String From = fromField.getText();
       String EidosMetaforas = eidosmetaforasField.getText();
       String Proteraiotita = proteraiotitaField.getText();
       String PlithosPallets = plithospalletsField.getText();
       boolean ProsTropopoiisi = Boolean.getBoolean(prostropopoiisiCheckBox.getText());
       boolean ProsAkirwsi = Boolean.getBoolean(prosakirwsiCheckBox.getText());



       String query = "UPDATE `aitima` SET"
                      +" `imerominia` = ?,"
                      +" `apo` = ?,"
                      +" `pros` = ?,"
                      +" `eidos_metaforas` = ?,"
                      +" `proteraiotita` = ?,"
                      +" `prostropopoiisi` = ?,"
                      +" `prosakirwsi` = ?,";

       statement = (PreparedStatement) DataConnection.getConnection().prepareStatement(query);

           statement.setDate(1, (java.sql.Date) Imerominia);
           statement.setString(2, To);
           statement.setString(3, From);
           statement.setString(4, EidosMetaforas);
           statement.setString(5, Proteraiotita);
           statement.setString(6, PlithosPallets);
           statement.setBoolean(7, ProsTropopoiisi);
           statement.setBoolean(8, ProsAkirwsi);


           rs = (ResultSetImpl) statement.executeQuery();
           if(statement.executeUpdate()!=0){
               JOptionPane.showInternalMessageDialog(null, "Οι πληροφορίες αποθηκεύτηκαν σωστά!");
               this.dispose();
           }
           else{
               JOptionPane.showMessageDialog(null, "Σφάλμα! Η τροποποίηση απέτυχε! ");
           }
       }
       catch(SQLException ex){
           Logger.getLogger(aitimaUpdate.class.getName()).log(Level.SEVERE, null, ex);
       }


   }

   private void kataxwrisiAitimatosButtonActionPerformed(java.awt.event.ActionEvent evt) {
           PreparedStatement statement1;
           PreparedStatement statement2;

           String query1 = "INSERT INTO `fortio`(`plithosIpostirigmatwn`,`mikos`, platos`, `varos`, `plithos`) VALUES (?,?,?,?,?)";
           String query2 = "INSERT INTO `aitima`(`fortioid`,`imerominia`,`to`, from`, `eidosmetaforas`, `proteraiotita`) VALUES (SCOPE_IDENTITY(),?,?,?,?,?)";


           String imerominia = imerominiaField.getText();
           String apo = apoTopothesiaField.getText();
           String pros = prosTopothesiaField.getText();
           String eidosmetaforas = eidosMetaforasField.getSelectedItem().toString();
           String proteraiotita = proteraiotitaField.getSelectedItem().toString();

           int plithosIpostirigmatwn = Integer.valueOf(plithosIpostirigmatwnField.getText());
           int mikos = Integer.valueOf(mikosField.getText());
           int platos = Integer.valueOf(platosField.getText());
           int varos = Integer.valueOf(varosField.getText());
           int plithos = Integer.valueOf(plithosField.getText());

           try{

             statement1 = (PreparedStatement) DataConnection.getConnection().prepareStatement(query1);
             statement2 = (PreparedStatement) DataConnection.getConnection().prepareStatement(query2);


             statement1.setString(1, imerominia);
             statement1.setString(2, apo);
             statement1.setString(3, pros);
             statement1.setString(4, eidosmetaforas);
             statement1.setString(5, proteraiotita);


             statement1.setNull(5, java.sql.Types.NULL);


             statement2.setInt(1, plithosIpostirigmatwn);
             statement2.setInt(2, mikos);
             statement2.setInt(3, platos);
             statement2.setInt(4, varos);
             statement2.setInt(5, plithos);

             statement2.setNull(5, java.sql.Types.NULL);

             ResultSetImpl ts = (ResultSetImpl) statement1.executeQuery();
             ResultSetImpl rs = (ResultSetImpl) statement2.executeQuery();


               if(statement1.executeUpdate()!=0 && statement2.executeUpdate()!=0){
                   new ArxikiOthoni();
               }
               else{
                   JOptionPane.showMessageDialog(null, "Σφάλμα! Ελέγξτε τις πληροφορίες που εισάγατε!");
                   imerominiaField.setText("");
                   apoTopothesiaField.setText("");
                   prosTopothesiaField.setText("");
                   eidosMetaforasField.setSelectedItem(0);
                   proteraiotitaField.setSelectedItem(0);

                   plithosIpostirigmatwnField.setText("");
                   mikosField.setText("");
                   platosField.setText("");
                   varosField.setText("");
                   plithosField.setText("");
               }
           }

           catch(Exception e){
               e.printStackTrace();
           }
       }



       public void comboBoxFieldsFill(){
        PreparedStatement statement1;
        PreparedStatement statement2;
        PreparedStatement statement3;

        String query1 = "SELECT `eidos_metaforas` FROM `aitima`";
        String query2 = "SELECT `proteraiotita` FROM `aitima`";
        String query3 = "SELECT `tiposfortiou` FROM `fortio`";

          try{
             statement1 = (PreparedStatement) DataConnection.getConnection().prepareStatement(query1);
             statement2 = (PreparedStatement) DataConnection.getConnection().prepareStatement(query2);
             statement3 = (PreparedStatement) DataConnection.getConnection().prepareStatement(query3);

             ResultSetImpl rs = (ResultSetImpl) statement1.executeQuery();
             ResultSetImpl ts = (ResultSetImpl) statement2.executeQuery();
             ResultSetImpl ps = (ResultSetImpl) statement3.executeQuery();

              while(ts.next()){
              proteraiotitaField.addItem(rs.getString("proteraiotita"));
              }
              while(ps.next()){
              tiposFortiouField.addItem(rs.getString("tiposfortiou"));
              }
              while(rs.next()){
              eidosMetaforasField.addItem(rs.getString("eidosmetaforas"));
              }
          }
          catch(SQLException ex){
              Logger.getLogger(dimiourgiaAitima.class.getName()).log(Level.SEVERE, null, ex);
          }
      }


    public static void updateRouteDate(Date route_date, int id_aitimatos){
      private static Statement statement;
      try{
          statement = DataConnection.getConnection("jdbc:mysql://localhost:3306/RapidPharma", "root", "root").createStatement();
          ResultSet rs = statement.executeQuery("Update Aitimata Set imerominia_dromologiou = " + route_date + " WHERE ID = "+ id_aitimatos ";");

          catch(Exception e){
            e.printStackTrace();
        }
      }
    }

//Επιστρέφονται όλα τα αιτήματα που αφορουν το χρήστη
public static ObservableList<Aitima> getaitimata(String id_xristi) {
      aitimata = FXCollections.observableArrayList();

      try {
          myStmt = DataConnection.getConnection("jdbc:mysql://localhost:3306/RapidPharma", "root", "root").createStatement();
          ResultSet myRs = myStmt.executeQuery("select * from Aitima inner join xristis on ID_xristi=id_xristi where ID_xristi=" + id_xristi + ";");

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

//εδω αλλάζει η κατασταση του αιτηματος οταν το διαχειριζεται (π.χ δεν ειναι ανοιχτο πλεον αλλα δρομολογημενο)
public static void Update_status(String id_aitimatos,int status){
    try{
        Connection myConn = DriverManager.getConnection("jdbc:mysql://localhost:3306/RapidPharma", "root", "root");
        Statement myStmt = myConn.createStatement();
        ResultSet myRs = myStmt.executeQuery("Update Aitima set status="+status+" where id_aitimatos="+id_aitimatos);
        sendemail(status,id_aitimatos);
    }
    catch(Exception exc) {
        exc.printStackTrace();
    }

        // int[] chkd τα επιλεγμένα αιτήματα, string[] router η επι΄λογη του νέου δρομολογητη απο τη λιστα για την ανακατευθυνση
        public static void updateRouter(String kodikos, String id_aitimatos, String aitiologia ,int id_dromologiti){
          private static Statement statement;

          try {
              statement = DataConnection.getConnection("jdbc:mysql://localhost:3306/RapidPharma", "root", "root").createStatement();

              ResultSet myRs = statement.executeQuery("select * from Aitima inner join xristis a on id_xristi=a.id where a.id=" + id_dromologiti + ";");
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

                      int id = aitima_1.getID();
                  for(i=0; i<=chkd.length(); i++){
                    if ((aitima_1.setEidos_Metaforas == "ΑΠΟ ΦΑΡΜΑΚΑΠΟΘΗΚΗ" || aitima_1.setEidos_Metaforas == "ΠΡΟΣ ΚΑΤΑΣΤΗΜΑ" )  && id_aitimatos == id){
                      try {
                          statement = DataConnection.getConnection("jdbc:mysql://localhost:3306/RapidPharma", "root", "root").createStatement();

                          ResultSet Rs = statement.executeQuery("Update Aitimata Set apo = " + router + ", aitiologia = WHERE ID = "+ aitima_1.getID() ";");
                        }
                        catch (Exception e) {
                          e.printStackTrace();
                        }
                      }
                    else if (aitima_1.setEidos_Metaforas == "ΠΡΟΣ ΦΑΡΜΑΚΑΠΟΘΗΚΗ" || aitima_1.setEidos_Metaforas == "ΑΠΟ ΚΑΤΑΣΤΗΜΑ") && id_aitimatos == id) {
                      try {
                          statement = DataConnection.getConnection("jdbc:mysql://localhost:3306/RapidPharma", "root", "root").createStatement();

                          ResultSet myRs = statement.executeQuery("Update Aitimata Set pros = " + router + " WHERE ID = "+ aitima_1.getID() ";");

                        }
                        catch (Exception e) {
                          e.printStackTrace();
                        }
                    }
                  }
                }
            catch (Exception e) {
              e.printStackTrace();
            }
        }
  }
}
