package sapient;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;

public class outputTableGenerator {
int cId;
String tType;
String tDate;
boolean flag;
int fee;
public static String india = "";
public static String uk = "";
public static String hk = "";
public static String sg = "";
public outputTableGenerator(int cId, String tType, String tDate, boolean flag, int fee)
{
	this.cId = cId;
	this.tDate = tDate;
	this.tType = tType;
	this.flag = flag;
	this.fee = fee;
}
public static void convertFormatReqired(String line)
{
	String [] arr = line.split(",");
	if(arr[1].equals("UK"))
	{
		Double currency = calculate(Integer.parseInt(arr[4]),arr[3]);
		uk = uk + arr[1] + "," + arr[2] + "," + currency + "\n";
 	}
	else if(arr[1].equals("India"))
	{
		Double currency = calculate(Integer.parseInt(arr[4]),arr[3]);
		india = india + arr[1] + "," + arr[2] + "," + currency + "\n";
 	}
	else if(arr[1].equals("Singapore"))
	{
		Double currency = calculate(Integer.parseInt(arr[4]),arr[3]);
		sg = sg + arr[1] + "," + arr[2] + "," + currency + "\n";
 	}
	else if(arr[1].equals("HongKong"))
	{
		Double currency = calculate(Integer.parseInt(arr[4]),arr[3]);
		hk = hk + arr[1] + "," + arr[2] + "," + currency + "\n";
 	}
	else
		return;
}
public static void printLog() throws IOException
{
	uk = sortIncome(uk);
	india = sortIncome(india);
	hk = sortIncome(hk);
	sg = sortIncome(sg);
	String out = hk+india+sg+uk;
	System.out.println(out);
	try{    
        FileWriter fw=new FileWriter("output.csv");    
       fw.write(out);    
        fw.close();    
       }catch(Exception e){System.out.println(e);}    
       System.out.println("Created output file");
}

private static String sortIncome(String str) {
	
String [] arr = str.split("\n");
Arrays.sort(arr,new Sort());
str = "";
for(int i=0;i<arr.length;i++)
{
	str = str + arr[i] + "\n";
}
	return str;
}
public static Double calculate(int n,String curr)
{
	if(curr.equals("GBP"))
		return n/0.67;
	else if(curr.equals("INR"))
		return (n/66.0);
	else if(curr.equals("SGD"))
		return (n/1.5);
	else if(curr.equals("HKD"))
		return (n/8.0);
	else
		return null;
	
}


}
