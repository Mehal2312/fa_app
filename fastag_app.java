package fastag_app;

import java.util.*;

interface user {
    public void data_disp();
}

abstract class regular implements user{
    public String v_id;
    public String o_name;
    Scanner inp_str = new Scanner(System.in);
    Scanner inp_int = new Scanner(System.in);
    Scanner inp_float = new Scanner(System.in);
    regular(){
        System.out.println("Enter Vehicle Id: ");
        v_id =  inp_str.nextLine();        
        System.out.println("Enter Owner Name: ");
        o_name = inp_str.nextLine();
    }
    abstract public void renewal();
    abstract public void reduction();
    abstract public void data_disp();
}

class vip implements user{
    String v_id;
    String o_name;
    Scanner inp_str = new Scanner(System.in);
    Scanner inp_int = new Scanner(System.in);
    Scanner inp_float = new Scanner(System.in);
    vip(){
        System.out.println("Enter Vehicle Id: ");
        v_id =  inp_str.nextLine();        
        System.out.println("Enter Owner Name: ");
        o_name = inp_str.nextLine();
    }
    public void data_disp(){
        System.out.println("Vehicle Id: "+v_id);
        System.out.println("Vehicle Owner Name: "+o_name);
        System.out.println("VIP Vehicle");
    }
    public void vip_validate(){
        System.out.println("Enter VIP Admin Password: ");
        if(inp_int.nextInt() == 1234 ){
            System.out.println("Vehicle Permitted");            
        }
        else{
            System.out.println("Vehicle Not Permitted.. Try Again");
            vip_validate();            
        }
    }
}

class min_bal_ex extends Exception{
    min_bal_ex(String msg){
        super(msg);
    }
}

class prepaid extends regular{
    public float bal;
    prepaid()
    {
        super();
    }
    public void renewal(){
        System.out.println("Amount to Renew: ");
        bal += inp_float.nextFloat();
        System.out.println("Current Balance: "+bal);
    }
    public void data_disp(){
        System.out.println("Vehicle Id: "+v_id);
        System.out.println("Vehicle Owner Name: "+o_name);
        System.out.println("Balance: "+bal);
    }
    public void reduction(){
        System.out.println("Enter Toll Charges: "); 
        float red = inp_float.nextFloat();
        
        try{
            if((bal - red)<0){
                throw new min_bal_ex("Insufficient Balance");                      
            }
            else{
                bal += inp_float.nextFloat();
                System.out.println("Current Balance: "+bal);
            }
        }
        catch(min_bal_ex e){
            System.out.println("Insufficient Balance");
        }        
    }
}

class postpaid extends regular{
    public float bill=0;
    public void renewal(){
        System.out.println("Total Bill: " + bill);
        System.out.println("Settle Bill (Y/N)");
        String ch = inp_str.nextLine();
        if(ch.equals("Y")){
            bill=0;
            System.out.println("Bill Settled");
        } 
    }
    public void reduction(){
        System.out.println("Enter Toll Charges: "); 
        float red = inp_float.nextFloat();
        bill+=red;        
    }
    public void data_disp(){
        System.out.println("Vehicle Id: "+v_id);
        System.out.println("Vehicle Owner Name: "+o_name);
        System.out.println("Bill: "+bill);
    }
}

public class fastag_app {

    public static void main(String[] args) {
       System.out.println("Fastag: ");
       vip u1 = new vip();
       System.out.println("VIP: ");
       int i=0;
       while(i<5){
           System.out.println("Toll "+i+":");
           u1.vip_validate();
           u1.data_disp();
           i++;
       }  
       System.out.println("Prepaid: ");
       prepaid u2 = new prepaid();
       i=0;
       while(i<5){
           u2.renewal();
//           u2.reduction();
           u2.data_disp();
           i++;
       }  
       System.out.println("Postpaid: ");
       postpaid u3 = new postpaid();
       i=0;
       while(i<5){
           u3.renewal();
           u3.reduction();
           u3.data_disp();
           i++;
       }  
       
       
       
    }  
}
