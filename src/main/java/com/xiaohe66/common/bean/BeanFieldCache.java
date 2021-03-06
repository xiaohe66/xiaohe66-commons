package com.xiaohe66.common.bean;

import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;
import com.xiaohe66.common.ex.XhRuntimeException;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

/**
 * @author xiaohe
 * @time 2020.07.20 10:49
 */
public class BeanFieldCache {

    private static final Logger log = LoggerFactory.getLogger(BeanFieldCache.class);

    private final Cache<String, List<BeanField>> cache;

    public BeanFieldCache() {

        cache = CacheBuilder.newBuilder()
                .expireAfterAccess(30, TimeUnit.MINUTES)
                .build();
    }

    public List<BeanField> get(Class<?> beanCls) {

        try {
            return cache.get(beanCls.getName(), () -> reflectFieldList(beanCls));

        } catch (ExecutionException e) {
            throw new XhRuntimeException("get field cache error", e);
        }
    }

    private List<BeanField> reflectFieldList(Class<?> beanCls) {

        Field[] fields = beanCls.getDeclaredFields();

        if (fields.length == 0) {
            return Collections.emptyList();
        }

        List<BeanField> result = new ArrayList<>(fields.length);

        for (Field field : fields) {

            BeanFieldIgnore ignore = field.getAnnotation(BeanFieldIgnore.class);
            if (ignore != null) {
                continue;
            }

            BeanFieldName fieldName = field.getAnnotation(BeanFieldName.class);

            String name = null;
            if (fieldName != null) {
                String value = fieldName.value();
                if (StringUtils.isNotEmpty(value)) {
                    name = value;
                }
            }
            if (name == null) {
                name = field.getName();
            }

            field.setAccessible(true);

            BeanField beanField = new BeanField(field, name);
            result.add(beanField);
        }

        return result;
    }
}
