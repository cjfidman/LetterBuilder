package LetterBuilder;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URLDecoder;
import java.util.*;
import javax.swing.JOptionPane;
import junit.framework.Test;
import static LetterBuilder.LetterBuilder.customerFile;

/**
 *
 * @author Cornell Hall III
 */
public class CustomerList{
    
    //Create LinkedList for data
       static LinkedList<Customer> cList = new LinkedList();
    //Create Hashmap for data
       static HashMap hMap = new HashMap<Integer,Customer>(5);
    //ArrayList for Customer data
       static ArrayList<Customer> cArray = new ArrayList<Customer>();
    
    public static void addArrayEntry(Customer c) {
        //Add new Customer object to Array
        cArray.add(c);
    }    
    
    //Performs a binary search of COntributor list for Last name
    //int a = array size while int b = lower limit
    public static Customer bSearch(String input, int a, int y){
        SortlName(cArray);
        int z=cList.size();
        int top=a;
        int b=a/2;
        Customer C = new Customer();

        if(cList.size()==a){
        System.out.println("Searching Array for last name: "+input+".......\n");
        }
        
        while(z>=0){ 
            
            Customer object = cArray.get(b);
            String lName = object.getlName();
            
            if (lName.compareToIgnoreCase(input) > 0){
                b=b/2;    
            }
            if (lName.compareToIgnoreCase(input) < 0){
                b=b+((top-b)/2);
            }
            if (lName.compareToIgnoreCase(input) == 0){
                C = object;
                return C;
            }
            z--;
        }
        return C;
}
    
    public static void SortlName(ArrayList<Customer> A){
        
        Comparator<Customer> cmp = Comparator.comparing(
             Customer::getlName, String.CASE_INSENSITIVE_ORDER);
        Collections.sort(A,cmp);
    }
    
    public static void SortfName(ArrayList<Customer> A){
        Comparator<Customer> cmp = Comparator.comparing(
             Customer::getfName, String.CASE_INSENSITIVE_ORDER);
        Collections.sort(A, cmp);
    }    
    
    public void loadFile() throws FileNotFoundException{
        //Define file as variable
            String fileLine; 
        try{
        //Create File reader and encase in Buffered reader
            String path = Test.class.getProtectionDomain().getCodeSource().getLocation().getPath();
            String decodedPath = URLDecoder.decode(path.substring(1, path.lastIndexOf("/")+1), "UTF-8");
            System.out.println(customerFile);
         //   try (InputStream is = this.getClass().getResourceAsStream(decodedPath+"/customers.csv")) {
            try (FileInputStream fileInput = new FileInputStream(customerFile)){
                BufferedReader br = new BufferedReader(new InputStreamReader(fileInput)); 
                    //Add Customer entries to list and array
                    while ((fileLine = br.readLine()) != null) {
                        Customer customer = newCustomer(fileLine);
                        System.out.println(fileLine);
                        addArrayEntry(customer);
                    }
                    //Sort entries by last name
                }
            SortlName(cArray); 
        //cArray.forEach((item)->{System.out.println(item.toString());});
        }
        catch (IOException e) {
            System.out.println(e.toString());
            }
        String success = "\n***Customer Data Loaded Successfully***\n";
        System.out.println(starLine(success.length()-2) + success + starLine(success.length()-2));  
    }

    private static Customer newCustomer(String input) {
        StringTokenizer line;
        int Num = 0;
        Customer c = new Customer();

        line = new StringTokenizer(input, ",");
        while(line.hasMoreTokens()){
        switch (Num) {
        case 0: c.setfName(line.nextToken());
        break;
        case 1: c.setlName(line.nextToken());
        break;
        case 2: c.setCity(line.nextToken());
        break;
        case 3: c.setCountry(line.nextToken());
        break;
        case 4: c.setPhone(line.nextToken());
        break;
        case 5: c.setContribution(Double.valueOf(line.nextToken()));
        break;
        case 6: c.setId(Integer.valueOf(line.nextToken()));
        break;
        }
        Num++;
        }
        Integer cID= c.getId();  
        hMap.put(cID, c);
        return c;
    }   

    public static void printArray(Customer[] a){
        Customer C;
        System.out.println(starLine()+"\n**Customer List***\n"+ starLine());    

        for (int count=0; count<cArray.size(); count++){
        C = a[count];
        System.out.println();
        System.out.println("Customer # " + (count+1)+ "\n"+ starLine());
        C.printData();
        }
    }
    
    public static void printCList(LinkedList <Customer> item){
        Customer C;
        System.out.println(starLine()+"\n**Customer List***\n"+ starLine());    

        for (int count=0; count<item.size(); count++){
        C = item.get(count);
        System.out.println();
        System.out.println("Customer # " + (count+1)+ "\n"+ starLine());
        C.printData();
        }
    }

    public static void printHash(){
        hMap.forEach((k,v) -> printHash((int) k));
    }
    
    public static void printHash(int x){
        Customer C = (Customer) hMap.get(x);
        C.printData();
    }

    public static void searchList(String input){
            int x=1, y=0;
            Customer C = new Customer();
            
        System.out.println("Searching List for last name: "+input+".......\n");
            
        while (x<cList.size()){
            Customer object = cList.get(x);
            String lName = object.getlName();

            if (lName.compareToIgnoreCase(input) == 0){
                C = object;
                y++;
            }
            x++;
        }
        if(y==0){
        System.out.print("Search Complete, no entry found.\n");
        System.out.print("Please verify entry...\n"+ starLine(32)+"\n");
        }
        else{
        System.out.print("Search Complete, "+y+ " entry found.\n"+ starLine(32)+"\n");
        C.printData();
        }
    }
  
    public static void searchHash(String input){
        Customer A = new Customer();
        int y=0;
        
        System.out.println("Searching HashTable for last name: "+input+".......\n");
        
        for(Object value:hMap.values()){
           Customer C = (Customer) value; 
           if (input.compareToIgnoreCase(C.getlName()) == 0){
               A=C;
               y++;
           }
        }
        if(y==0){
        System.out.print("Search Complete, no entry found.\n");
        System.out.print("Please verify entry...\n"+ starLine(32)+"\n");
        }
        else{
        System.out.print("Search Complete, "+y+ " entry found.\n"+ starLine(32)+"\n");
        A.printData();
        }
              
    }
    

    public static String starLine(){  
        String s="";
        for(int a=0;a<21;a++){
        s= s +"*";
        }
    return s;
    }
        public static String starLine(int b){  
        String s="";
        for(int a=0;a<b;a++){
        s= s +"*";
        }
    return s;
    }

}


