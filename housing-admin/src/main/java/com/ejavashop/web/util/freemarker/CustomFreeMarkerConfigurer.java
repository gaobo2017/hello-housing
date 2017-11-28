package com.ejavashop.web.util.freemarker;

import java.io.IOException;
import java.util.List;

import org.springframework.web.servlet.view.freemarker.FreeMarkerConfigurer;

import com.ejavashop.web.shiro.tag.ShiroTags;

import freemarker.cache.TemplateLoader;
import freemarker.template.TemplateException;

public class CustomFreeMarkerConfigurer extends FreeMarkerConfigurer {

    @Override
    protected TemplateLoader getAggregateTemplateLoader(List<TemplateLoader> templateLoaders) {

        return new EscapeHtmlTemplateLoader(super.getAggregateTemplateLoader(templateLoaders));

    }

    /**
     * 重写本方法，目的是在FreeMarker的Configuration中添加shiro的配置 
     * @throws IOException
     * @throws TemplateException
     * @see org.springframework.web.servlet.view.freemarker.FreeMarkerConfigurer#afterPropertiesSet()
     */
    @Override
    public void afterPropertiesSet() throws IOException, TemplateException {
        super.afterPropertiesSet();
        this.getConfiguration().setSharedVariable("shiro", new ShiroTags());
    }
}
