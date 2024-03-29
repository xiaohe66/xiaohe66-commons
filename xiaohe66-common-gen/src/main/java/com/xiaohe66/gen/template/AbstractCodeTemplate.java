package com.xiaohe66.gen.template;

import com.xiaohe66.gen.template.bo.CodeTemplateProperty;
import lombok.Getter;
import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.VelocityEngine;
import org.apache.velocity.runtime.RuntimeConstants;
import org.apache.velocity.runtime.resource.loader.ClasspathResourceLoader;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * @author xiaohe
 * @since 2022.02.22 17:11
 */
@Getter
public abstract class AbstractCodeTemplate implements CodeTemplate {

    protected static final VelocityEngine VELOCITY_ENGINE = new VelocityEngine();

    protected final CodeTemplateProperty property;
    protected final Template template;
    protected final String templateName;

    static {
        VELOCITY_ENGINE.setProperty(RuntimeConstants.RESOURCE_LOADER, "classpath");
        VELOCITY_ENGINE.setProperty("classpath.resource.loader.class", ClasspathResourceLoader.class.getName());
        VELOCITY_ENGINE.init();
    }

    public AbstractCodeTemplate(CodeTemplateProperty property, String templateFileName) {

        this.property = property;
        this.template = VELOCITY_ENGINE.getTemplate(templateFileName);
        this.templateName = templateFileName;
    }

    protected final VelocityContext genDefaultContext(String entityName) {

        VelocityContext velocityContext = new VelocityContext();

        velocityContext.put("package", property.getClassPackage());
        velocityContext.put("entityName", entityName);

        String time = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));

        velocityContext.put("time", time);

        return velocityContext;
    }
}
