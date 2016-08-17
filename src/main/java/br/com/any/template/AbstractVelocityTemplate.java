package br.com.any.template;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.reflect.FieldUtils;

import br.com.any.annotation.VelocityParameter;

public abstract class AbstractVelocityTemplate {

	public Map<String, Object> getParametros() {
		Map<String, Object> data = new HashMap<String, Object>();
		try{
			
			List<Field> fields = FieldUtils.getFieldsListWithAnnotation(getClass(), VelocityParameter.class);
			for (Field field : fields) {
				Object valor = FieldUtils.readField(this, field.getName(), true);
				String parameterName = getParameterName(field);
				data.put(parameterName, valor);
			}
		} catch (IllegalArgumentException e){
			throw new RuntimeException("REFLECT ERROR ",e);
		} catch (IllegalAccessException e) {
			throw new RuntimeException("REFLECT ERROR ",e);
		}
		return data;
	}

	private String getParameterName(Field field) {
		String parameterName = field.getAnnotation(VelocityParameter.class).name();
		if (parameterName == null || parameterName.isEmpty()) {
			parameterName = field.getName();
		}
		return parameterName;
	}

	public abstract String getTemplateDirectory();

}
