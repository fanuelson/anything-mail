package br.com.any.service;

import java.io.Serializable;
import java.io.StringWriter;

import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.VelocityEngine;
import org.apache.velocity.runtime.RuntimeConstants;
import org.apache.velocity.runtime.resource.loader.ClasspathResourceLoader;

import br.com.any.annotation.VelocityTemplate;
import br.com.any.template.AbstractVelocityTemplate;

public class VelocityGeneratorService implements Serializable {

	private static final String UTF_8 = "UTF-8";

	private static final long serialVersionUID = 1L;
	
	private static final String RESOURCE_PATH = "/src/main/resources/";

	private VelocityEngine ve;

	public VelocityGeneratorService() {
		ve = new VelocityEngine();
	}

	public String generateString(AbstractVelocityTemplate velocityTemplate) {
		String templateDirectory = velocityTemplate.getTemplateDirectory();
		if(templateDirectory.contains(RESOURCE_PATH)){
			ve.setProperty(RuntimeConstants.RESOURCE_LOADER, "classpath");
			ve.setProperty("classpath.resource.loader.class", ClasspathResourceLoader.class.getName());
			templateDirectory = templateDirectory.replace(RESOURCE_PATH, "");
		}else{
			this.ve.setProperty(RuntimeConstants.FILE_RESOURCE_LOADER_PATH, velocityTemplate.getTemplateDirectory());
			templateDirectory = "";
		}
		this.ve.init();

		VelocityContext context = new VelocityContext(velocityTemplate.getParametros());
		String templateFileName = velocityTemplate.getClass().getAnnotation(VelocityTemplate.class).templateFileName();
		Template t = this.ve.getTemplate(templateDirectory+templateFileName, UTF_8);

		StringWriter writer = new StringWriter();

		t.merge(context, writer);

		return writer.toString();
	}
}
