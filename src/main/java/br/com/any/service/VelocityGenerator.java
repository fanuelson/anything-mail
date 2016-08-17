package br.com.any.service;

import java.io.Serializable;
import java.io.StringWriter;

import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.VelocityEngine;
import org.apache.velocity.runtime.RuntimeConstants;

import br.com.any.annotation.VelocityTemplate;
import br.com.any.template.AbstractVelocityTemplate;

public class VelocityGenerator implements Serializable {

	private static final long serialVersionUID = 1L;

	private VelocityEngine ve;

	public VelocityGenerator() {
		ve = new VelocityEngine();
	}

	public String generateString(AbstractVelocityTemplate velocityTemplate) {

		this.ve.setProperty(RuntimeConstants.FILE_RESOURCE_LOADER_PATH, velocityTemplate.getTemplateDirectory());
		this.ve.init();

		VelocityContext context = new VelocityContext(velocityTemplate.getParametros());
		String templateFileName = velocityTemplate.getClass().getAnnotation(VelocityTemplate.class).templateFileName();
		Template t = this.ve.getTemplate(templateFileName);

		StringWriter writer = new StringWriter();

		t.merge(context, writer);

		return writer.toString();
	}
}
