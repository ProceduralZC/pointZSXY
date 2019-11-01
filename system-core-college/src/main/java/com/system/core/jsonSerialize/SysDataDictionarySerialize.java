package com.system.core.jsonSerialize;

import java.io.IOException;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.system.core.entity.SysDataDictionary;

public class SysDataDictionarySerialize extends JsonSerializer<SysDataDictionary> {
	public void serialize(SysDataDictionary value, JsonGenerator jgen,
			SerializerProvider provider) throws IOException,
			JsonProcessingException {
		jgen.writeStartObject();
		jgen.writeNumberField("id", value.getId());
		jgen.writeStringField("name", value.getName());
		jgen.writeNumberField("property", value.getProperty());
		jgen.writeStringField("dataCode", value.getDataCode());
		jgen.writeStringField("code", value.getCode()==null?"":value.getCode());
		jgen.writeEndObject();
	}
}