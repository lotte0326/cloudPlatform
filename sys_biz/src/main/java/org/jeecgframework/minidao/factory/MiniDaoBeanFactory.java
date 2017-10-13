//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package org.jeecgframework.minidao.factory;

import java.lang.reflect.Proxy;
import org.jeecgframework.minidao.aop.MiniDaoHandler;
import org.springframework.beans.factory.FactoryBean;

public class MiniDaoBeanFactory<T> implements FactoryBean<T> {
    private Class<T> daoInterface;
    private MiniDaoHandler proxy;

    public MiniDaoBeanFactory() {
    }

    public T getObject() throws Exception {
        return this.newInstance();
    }

    public Class<?> getObjectType() {
        return this.daoInterface;
    }

    public MiniDaoHandler getProxy() {
        return this.proxy;
    }

    public boolean isSingleton() {
        return true;
    }

    private T newInstance() {
        return (T) Proxy.newProxyInstance(this.daoInterface.getClassLoader(), new Class[]{this.daoInterface}, this.proxy);
    }

    public void setProxy(MiniDaoHandler proxy) {
        this.proxy = proxy;
    }

    public void setDaoInterface(Class<T> daoInterface) {
        this.daoInterface = daoInterface;
    }
}
