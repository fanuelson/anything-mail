package br.com.any.template;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import br.com.any.annotation.VelocityParameter;
import br.com.any.utils.ReflectionUtils;

public abstract class AbstractVelocityTemplate {

	public Map<String, Object> getParametros() {
		Map<String, Object> data = new HashMap<String, Object>();
		List<Field> fields = ReflectionUtils.recuperarCamposAnotadosCom(getClass(), VelocityParameter.class);
		for (Field field : fields) {
			Object valor = ReflectionUtils.recuperarValorCampo(this, field.getName());
			String parameterName = getParameterName(field);
			data.put(parameterName, valor);
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
