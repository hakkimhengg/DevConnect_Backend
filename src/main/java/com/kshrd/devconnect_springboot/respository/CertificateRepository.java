package com.kshrd.devconnect_springboot.respository;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface CertificateRepository {
    //Get all certificates by Developer ID
    //Insert a certificate when the recruiter evaluated a developer with the score 50% up of full score
}
