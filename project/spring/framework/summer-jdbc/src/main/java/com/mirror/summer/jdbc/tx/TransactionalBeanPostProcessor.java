package com.mirror.summer.jdbc.tx;

import com.mirror.summer.annotation.Transactional;
import com.itranswarp.summer.aop.AnnotationProxyBeanPostProcessor;

public class TransactionalBeanPostProcessor extends AnnotationProxyBeanPostProcessor<Transactional> {

}
