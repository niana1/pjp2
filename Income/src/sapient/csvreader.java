package sapient;


	import java.io.BufferedReader;
	import java.io.File;
	import java.io.FileReader;
	import java.io.IOException;

	public class csvreader {
		   public static final String delimiter = ",";
		   public static void read(String csvFile) {
		      try {
		         File file = new File(csvFile);
		         FileReader fr = new FileReader(file);
		         BufferedReader br = new BufferedReader(fr);
		         String line = "";
		         String[] temp;
		         int i=0;
		         while((line = br.readLine()) != null) {
		        	 if(i==0) i=i+1;
		        	 else
		        	 {
		        		 outputTableGenerator.convertFormatReqired(line);
		        		 temp=line.split(delimiter);
		        		 for(String x:temp)
		        		 {
		        			 System.out.print(x+" ");
		        		 }
		        		 System.out.println();
		        	 }
		         }
		         br.close();
		         } catch(IOException ioe) {
		            ioe.printStackTrace();
		         }
		   }

	}


