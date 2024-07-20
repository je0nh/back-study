package com.winterpear.shop.Membership;

import org.springframework.data.jpa.repository.JpaRepository;

import java.lang.reflect.Member;
import java.util.Optional;

public interface MembershipRepository extends JpaRepository<Membership, Integer> {
    Optional<Membership> findByUserid(String username);
}
