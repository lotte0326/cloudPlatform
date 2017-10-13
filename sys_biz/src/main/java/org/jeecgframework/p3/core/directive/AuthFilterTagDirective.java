//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package org.jeecgframework.p3.core.directive;

import com.jeecg.core.biz.dao.TSOperationDao;
import java.io.IOException;
import java.io.Writer;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import org.apache.velocity.context.InternalContextAdapter;
import org.apache.velocity.exception.MethodInvocationException;
import org.apache.velocity.exception.ParseErrorException;
import org.apache.velocity.exception.ResourceNotFoundException;
import org.apache.velocity.runtime.directive.Directive;
import org.apache.velocity.runtime.parser.node.Node;
import org.jeecgframework.p3.core.util.plugin.ContextHolderUtils;
import org.jeecgframework.p3.core.utils.common.ApplicationContextUtil;
import org.jeecgframework.p3.core.utils.common.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class AuthFilterTagDirective extends Directive {
    private static final Logger log = LoggerFactory.getLogger(AuthFilterTagDirective.class);
    public static final Short OPERATION_TYPE_HIDE = Short.valueOf((short) 0);
    public static final Short OPERATION_TYPE_DISABLED = Short.valueOf((short)1);

    public AuthFilterTagDirective() {
    }

    public String getName() {
        return "AuthFilterTag";
    }

    public int getType() {
        return 2;
    }

    public boolean render(InternalContextAdapter context, Writer writer, Node node) throws IOException, ResourceNotFoundException, ParseErrorException, MethodInvocationException {
        log.info("[AuthFilterTag]");
        String html = "";
        StringBuilder out = new StringBuilder();

        try {
            this.getAuthFilter(out);
        } catch (Exception var7) {
            var7.printStackTrace();
        }

        html = out.toString();
        log.info("[AuthFilterTag] html:\n {}", new Object[]{html});
        writer.write(html);
        return true;
    }

    private void getAuthFilter(StringBuilder out) {
        out.append("<script type=\"text/javascript\">");
        out.append("$(document).ready(function(){");
        Set operationCodeIds = ContextHolderUtils.getPageSelectOperationCodes();
        if(operationCodeIds != null) {
            Iterator var4 = operationCodeIds.iterator();

            label27:
            while(true) {
                String operationCode;
                Integer operationType;
                do {
                    if(!var4.hasNext()) {
                        break label27;
                    }

                    String myoperationCodeId = (String)var4.next();
                    if(StringUtils.isEmpty(myoperationCodeId)) {
                        break label27;
                    }

                    TSOperationDao operationDao = (TSOperationDao)ApplicationContextUtil.getContext().getBean(TSOperationDao.class);
                    Map operationCodeMap = operationDao.getOperationCode(myoperationCodeId);
                    operationCode = (String)operationCodeMap.get("OPERATIONCODE");
                    operationType = (Integer)operationCodeMap.get("OPERATIONTYPE");
                } while(!operationCode.startsWith(".") && !operationCode.startsWith("#"));

                if(operationType.intValue() == OPERATION_TYPE_HIDE.shortValue()) {
                    out.append("$(\"" + operationCode.replaceAll(" ", "") + "\").hide();");
                } else {
                    out.append("$(\"" + operationCode.replaceAll(" ", "") + "\").attr(\"disabled\",\"disabled\");");
                    out.append("$(\"" + operationCode.replaceAll(" ", "") + "\").find(\":input\").attr(\"disabled\",\"disabled\");");
                }
            }
        }

        out.append("});");
        out.append("</script>");
    }
}
