package com.ejavashop.web.util.freemarker;

import java.util.List;

import com.ejavashop.web.util.freemarkerutil.CodeManager;

import freemarker.template.TemplateMethodModel;
import freemarker.template.TemplateModelException;

public class InitJSCodeContainerTemplateMethodModel implements TemplateMethodModel {

    @Override
    public Object exec(List arguments) throws TemplateModelException {
        return CodeManager.getCodesJsonByDivs(arguments.toArray());
    }

}
