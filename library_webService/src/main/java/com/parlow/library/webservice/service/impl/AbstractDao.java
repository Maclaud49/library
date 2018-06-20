package com.parlow.library.webservice.service.impl;

import com.parlow.library.consumer.dao.contract.DaoFactory;

import javax.inject.Inject;

public abstract class AbstractDao {

    @Inject
    protected DaoFactory daoFactory;
}
