package com.itranswarp.scan.proxy;

import com.mirror.summer.annotation.Autowired;
import com.mirror.summer.annotation.Component;

@Component
public class InjectProxyOnConstructorBean {

    public final OriginBean injected;

    public InjectProxyOnConstructorBean(@Autowired OriginBean injected) {
        this.injected = injected;
    }
}
