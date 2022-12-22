package com.urbanladder.utilities;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class ReadConfig {

	Properties pro;

	public ReadConfig()
	{
		try {

			FileInputStream fis=new FileInputStream(".//Configurations//config.properties");
			pro=new Properties();
			pro.load(fis);

		}catch(Exception e)
		{
          System.out.println("Exception is "+e.getMessage());
		}
	}

	public String getApplicationUrl()
	{
		return pro.getProperty("baseUrl");
	}

}
