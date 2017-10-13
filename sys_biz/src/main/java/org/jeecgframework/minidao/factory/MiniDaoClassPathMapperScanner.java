//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package org.jeecgframework.minidao.factory;

import java.lang.annotation.Annotation;
import java.util.Arrays;
import java.util.Iterator;
import java.util.Set;
import org.apache.log4j.Logger;
import org.jeecgframework.minidao.annotation.MiniDao;
import org.jeecgframework.minidao.aop.MiniDaoHandler;
import org.jeecgframework.minidao.factory.MiniDaoBeanFactory;
import org.springframework.beans.factory.annotation.AnnotatedBeanDefinition;
import org.springframework.beans.factory.config.BeanDefinitionHolder;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.GenericBeanDefinition;
import org.springframework.context.annotation.ClassPathBeanDefinitionScanner;
import org.springframework.core.type.filter.AnnotationTypeFilter;

public class MiniDaoClassPathMapperScanner extends ClassPathBeanDefinitionScanner {
    private static final Logger logger = Logger.getLogger(MiniDaoHandler.class);

    public MiniDaoClassPathMapperScanner(BeanDefinitionRegistry registry, Class<? extends Annotation> annotation) {
        super(registry, false);
        this.addIncludeFilter(new AnnotationTypeFilter(annotation));
        if(!MiniDao.class.equals(annotation)) {
            this.addIncludeFilter(new AnnotationTypeFilter(MiniDao.class));
        }

    }

    public Set<BeanDefinitionHolder> doScan(String... basePackages) {
        Set beanDefinitions = super.doScan(basePackages);
        if(beanDefinitions.isEmpty()) {
            logger.warn("No Dao interface was found in \'" + Arrays.toString(basePackages) + "\' package. Please check your configuration.");
        }

        GenericBeanDefinition definition;
        for(Iterator var5 = beanDefinitions.iterator(); var5.hasNext(); definition.setBeanClass(MiniDaoBeanFactory.class)) {
            BeanDefinitionHolder holder = (BeanDefinitionHolder)var5.next();
            definition = (GenericBeanDefinition)holder.getBeanDefinition();
            definition.getPropertyValues().add("proxy", this.getRegistry().getBeanDefinition("miniDaoHandler"));
            definition.getPropertyValues().add("daoInterface", definition.getBeanClassName());
            if(logger.isInfoEnabled()) {
                logger.info("register minidao name is { " + definition.getBeanClassName() + " }");
            }
        }

        return beanDefinitions;
    }

    protected boolean isCandidateComponent(AnnotatedBeanDefinition beanDefinition) {
        return beanDefinition.getMetadata().isInterface() && beanDefinition.getMetadata().isIndependent();
    }
}
