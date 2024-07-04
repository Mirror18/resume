package com.itranswarp.scan.proxy;

import com.mirror.summer.annotation.Autowired;
import com.mirror.summer.annotation.Component;

@Component
public class InjectProxyOnPropertyBean {

    @Autowired
    public OriginBean injected;
}
