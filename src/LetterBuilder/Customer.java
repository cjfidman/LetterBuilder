package LetterBuilder;

import java.text.NumberFormat;
import java.util.Arrays;


/**
 *
 * @author Cornell Hall III
 */
public class Customer{

//Define variables    
    String fName,lName, city, address, phone;
    double contribution;
    int id;
    
//Define format for currency   
    NumberFormat defaultFormat = NumberFormat.getCurrencyInstance();
//Define Constructors
    //Default Constructor
    public Customer(){        
        this.fName = "";
        this.lName = "";
        this.city = "";
        this.address = "";
        this.phone = "";
        this.contribution = 0;
        this.id =0;
    }
    
    //Parameterized Constructor
  public  Customer(String fName, String lName, String city, String address, String phone, double contribution, int id){
        this.fName = fName;
        this.lName = lName;
        this.city = city;
        this.address = address;
        this.phone = phone;
        this.contribution = contribution;
        this.id = id;
    }   
        
        
//Attribute Getters
    public String getfName(){
        return fName;
    }
    public String getlName(){
        return lName;
    }
    public String getCity(){
        return city;
    }
    public String getCountry(){
        return address;
    }    
    public String getPhone(){
        return phone;
    }
    public double getContribution(){
        return contribution;
    }
    public int getId(){
        return id;
    }

//Attribute Setters
    public void setfName(String fName){
        this.fName=fName;
    }
    public void setlName(String lName){
        this.lName=lName;
    }
    public void setCity(String city){
        this.city=city;
    }
    public void setCountry(String address){
        this.address=address;
    }    
    public void setPhone(String phone){
        this.phone=phone;
    }
    public void setContribution(double cont){
        this.contribution=cont;
    }
    public void setId(int ident){
        this.id=ident;
    }


//Print Contributor Data 
//
    public void printData(){
    String[] dataArray;
    dataArray = new String[]{
        "First Name: " + getfName(),"Last Name: " + getlName(),"City: " + getCity(), 
        "Phone: "+getPhone(),"Contribution: "+ defaultFormat.format(getContribution()),"ID: "+getId()+"\n"};    

    for(String d:dataArray){
        System.out.println(d);
    }
    }
    
    @Override
    public String toString(){
    String output;
    output= "\nFirst Name: " + getfName()+
            "\nLast Name: " + getlName()+
            "\nCity: " + getCity()+
            "\nPhone: "+getPhone()+
            "\nContribution: "+ defaultFormat.format(getContribution())+
            "\nID: "+getId();    
    return output;
    }

}
        
