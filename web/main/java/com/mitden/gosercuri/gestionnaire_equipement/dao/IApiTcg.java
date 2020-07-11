package com.mitden.gosercuri.gestionnaire_equipement.dao;

import com.mitden.gosercuri.gestionnaire_equipement.dao.model.User;

import java.util.List;

public interface IApiTcg {
    public List<User> getUsers() throws Exception;
}
