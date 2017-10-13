//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.jeecg.auth.util;

import com.jeecg.auth.entity.JwSystemAuth;
import com.jeecg.auth.entity.TreeNode;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import net.sf.json.JSONArray;

public class SystemUtil {
    public SystemUtil() {
    }

    public static String listTreeToAuth(List<JwSystemAuth> allList) {
        if(allList != null && allList.size() > 0) {
            ArrayList treeList = new ArrayList();
            Iterator var3 = allList.iterator();

            while(var3.hasNext()) {
                JwSystemAuth jsonArray = (JwSystemAuth)var3.next();
                TreeNode tn = new TreeNode();
                tn.setId(jsonArray.getAuthId());
                String pId = "0";
                if(jsonArray.getParentAuthId() != null && !jsonArray.getParentAuthId().equals("")) {
                    pId = jsonArray.getParentAuthId();
                }

                tn.setpId(pId);
                tn.setName(jsonArray.getAuthName());
                if(!"1".equals(jsonArray.getAuthType()) && !"Y".equals(jsonArray.getLeafInd())) {
                    tn.setOpen(true);
                } else {
                    tn.setOpen(false);
                }

                tn.setChecked(false);
                tn.setDoCheck(false);
                tn.setHalfCheck(false);
                tn.setParent(false);
                tn.setChkDisabled(false);
                tn.setNocheck(false);
                treeList.add(tn);
            }

            JSONArray jsonArray1 = JSONArray.fromObject(treeList);
            return jsonArray1.toString();
        } else {
            return "";
        }
    }
}
