package com.mirror.support;

import org.springframework.beans.factory.annotation.Autowired;

import com.mirror.db.DbTemplate;

/**
 * Service with db support.
 */
public abstract class AbstractDbService extends LoggerSupport {

    @Autowired
    protected DbTemplate db;
}
