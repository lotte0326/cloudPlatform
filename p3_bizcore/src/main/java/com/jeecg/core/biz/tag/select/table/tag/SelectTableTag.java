//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.jeecg.core.biz.tag.select.table.tag;

import com.jeecg.core.biz.tag.select.table.dao.CustomTableDao;
import java.io.IOException;
import java.io.Writer;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import org.apache.commons.lang.StringUtils;
import org.apache.velocity.context.InternalContextAdapter;
import org.apache.velocity.exception.MethodInvocationException;
import org.apache.velocity.exception.ParseErrorException;
import org.apache.velocity.exception.ResourceNotFoundException;
import org.apache.velocity.runtime.directive.Directive;
import org.apache.velocity.runtime.parser.node.Node;
import org.apache.velocity.runtime.parser.node.SimpleNode;
import org.jeecgframework.p3.core.utils.common.ApplicationContextUtil;

public class SelectTableTag extends Directive {
    private String defaultVal;

    public SelectTableTag() {
    }

    public String getName() {
        return "selectTableTag";
    }

    public int getType() {
        return 2;
    }

    public boolean render(InternalContextAdapter context, Writer writer, Node node) throws IOException, ResourceNotFoundException, ParseErrorException, MethodInvocationException {
        SimpleNode sn_customField = (SimpleNode)node.jjtGetChild(0);
        String customField = (String)sn_customField.value(context);
        SimpleNode sn_customText = (SimpleNode)node.jjtGetChild(1);
        String customText = (String)sn_customText.value(context);
        SimpleNode sn_customTable = (SimpleNode)node.jjtGetChild(2);
        String customTable = (String)sn_customTable.value(context);
        SimpleNode sn_field = (SimpleNode)node.jjtGetChild(3);
        String field = (String)sn_field.value(context);
        SimpleNode sn_defaultVal = (SimpleNode)node.jjtGetChild(4);
        String defaultVal = (String)sn_defaultVal.value(context);
        writer.write(this.queryCustom(customField, customText, customTable, field, defaultVal));
        return true;
    }

    private String queryCustom(String customField, String customText, String customTable, String field, String defaultVal) {
        this.defaultVal = defaultVal;
        CustomTableDao customTableDao = (CustomTableDao)ApplicationContextUtil.getContext().getBean("customTableDao");
        List customTableEntities = customTableDao.getCustomTable(customTable).getResults();
        StringBuffer sb = new StringBuffer();
        sb.append("<select");
        if(!StringUtils.isBlank(field)) {
            sb.append(" name=\"" + field + "\" id=\"" + field + "\"");
        }

        sb.append(">");
        this.select("--请选择--", "", sb);
        Iterator var10 = customTableEntities.iterator();

        while(var10.hasNext()) {
            Map entity = (Map)var10.next();
            this.select(((String)entity.get(customText)).toString(), ((String)entity.get(customField)).toString(), sb);
        }

        sb.append("</select>");
        return sb.toString();
    }

    private void select(String name, String code, StringBuffer sb) {
        if(code.equals(this.defaultVal)) {
            sb.append(" <option value=\"" + code + "\" selected=\"selected\">");
        } else {
            sb.append(" <option value=\"" + code + "\">");
        }

        sb.append(name);
        sb.append(" </option>");
    }
}
