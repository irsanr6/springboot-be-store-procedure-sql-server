package com.irsan.springbootsprestapi.service;

import com.irsan.springbootsprestapi.utils.Helpers;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author: Irsan Ramadhan
 * @email: irsan.ramadhan@iconpln.co.id
 */
@Service
public class MemberPerpusDetailServiceImpl implements MemberPerpusDetailService {

    @Resource
    private EntityManager entityManager;

    @Override
    public UserDetails loadUserByUsername(String usernameOrEmail) throws UsernameNotFoundException {
        Query query = entityManager.createNativeQuery("exec spLoadMemberPerpus ?1");
        query.setParameter(1, usernameOrEmail);
        List<?> data = Arrays.asList((Object[]) query.getSingleResult());
        return new User(data.get(0).toString(), data.get(1).toString(), new ArrayList<>());
    }
}
