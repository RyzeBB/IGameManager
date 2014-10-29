package com.igame.security.manager;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataAccessException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.igame.commons.util.SecretUtil;
import com.igame.security.entity.SecRole;
import com.igame.security.entity.SecUser;
import com.igame.security.service.SecUserService;

/**
 * 根据登录信息 获取用户信息
 * @author Allen
 *
 */
@Service("myUserDetailService")
public class MyUserDetailService implements UserDetailsService {
	private static final Logger logger = LoggerFactory.getLogger(MyUserDetailService.class);
	private SecUserService secUserService;
    public UserDetails loadUserByUsername(final String username) throws UsernameNotFoundException, DataAccessException {
    	try{
    	logger.debug(" =============== loadUserByUsername  start  =========登陆账号 "+username);
    	SecUser secUser = secUserService.getUserByName(username);
    	boolean isSuperAdmin = false;
    	if(secUser!=null){
    		 List<GrantedAuthority> auths = new ArrayList<GrantedAuthority>();
    		 List<SecRole> secRoles= secUserService.getRolesByUserId(secUser.getId());
    	        for(SecRole sr : secRoles){
    	        	if("ROLE_ADMIN".equals(sr.getCode())){
    	        		isSuperAdmin = true;
    	        	}
    		        GrantedAuthority auth = new SimpleGrantedAuthority(sr.getCode());
    		        auths.add(auth);
    	        }
    	        MyUser myUser = new MyUser(username,  SecretUtil.md5Encryption(secUser.getPassword()), auths);
    	        myUser.setUserId(secUser.getId());
    	        myUser.setSuperAdmin(isSuperAdmin);
    	        myUser.setSecUser(secUser);
    	        
    	        logger.debug(" =============== loadUserByUsername  end  =========登陆成功  登陆账号 "+username);
    	        return myUser;
    	}else{
    		logger.debug(" =============== loadUserByUsername  用户不存=========");
    		throw new UsernameNotFoundException("user is not exist!!( 用户不存... )");
    	}
    	}catch(UsernameNotFoundException e){
    		logger.debug(" =============== loadUserByUsername UsernameNotFoundException  error "+e);
    		throw e;
    	}catch(DataAccessException e){
    		logger.debug(" =============== loadUserByUsername DataAccessException error "+e);
    		throw e;
    	}
    	
    }
	
	@Resource
	public void setSecUserService(SecUserService secUserService) {
		this.secUserService = secUserService;
	}
    
}