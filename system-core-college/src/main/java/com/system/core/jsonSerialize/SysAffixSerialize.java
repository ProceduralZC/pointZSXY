package com.system.core.jsonSerialize;

import java.io.IOException;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.system.core.entity.SysAffix;

public class SysAffixSerialize extends JsonSerializer<SysAffix>
{
	  public void serialize(SysAffix value, JsonGenerator jgen, SerializerProvider provider)
	    throws IOException, JsonProcessingException
	  {
	    jgen.writeStartObject();
	    jgen.writeNumberField("id", value.getId());
	    jgen.writeStringField("name", value.getName());
	    jgen.writeStringField("source", value.getSource());
	    jgen.writeStringField("size", value.getSize());
	    jgen.writeStringField("type", value.getType());
	    jgen.writeStringField("extname", value.getExtname());
	    if(null!=value.getWidth()){
	    	jgen.writeNumberField("width", value.getWidth());
	    }
	    if(null!=value.getHeight()){
	    	jgen.writeNumberField("height", value.getHeight());
	    }
	    jgen.writeEndObject();
	  }
}