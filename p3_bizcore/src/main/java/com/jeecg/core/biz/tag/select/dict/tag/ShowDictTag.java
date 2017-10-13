//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.jeecg.core.biz.tag.select.dict.tag;

import com.jeecg.core.biz.mutiLang.service.P3MutiLangServiceI;
import com.jeecg.core.biz.tag.select.dict.dao.TSTypeDao;
import com.jeecg.core.biz.tag.select.dict.entity.TSTypeEntity;
import java.io.IOException;
import java.io.Writer;
import java.util.Iterator;
import java.util.List;
import org.apache.velocity.context.InternalContextAdapter;
import org.apache.velocity.exception.MethodInvocationException;
import org.apache.velocity.exception.ParseErrorException;
import org.apache.velocity.exception.ResourceNotFoundException;
import org.apache.velocity.runtime.directive.Directive;
import org.apache.velocity.runtime.parser.node.Node;
import org.apache.velocity.runtime.parser.node.SimpleNode;
import org.jeecgframework.p3.core.utils.common.ApplicationContextUtil;

public class ShowDictTag extends Directive {
    private static P3MutiLangServiceI mutiLangService = (P3MutiLangServiceI)ApplicationContextUtil.getContext().getBean("p3MutiLangService");
    private String defaultVal;

    public ShowDictTag() {
    }

    public String getName() {
        return "showDictTag";
    }

    public int getType() {
        return 2;
    }

    public boolean render(InternalContextAdapter context, Writer writer, Node node) throws IOException, ResourceNotFoundException, ParseErrorException, MethodInvocationException {
        SimpleNode sn_typeGroupCode = (SimpleNode)node.jjtGetChild(0);
        String typeGroupCode = (String)sn_typeGroupCode.value(context);
        SimpleNode sn_defaultVal = (SimpleNode)node.jjtGetChild(1);
        String defaultVal = (String)sn_defaultVal.value(context);
        writer.write(this.queryDic(typeGroupCode, defaultVal));
        return true;
    }

    private String queryDic(String typeGroupCode, String defaultVal) {
        this.defaultVal = defaultVal;
        TSTypeDao typeDao = (TSTypeDao)ApplicationContextUtil.getContext().getBean("TSTypeDao");
        List types = typeDao.getTypeByTypeGroupCode(typeGroupCode).getResults();
        StringBuffer sb = new StringBuffer();
        Iterator var7 = types.iterator();

        while(var7.hasNext()) {
            TSTypeEntity type = (TSTypeEntity)var7.next();
            this.select(type.getTypename(), type.getTypecode(), sb);
        }

        return sb.toString();
    }

    private void select(String name, String code, StringBuffer sb) {
        if(code.equals(this.defaultVal)) {
            sb.append(mutiLangService.getLang(name));
        }

    }
}
