package com.example.demo.base;

import org.springframework.context.annotation.Condition;
import org.springframework.context.annotation.ConditionContext;
import org.springframework.core.env.Environment;
import org.springframework.core.type.AnnotatedTypeMetadata;

/**
 * @author zhuzw
 * @version <b>1.0.0</b>
 * @date 2019/9/2 16:45
 */
public class NonProdEnvCondition implements Condition {

    @Override
    public boolean matches(ConditionContext conditionContext, AnnotatedTypeMetadata annotatedTypeMetadata) {
        Environment environment = conditionContext.getEnvironment();
        String[] activeProfiles = environment.getActiveProfiles();
        return activeProfiles != null && activeProfiles.length > 0 && !BaseConstant.ENV_PROD.equals(activeProfiles[0]);
    }
}