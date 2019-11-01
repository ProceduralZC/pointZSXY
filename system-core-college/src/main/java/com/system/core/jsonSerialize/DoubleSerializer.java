package com.system.core.jsonSerialize;

import java.io.IOException;
import java.text.DecimalFormat;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

public class DoubleSerializer extends JsonSerializer<Double> {  
	@Override  
    public void serialize(Double value, JsonGenerator jgen, SerializerProvider provider) throws IOException, JsonProcessingException {  
	    DecimalFormat formatter = new DecimalFormat("#0.00");  
	    String formattedDate = formatter.format(value);  
	    jgen.writeString(formattedDate);  
    }  
}
