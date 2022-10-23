package com.irsan.springbootsprestapi.service;

import com.irsan.springbootsprestapi.utils.DBHandler;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.persistence.EntityManager;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * @author: Irsan Ramadhan
 * @email: irsan.ramadhan@iconpln.co.id
 */
@Service
public class MemberPerpusDetailServiceImpl extends DBHandler implements MemberPerpusDetailService {

    @Resource
    private EntityManager entityManager;

    @Override
    public UserDetails loadUserByUsername(String usernameOrEmail) throws UsernameNotFoundException {
        Object[] params = {usernameOrEmail};
        HashMap<String, String> data = getSingleResult("spLoadMemberPerpus", params);
        return new User(data.get("username"), data.get("password"), new ArrayList<>());
    }
}
