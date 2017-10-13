//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package org.jeecgframework.p3.core.directive;

import com.jeecg.core.biz.dao.TSOperationDao;
import java.io.IOException;
import java.io.StringWriter;
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
import org.apache.velocity.runtime.parser.node.SimpleNode;
import org.jeecgframework.p3.core.util.plugin.ContextHolderUtils;
import org.jeecgframework.p3.core.utils.common.ApplicationContextUtil;
import org.jeecgframework.p3.core.utils.common.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class AuthOperateTagDirective extends Directive {
    private static final Logger log = LoggerFactory.getLogger(AuthOperateTagDirective.class);

    public AuthOperateTagDirective() {
    }

    public String getName() {
        return "AuthOperateTag";
    }

    public int getType() {
        return 1;
    }

    public boolean render(InternalContextAdapter context, Writer writer, Node node) throws IOException, ResourceNotFoundException, ParseErrorException, MethodInvocationException {
        SimpleNode sn_operatecode = (SimpleNode)node.jjtGetChild(0);
        String operatecode = (String)sn_operatecode.value(context);
        log.info("[AuthOperateTag] operatecode:{}", new Object[]{operatecode});
        if(StringUtils.isEmpty(operatecode)) {
            return false;
        } else {
            String html = "";
            Node body = node.jjtGetChild(1);
            Set operationCodeIds = ContextHolderUtils.getPageSelectOperationCodes();
            log.info("[AuthOperateTag] operationCodeIds:{}", new Object[]{operationCodeIds});
            if(operationCodeIds != null) {
                Iterator var10 = operationCodeIds.iterator();

                while(var10.hasNext()) {
                    String myoperationCodeId = (String)var10.next();
                    if(StringUtils.isEmpty(myoperationCodeId)) {
                        break;
                    }

                    TSOperationDao operationDao = (TSOperationDao)ApplicationContextUtil.getContext().getBean(TSOperationDao.class);
                    Map operationCodeMap = operationDao.getOperationCode(myoperationCodeId);
                    String operationCode = (String)operationCodeMap.get("OPERATIONCODE");
                    if(operatecode.equals(operationCode)) {
                        StringWriter sw = new StringWriter();
                        body.render(context, sw);
                        html = sw.toString();
                    }
                }
            }

            log.info("[AuthOperateTag] html:\n {}", new Object[]{html});
            writer.write(html);
            return true;
        }
    }
}
