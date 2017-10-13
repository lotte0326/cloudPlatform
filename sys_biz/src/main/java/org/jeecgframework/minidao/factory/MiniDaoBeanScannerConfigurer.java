//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package org.jeecgframework.minidao.factory;

import java.lang.annotation.Annotation;
import org.jeecgframework.minidao.annotation.MiniDao;
import org.jeecgframework.minidao.aop.MiniDaoHandler;
import org.jeecgframework.minidao.aspect.EmptyInterceptor;
import org.jeecgframework.minidao.factory.MiniDaoClassPathMapperScanner;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.BeanDefinitionRegistryPostProcessor;
import org.springframework.beans.factory.support.GenericBeanDefinition;
import org.springframework.util.StringUtils;

public class MiniDaoBeanScannerConfigurer implements BeanDefinitionRegistryPostProcessor {
    private String basePackage;
    private Class<? extends Annotation> annotation = MiniDao.class;
    private String keyType = "origin";
    private boolean formatSql = false;
    private boolean showSql = false;
    private String dbType;
    private EmptyInterceptor emptyInterceptor;

    public MiniDaoBeanScannerConfigurer() {
    }

    public void postProcessBeanDefinitionRegistry(BeanDefinitionRegistry registry) throws BeansException {
        this.registerRequestProxyHandler(registry);
        MiniDaoClassPathMapperScanner scanner = new MiniDaoClassPathMapperScanner(registry, this.annotation);
        scanner.scan(StringUtils.tokenizeToStringArray(this.basePackage, ",; \t\n"));
    }

    public void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) throws BeansException {
    }

    private void registerRequestProxyHandler(BeanDefinitionRegistry registry) {
        GenericBeanDefinition jdbcDaoProxyDefinition = new GenericBeanDefinition();
        jdbcDaoProxyDefinition.setBeanClass(MiniDaoHandler.class);
        jdbcDaoProxyDefinition.getPropertyValues().add("formatSql", Boolean.valueOf(this.formatSql));
        jdbcDaoProxyDefinition.getPropertyValues().add("keyType", this.keyType);
        jdbcDaoProxyDefinition.getPropertyValues().add("showSql", Boolean.valueOf(this.showSql));
        jdbcDaoProxyDefinition.getPropertyValues().add("dbType", this.dbType);
        jdbcDaoProxyDefinition.getPropertyValues().add("emptyInterceptor", this.emptyInterceptor);
        registry.registerBeanDefinition("miniDaoHandler", jdbcDaoProxyDefinition);
    }

    public void setAnnotation(Class<? extends Annotation> annotation) {
        this.annotation = annotation;
    }

    public void setBasePackage(String basePackage) {
        this.basePackage = basePackage;
    }

    public void setDbType(String dbType) {
        this.dbType = dbType;
    }

    public void setFormatSql(boolean formatSql) {
        this.formatSql = formatSql;
    }

    public void setKeyType(String keyType) {
        this.keyType = keyType;
    }

    public void setShowSql(boolean showSql) {
        this.showSql = showSql;
    }

    public EmptyInterceptor getEmptyInterceptor() {
        return this.emptyInterceptor;
    }

    public void setEmptyInterceptor(EmptyInterceptor emptyInterceptor) {
        this.emptyInterceptor = emptyInterceptor;
    }
}
