package com.linage.transform;

import java.io.IOException;
import java.io.PrintWriter;

/**
 * 
 * @author edwin permana
 * This class connect to Impala and Transform
 */
public class ClouderaImpala {

	public static void main(String[] args) {
		
		try {
			TransformInforTransactionLog tt =  new TransformInforTransactionLog();
			String cmd= "impala-shell -q "+"\"" +tt.buildSQL()+"\"";  
			System.out.println("Executing: \n" );
		
	
			PrintWriter out = new PrintWriter("infor_tranlog_transform.sh");
			out.println(cmd);
			out.close();
		} catch (IOException e) {
			 
			e.printStackTrace();
		}
	
	}
}
