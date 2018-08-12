import java.util.*;
import java.lang.*;
import java.io.*;

class Invoice
{
    int n;
    String i;
    double c;
    public static final double salestax = 20;
    public static final double salestax_on_medicine = 0;
    Invoice(int n,String i,double c){
        this.n = n;
        this.i = i;
        this.c = c;
    }
	public static void main (String[] args) throws java.lang.Exception
	{
	   ArrayList<Invoice> ar = new ArrayList<Invoice>();
	   Invoice cd1 = new Invoice(1,"bottle of wine",20);
	   Invoice cd2 = new Invoice(2,"box of headache pills",4);
	   Invoice cd3 = new Invoice(1,"box of pens",10);
	   ar.add(cd1);
	   ar.add(cd2);
	   ar.add(cd3);
	   System.out.println("Output of Test case 1\n");
	   printInvoice(ar); 
	   ar.remove(cd1);
       ar.remove(cd2);
       ar.remove(cd3);
       
       Invoice cd4 = new Invoice(1,"book",30);
	   Invoice cd5 = new Invoice(1,"chocolate",1);
	   ar.add(cd4);
	   ar.add(cd5);
       System.out.println("Output of Test case 2\n");
       printInvoice(ar);
       ar.remove(cd4);
       ar.remove(cd5);
       
       Invoice cd6 = new Invoice(1,"pen",5);
       System.out.println("Output of Test case 3\n");
       ar.add(cd6);
       printInvoice(ar);
       ar.remove(cd6);
	}
	
	public static void printInvoice(ArrayList ar){
	   
	   Iterator<Invoice> iterator = ar.iterator();
	   while (iterator.hasNext()) {
	       Invoice in = (Invoice)iterator.next();
           System.out.print(in.n+" ");
           System.out.print(in.i+": ");
           System.out.print(MRP_individual(in.n,in.c,in.i));
           System.out.println();
        }
        System.out.println("Sales Taxes: "+salestax(ar));
        System.out.println("Total: "+totalCost(ar));
        System.out.println();
	}
	
	public static double salestax(ArrayList ar){
	    double sum = 0;
	    Iterator<Invoice> iterator_sales = ar.iterator();
	    while (iterator_sales.hasNext()) {
	       Invoice in = (Invoice)iterator_sales.next();
           sum = sum + (taxOnItem(in.c,findSalesTaxRate(in.i)));
        }
        return sum;
	}
	
	public static double totalCost(ArrayList ar){
	    double sum_total = 0;
	    Iterator<Invoice> iterator_sales = ar.iterator();
	    while (iterator_sales.hasNext()) {
	       Invoice in = (Invoice)iterator_sales.next();
           sum_total = sum_total + (in.n*((in.c)+(taxOnItem(in.c,findSalesTaxRate(in.i)))));
        }
	    return sum_total;
	}
	
	public static double MRP_individual(int num,double cost,String inventory){
	    return num*(cost+(taxOnItem(cost,findSalesTaxRate(inventory))));
	    
	}

	public static double taxOnItem(double cost,double salesTaxRateIndividual){
	    return ((cost*salesTaxRateIndividual)/100);
	}
	
	public static double findSalesTaxRate(String inventory){
	    if(checkIfMedicine(inventory))
	    return salestax_on_medicine;
	    else
	    return salestax;
	}
	/*To Extend this code to add new sales tax for other Items create methods 
	similar to checkIfMedicine(String inventory) and add that check 
	in findSalesTaxRate(String inventory) method. Provide the 
	sales tax rate as a constant at the start of this class similar to salestax_on_medicine */
	
	public static boolean checkIfMedicine(String inventory){
	    if(inventory.contains("pill")||inventory.contains("medicine"))
	    return true;
	    else 
	    return false;
	}
}
