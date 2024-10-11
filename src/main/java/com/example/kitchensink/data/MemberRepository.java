package com.example.kitchensink.data;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.example.kitchensink.model.Member;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import org.springframework.data.mongodb.repository.MongoRepository;


import java.util.List;

@Repository
public interface MemberRepository extends MongoRepository<Member, Long> {

    // Custom method using a query to find a member by email
    @Query("SELECT m FROM Member m WHERE m.email = :email")
    Member findByEmail(@Param("email") String email);

    // Custom method to find all members ordered by name
    @Query("SELECT m FROM Member m ORDER BY m.name ASC")
    List<Member> findAllOrderedByName();
}
