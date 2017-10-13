//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package org.jeecgframework.poi.excel.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.FIELD})
public @interface Excel {
    String databaseFormat() default "yyyyMMddHHmmss";

    String exportFormat() default "";

    String format() default "";

    double height() default 10.0D;

    int imageType() default 1;

    String importFormat() default "";

    String suffix() default "";

    boolean isWrap() default true;

    int[] mergeRely() default {};

    boolean mergeVertical() default false;

    String name();

    boolean needMerge() default false;

    String orderNum() default "0";

    String[] replace() default {};

    String savePath() default "upload";

    int type() default 1;

    double width() default 10.0D;

    boolean isStatistics() default false;

    String dictTable() default "";

    String dicCode() default "";

    String dicText() default "";
}
