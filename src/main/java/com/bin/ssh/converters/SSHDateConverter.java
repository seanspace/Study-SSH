package com.bin.ssh.converters;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

import org.apache.struts2.util.StrutsTypeConverter;

public class SSHDateConverter extends StrutsTypeConverter {
	private DateFormat dateFormat ;
	
	{
		dateFormat = new SimpleDateFormat("yyyy-MM-dd") ;
	}
	
	@Override
	public Object convertFromString(Map context, String[] value, Class toClass) {
		if (toClass == Date.class) {
			try {
				return dateFormat.parse(value[0]) ;
			} catch (ParseException e) {
				e.printStackTrace();
			}
		}
		return null;
	}

	@Override
	public String convertToString(Map arg0, Object arg1) {
		if (arg1 instanceof Date) {
			return dateFormat.format(arg1) ;
		}
		return null;
	}

}
