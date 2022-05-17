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
import java.util.ArrayList;

public class Dromologio {


private static ObservableList<Dromologio> dromologia;
private static Statement statement;

  public static void ValidateNewDate(Date date){
   //Scanner scanner = new Scanner(System.in);
   if((date.month < getCurrentMonth() || date.year < getCurrentYear()) || (date.day < getCurrentDay() && date.month = getCurrentMonth()))
   System.out.println("H ημερομηνία νέας δρομολόγησης που επιλέξατε δεν είναι σωστή");

}

  public static void updateRouteDate(Date newRoute_date, int id_dromologio){
      try{
        statement = DataConnection.getConnection("jdbc:mysql://localhost:3306/RapidPharma", "root", "root").createStatement();
        ResultSet rs = statement.executeQuery("Update Dromologio Set route_date = " + newRoute_date + " WHERE ID = "+ id_dromologio ";");

      catch(Exception e){
        e.printStackTrace();
      }
    }
  }

  public static void updateRouteList(int[] id_ait){
        for(i=0; i< id_ait.length(); i++){
          try{
            statement = DataConnection.getConnection("jdbc:mysql://localhost:3306/RapidPharma", "root", "root").createStatement();
            ResultSet rs = statement.executeQuery("Update Aitima Set route_id = 0 WHERE id = "+ id_ait[i] +";");

          catch(Exception e){
            e.printStackTrace();
          }
        }
        try{
          statement = DataConnection.getConnection("jdbc:mysql://localhost:3306/RapidPharma", "root", "root").createStatement();
          ResultSet rs = statement.executeQuery("Update Aitima Set route_id = 0 WHERE id = "+ id_ait[i] +";");

        catch(Exception e){
          e.printStackTrace();
        }
      }
    }
  }

  public static void calculateRoute(int dromologiti){
    String onoma;
    ArrayList<String> date = new ArrayList<String>();
    ArrayList<String> sumOfpaletes = new ArrayList<String>();
    try{
      statement = DataConnection.getConnection("jdbc:mysql://localhost:3306/RapidPharma", "root", "root").createStatement();
      ResultSet rs = statement.executeQuery("Select imerominia_dromologiou from Aitima where id_dromologiti =" + dromologiti + ";");
      if (rs != null){
          while (rs.next()) {
            date.add(rs.getString(0));
          }
}
    catch(Exception e){
      e.printStackTrace();
    }
  }
  try{
    statement = DataConnection.getConnection("jdbc:mysql://localhost:3306/RapidPharma", "root", "root").createStatement();
    ResultSet rs = statement.executeQuery("Select onoma_dromologhth  from Dromologhths where id_dromologiti =" +id_dromologiti + ";");
    if (rs != null){
        while (rs.next()) {
     onoma = rs.getString(0);
  }
}
  catch(Exception e){
    e.printStackTrace();
  }
}
  for(String hmeromhnia : date){
    int i =0 ;
  try{
    statement = DataConnection.getConnection("jdbc:mysql://localhost:3306/RapidPharma", "root", "root").createStatement();
    ResultSet rs = statement.executeQuery("Select sum(sunolo_paletwn) from Aitima where imerominia_dromologiou =" + hmeromhnia + " and in_route = false and apo =" + onoma + " or pros = " + onoma + ";");
    if (rs != null){
        while (rs.next()) {
          sumOfpaletes.add(rs.getInt(0));
        }
  }
  catch(Exception e){
    e.printStackTrace();
  }
  }
  try{
    statement = DataConnection.getConnection("jdbc:mysql://localhost:3306/RapidPharma", "root", "root").createStatement();
    ResultSet rs = statement.executeQuery("INSERT INTO Dromologio(hmeromhnia,dromologitis,ogkos_fortiou) VALUES ('"+hmeromhnia+"','"+ onoma +"','" + sumOfpaletes[i] + "')");
    i++;
  catch(Exception e){
    e.printStackTrace();
    }
  }
}
}

//get αιτηματα συγκεκριμενου δρομολογίου
public static ObservableList<Aitima> getAitimatabyRoute(int id_dromologiou){
        aitimata = FXCollections.observableArrayList(); // einai Ui stoixeio (h lista pou vlepei o xrhsths)
        private static Statement statement;
        try{
            statement = DataConnection.getConnection("jdbc:mysql://localhost:3306/RapidPharma", "root", "root").createStatement();
            ResultSet rs = statement.executeQuery("SELECT * FROM aitimata WHERE id_dromologiou = " + id_dromologiou +" order by ID desc;");

            if(rs != null)
                while (rs.next()) {
                    Aitima aitima = new Aitima();
                    aitima.setid(rs.getInt(1));
                    aitima.setpallets(rs.getInt(2));
                    aitima.set_to(rs.getString(3));
                    aitima.set_from(rs.getString(4));
                    aitima.set_date(rs.getString(5));
                    aitima.set_type(rs.getString(6));
                    aitima.add(aitimata);
                }
        }

        catch(Exception e){
            e.printStackTrace();
        }
        return aitimata;
    }

//Επιστρεφει τα δρομολογια του δρομολογητη
public static ObservableList<Dromologio> getRoute(int id_dromologiti){

  private static Statement statement;
  try{
      statement = DataConnection.getConnection("jdbc:mysql://localhost:3306/RapidPharma", "root", "root").createStatement();
      ResultSet rs = statement.executeQuery("SELECT * FROM Dromologio WHERE dromologitis = " + id_dromologiti +" order by ID desc;");

      if(rs != null)
          while (rs.next()) {
              Dromologio dromologio = new Dromologio();
              dromologio.setid(rs.getInt(1));
              dromologio.setpallets(rs.getInt(2));
              dromologio.set_date(rs.getString(5));
              dromologio.set_router(rs.getString(6));
              dromologio.add(dromologia);
          }
  }

  catch(Exception e){
      e.printStackTrace();
  }
  return dromologia;
}

}
}
