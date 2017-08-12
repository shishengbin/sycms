package com.sy.service;

import java.security.Key;

import com.sy.entity.User;

import org.apache.shiro.codec.Base64;
import org.apache.shiro.codec.Hex;
import org.apache.shiro.crypto.AesCipherService;
import org.apache.shiro.crypto.RandomNumberGenerator;
import org.apache.shiro.crypto.SecureRandomNumberGenerator;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

/**
 * <p>User: Zhang Kaitao
 * <p>Date: 14-1-28
 * <p>Version: 1.0
 */
@Service
public class PasswordHelper {

    private RandomNumberGenerator randomNumberGenerator = new SecureRandomNumberGenerator();

    @Value("${password.algorithmName}")
    private String algorithmName = "md5";
    @Value("${password.hashIterations}")
    private int hashIterations = 2;

    public void setRandomNumberGenerator(RandomNumberGenerator randomNumberGenerator) {
        this.randomNumberGenerator = randomNumberGenerator;
    }

    public void setAlgorithmName(String algorithmName) {
        this.algorithmName = algorithmName;
    }

    public void setHashIterations(int hashIterations) {
        this.hashIterations = hashIterations;
    }

    public void encryptPassword(User user) {

        user.setSalt(randomNumberGenerator.nextBytes().toHex());

        String newPassword = new SimpleHash(
                algorithmName,
                user.getPassword(),
                ByteSource.Util.bytes(user.getCredentialsSalt()),
                hashIterations).toHex();

        user.setPassword(newPassword);
    }
    
    
    public static void main(String[] args){
    	//AES算法
    	AesCipherService acs=new AesCipherService();
    	acs.setKeySize(128);//设置key长度
    	//生成key
    	Key key=acs.generateNewKey();
    	String s="hello";
    	//加密
    	String encrpText=acs.encrypt(s.getBytes(), key.getEncoded()).toHex();
    	System.out.println(encrpText);
    	
    	//解密
    	String text2=new String(acs.decrypt(Hex.decode(encrpText), key.getEncoded()).getBytes());
    	System.out.println(text2);
    	
    	//System.out.println(T(Base64.decode('4AvVhmFLUs0KTA3Kprsdag==')));
    }
    
    
}
