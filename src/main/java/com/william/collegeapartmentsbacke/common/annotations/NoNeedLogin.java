package com.william.collegeapartmentsbacke.common.annotations;

/**
 * @Author: William
 * @Description: TODO
 * @Date: 2024/6/21 16:54
 * @Version: 1.0
 */

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * [无需登录]
 */

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface NoNeedLogin {

}
