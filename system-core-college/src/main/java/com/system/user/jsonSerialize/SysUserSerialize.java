package com.system.user.jsonSerialize;

import java.io.IOException;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.system.user.entity.SysUsers;


public class SysUserSerialize extends JsonSerializer<SysUsers>
{
	  public void serialize(SysUsers value, JsonGenerator jgen, SerializerProvider provider)
	    throws IOException, JsonProcessingException
	  {
	    jgen.writeStartObject();
	    jgen.writeNumberField("id", value.getId().longValue());
	    jgen.writeStringField("username", value.getUsername());
//	    jgen.writeStringField("truename", value.getTruename());
	    jgen.writeStringField("code", value.getCode());
	    jgen.writeStringField("sex", value.getSex()+"");
	    jgen.writeStringField("tel", value.getTel());
	    jgen.writeEndObject();
	  }
	}