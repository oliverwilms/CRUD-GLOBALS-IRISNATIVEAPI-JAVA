/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import com.intersystems.jdbc.IRIS;
import com.intersystems.jdbc.IRISConnection;
import com.intersystems.jdbc.IRISIterator;
import java.sql.DriverManager;
import java.util.Arrays;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Scanner;


/**
 *
 * @author muniganesh
 */
public class IRISNative {
    
    protected static int superserverPort = 51773;
    protected static String namespace = "USER";
    protected static String username = "_SYSTEM";
    protected static String password = "SYS";
    public static void main(String[] args) {
        // TODO code application logic here
         Displayoption();
         
    
    }
    public static void Displayoption()
    {
           try {
            // open connection to InterSystems IRIS instance using connection string
            
            Scanner reader = new Scanner(System.in);
            System.out.print("Select option \n");
            System.out.print("1.VIEW \n");
            System.out.print("2.CREATE \n");
            System.out.print("3.UPDATE \n");
            System.out.print("4.DELETE \n");
            System.out.print("5.SEARCH \n");
            System.out.print("6.EXIT \n");
            Integer InpOption = reader.nextInt();
            
            switch (InpOption){
                case 1: System.out.print("Selected option "+InpOption+"\n");
                        GlobalView();
                        break;
                case 2: System.out.print("Selected option "+InpOption+"\n");
                        GlobalCreate();
                        break;
                case 3: System.out.print("Selected option "+InpOption+"\n");
                        GlobalUpdate();
                        break;
                case 4: System.out.print("Selected option "+InpOption+"\n");
                        GlobalDelete();
                        break;
                case 5: System.out.print("Selected option "+InpOption+"\n");
                        GlobalSearch();
                        break;
                case 6: System.out.print("Selected option "+InpOption+"\n");
                        return;
               default: System.out.print("Invalid Option "+InpOption+"\n");
                        return;
                        
            }
            Displayoption();
            

        } catch (Exception ex) {
            System.out.println(ex.getMessage());
            
        }
    }
public static void GlobalCreate()
    {
        
    try{
            Scanner reader = new Scanner(System.in);
            System.out.print("Enter a Global Name: ");
            String InpGlobalName = reader.nextLine();
            if ((InpGlobalName.isEmpty()==false)&&(InpGlobalName.contains("^")==false)) InpGlobalName="^"+InpGlobalName;
            if (InpGlobalName.isEmpty()==true) {
                System.out.println("EMPTY Value entered \n ");
                return;
            }
            System.out.println("Enter Subscript(s) as comma separated, for global " + InpGlobalName);
            String Inpsubscrpts = reader.nextLine();
            
            System.out.println("Enter global Value");
            String InpGlobalVal = reader.nextLine();
            
            Inpsubscrpts=Inpsubscrpts.replace("\"", "");
            String[] SubScrpts = Inpsubscrpts.split(",");
        try (IRISConnection conn = (IRISConnection) DriverManager.getConnection("jdbc:IRIS://localhost:"+superserverPort+"/"+namespace,username,password)) {
            IRIS iris = IRIS.createIRIS(conn);
            String retvaltstr=iris.getString(InpGlobalName, (Object[])SubScrpts);
            
            if (retvaltstr==null)
            {
                iris.set(InpGlobalVal, InpGlobalName, (Object[])SubScrpts);
                String outval=iris.getString(InpGlobalName,(Object[])SubScrpts);
                if (outval.equals(InpGlobalVal)) {
                    System.out.println("Global Created:");
                    String tmpdisstr="";
                    for (String strTemp : SubScrpts){
                        if (tmpdisstr.isEmpty()==false) tmpdisstr=tmpdisstr+"\",\""+strTemp;
                        else tmpdisstr=strTemp;
                    }
                    System.out.println(InpGlobalName+"(\""+tmpdisstr+"\")=\""+outval+"\"");
                }
            }
            else {
                System.out.println("Global Already Exists");
            }
            iris.close();
            conn.close();
        }
            
        }
    catch (Exception ex) {
            System.out.println("Exception -- "+ex.getMessage());
        }
    
    }
public static void GlobalUpdate()
    {
        
    try{
            Scanner reader = new Scanner(System.in);
            System.out.print("Enter a Global Name: ");
            String InpGlobalName = reader.nextLine();
            if ((InpGlobalName.isEmpty()==false)&&(InpGlobalName.contains("^")==false)) InpGlobalName="^"+InpGlobalName;
            if (InpGlobalName.isEmpty()==true) {
                System.out.println("EMPTY Value entered \n ");
                return;
            }
            System.out.println("Enter Subscript(s) as comma separated, for global " + InpGlobalName);
            String Inpsubscrpts = reader.nextLine();
            
            System.out.println("Enter global Value");
            String InpGlobalVal = reader.nextLine();
            
            Inpsubscrpts=Inpsubscrpts.replace("\"", "");
            String[] SubScrpts = Inpsubscrpts.split(",");
        try (IRISConnection conn = (IRISConnection) DriverManager.getConnection("jdbc:IRIS://localhost:"+superserverPort+"/"+namespace,username,password)) {
            IRIS iris = IRIS.createIRIS(conn);
            String retvaltstr=iris.getString(InpGlobalName, (Object[])SubScrpts);
            if (retvaltstr!=null)
            {
                iris.set(InpGlobalVal, InpGlobalName, (Object[])SubScrpts);
                String outval=iris.getString(InpGlobalName,(Object[])SubScrpts);
                if (outval.equals(InpGlobalVal)) {
                    System.out.println("Global Updated :");
                    String tmpdisstr="";
                    for (String strTemp : SubScrpts){
                        if (tmpdisstr.isEmpty()==false) tmpdisstr=tmpdisstr+"\",\""+strTemp;
                        else tmpdisstr=strTemp;
                    }
                    System.out.println(InpGlobalName+"(\""+tmpdisstr+"\")=\""+outval+"\"");
                }
            }
            else {
                System.out.println("Global Does not Exists !!!");
            }
            iris.close();
            conn.close();
        }
            
        }
    catch (Exception ex) {
            System.out.println("Exception -- "+ex.getMessage());
        }
    
    }
public static void GlobalDelete()
    {
        
    try
    {
            Scanner reader = new Scanner(System.in);
            System.out.print("Enter a Global Name: ");
            String InpGlobalName = reader.nextLine();
            if ((InpGlobalName.isEmpty()==false)&&(InpGlobalName.contains("^")==false)) InpGlobalName="^"+InpGlobalName;
            if (InpGlobalName.isEmpty()==true) {
                System.out.println("EMPTY Value entered \n ");
                return;
            }
            System.out.println("Enter Subscript(s) as comma separated, for global " + InpGlobalName);
            String Inpsubscrpts = reader.nextLine();
            
        try (IRISConnection conn = (IRISConnection) DriverManager.getConnection("jdbc:IRIS://localhost:"+superserverPort+"/"+namespace,username,password)) {
            IRIS iris = IRIS.createIRIS(conn);
            if (Inpsubscrpts.isEmpty()==false) {
                Inpsubscrpts=Inpsubscrpts.replace("\"", "");
                String[] SubScrpts = Inpsubscrpts.split(",");
                iris.kill(InpGlobalName,(Object[])SubScrpts);
                System.out.println("Global Deleted ");
                
             }
            else{
                iris.kill(InpGlobalName);
                System.out.println("Global Deleted ");
            }
            iris.close();
            conn.close();
            
        }
    }
    catch (Exception ex) {
            System.out.println("Exception -- "+ex.getMessage());
        }
    
    }

     public static void GlobalView(){
        try {    
       restart:while(true) {
            IRISConnection conn = (IRISConnection) DriverManager.getConnection("jdbc:IRIS://localhost:"+superserverPort+"/"+namespace,username,password);
            IRIS iris = IRIS.createIRIS(conn);
            Scanner reader = new Scanner(System.in);
            System.out.print("Enter a Global Name: ");
            String InpGlobalName = reader.nextLine();
            System.out.println("You entered: " + InpGlobalName);
            if (InpGlobalName.isEmpty()==true) {
                System.out.println("EMPTY Value enetered \n ");
                iris.close();
                conn.close();
                break;
            }
            if (InpGlobalName.contains("^")==false) InpGlobalName="^"+InpGlobalName;
                   
                
            IRISIterator subscriptIter = iris.getIRISIterator(InpGlobalName);
            //System.out.print(subscriptIter+"\n");
            
            
            if (subscriptIter.hasNext()==false){
                System.out.print("Global:"+InpGlobalName+" Does not exists \n");
                continue;
                
            }
            int cnt=0;
            String DisplayStr="";
            if (subscriptIter.hasNext()==true){
                while (subscriptIter.hasNext()) {
                    String subscript = subscriptIter.next();
                    String Subvalue= String.valueOf(subscriptIter.getValue());
                    if (InpGlobalName.contains("(")==false) DisplayStr=InpGlobalName+"(\""+subscript+"\")="+Subvalue;
                    else if ((InpGlobalName.contains(")")==true)) {
                        if (isObjectInteger(subscript)==false) DisplayStr=InpGlobalName.replace(")", "") +","+subscript+")=\""+Subvalue+"\"";
                    }   else DisplayStr=InpGlobalName.replace(")", "") +","+subscript+"\")=\""+Subvalue+"\"";
                         
                  
                    System.out.println(DisplayStr);
                    
                }
            }
           // close connection and IRIS object
            iris.close();
            conn.close();
            break;
       }
        }
        catch (Exception ex) {
                System.out.println(ex.getMessage());
              
        }
      
    }
    public static void GlobalSearch(){
    try 
    {
        restart:while(true){
            Scanner reader = new Scanner(System.in);
            System.out.print("Enter a Global Name: ");
            String InpGlobalName = reader.nextLine();
            System.out.println("You Entered: " + InpGlobalName);
            if (InpGlobalName.isEmpty()==true) {
                System.out.println("EMPTY Value entered \n ");
                break ;
            }
            if (InpGlobalName.contains("^")==false) InpGlobalName="^"+InpGlobalName;
            IRISConnection conn = (IRISConnection) DriverManager.getConnection("jdbc:IRIS://localhost:"+superserverPort+"/"+namespace,username,password);
            IRIS iris = IRIS.createIRIS(conn);
            IRISIterator subscriptIter = iris.getIRISIterator(InpGlobalName);
                      
            if (subscriptIter.hasNext()==false){
                System.out.print("Global:"+InpGlobalName+" Does not exists \n");
                continue;
            }
            if (subscriptIter.hasNext()==true){
                findglobal(InpGlobalName,iris);
            }
            iris.close();
            conn.close();
        }
    }
    catch (Exception ex) {
        System.out.println(ex.getMessage());
    }
      
    }
  static void findglobal(String InpGlobalName,IRIS piris)
  {
      try
      {
        restart:while(true)
        { 
            IRISIterator tmpsubobj = piris.getIRISIterator(InpGlobalName);
            Scanner reader = new Scanner(System.in);
            System.out.print("Enter the value to find : ");
            String InpFind = reader.nextLine();
            if (InpFind.isEmpty()==true){
                System.out.print("EMPTY Value entered \n");
                break;
            }
           
            System.out.println("Finding : " + InpFind + " in Global "+ InpGlobalName);
            while (tmpsubobj.hasNext()) 
            {
               String subscript = tmpsubobj.next();
               String Subvalue= String.valueOf(tmpsubobj.getValue());
               if (Subvalue.contains(InpFind)==true) 
               {   
                   String DisplayStr="";
                   if (InpGlobalName.contains("(")==false) DisplayStr=InpGlobalName+"(\""+subscript+"\")=\""+Subvalue+"\"";
                    else if ((InpGlobalName.contains(")")==true)) {
                        if (isObjectInteger(subscript)==false) DisplayStr=InpGlobalName.replace(")", "") +","+subscript+")=\""+Subvalue+"\"";
                    }   else DisplayStr=InpGlobalName.replace(")", "") +","+subscript+"\")=\""+Subvalue+"\""; 
                   
                   System.out.println(DisplayStr);
               }
            }
         }
        }
        catch(IllegalStateException | NoSuchElementException ex){
            System.out.println(ex.getMessage());
        }
    
  }
 public static boolean isObjectInteger(Object o)
{
    return o instanceof Integer;
}

   
}
