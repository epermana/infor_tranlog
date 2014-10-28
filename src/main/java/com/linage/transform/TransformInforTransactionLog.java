package com.linage.transform;


import java.sql.SQLException;
import java.util.HashMap;

import com.linage.mapping.MappingReader;

public class TransformInforTransactionLog {

	  private String mappingconfigFile = "infor_t_tran_log_mapping.properties";
	  private String transformconfigFile= "infor_itrn_orderdetail_transform.properties";
	  /**
	   * @param args
	   * @throws SQLException
	   */
	  public static void main(String[] args) throws SQLException {
	  //Test SQL
		  TransformInforTransactionLog tt =  new TransformInforTransactionLog();
		  System.out.println(tt.buildSQL());
	  }
	
	  /**
	   * 
	   * @return String of SQL to execute transformation
	   */
	  public String buildSQL(){
		 //
		  MappingReader readMapping= new MappingReader();		
		  MappingReader readTransform= new MappingReader();
		 
			HashMap<String, String> inforTable = readMapping.readPropertyFile(this.transformconfigFile);
			HashMap<String,String> inforMap = readTransform.readPropertyFile(this.mappingconfigFile );
		  
		  StringBuffer insert = new StringBuffer("Insert into ");
		
		  
		  insert.append(inforTable.get("destination_table"));
		  insert.append("\n");
		  insert.append("select  \n");
		 
		  for (String key : inforMap.keySet()) {
			  insert.append(inforMap.get(key));
			  insert.append(" as ");insert.append(key);
			  insert.append(",\n");
			}
		  if (insert.length() > 0) {
			   insert.setLength(insert.length() - 2);
			}
		  
		  insert.append(" from ");
		  insert.append(inforTable.get("source_table"));
		  insert.append("\n");
		  insert.append(" LEFT OUTER JOIN ");
		 
		  insert.append(inforTable.get("source_leftjoin_table"));
		  insert.append(" \n");
		  insert.append(" ON (");//(itrn.serialkey = orderdetail.serialkey)
		  insert.append(inforTable.get("source_table")+"."+ inforTable.get("leftjoin_key") );
		  insert.append("=");
		  insert.append(inforTable.get("source_leftjoin_table")+"."+ inforTable.get("leftjoin_key") );
		  insert.append(")");
		  return insert.toString();
	  }
	  
	

}
