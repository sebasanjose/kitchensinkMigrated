package com.example.kitchensink.rest;

import com.example.kitchensink.data.MemberRepository;
import jakarta.persistence.NoResultException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.example.kitchensink.model.Member;
import com.example.kitchensink.service.MemberRegistration;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
import jakarta.validation.Valid;
import jakarta.validation.ValidationException;
import java.util.*;

@RestController
@RequestMapping("/members")
@Validated
public class MemberResourceRESTService {

    private static final Logger log = LoggerFactory.getLogger(MemberResourceRESTService.class);

    @Autowired
    private MemberRepository repository;

    @Autowired
    private MemberRegistration registration;

    @GetMapping
    public List<Member> listAllMembers() {
        return repository.findAllOrderedByName();
    }

    @GetMapping("/{id:[0-9][0-9]*}")
    public ResponseEntity<Member> lookupMemberById(@PathVariable("id") Long id) {
        Optional<Member> member = repository.findById(id);
        return member.map(value -> new ResponseEntity<>(value, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PostMapping
    public ResponseEntity<Object> createMember(@Valid @RequestBody Member member) {

        ResponseEntity<Object> responseEntity;

        try {
            registration.register(member);

            responseEntity = new ResponseEntity<>(HttpStatus.OK);
        } catch (ConstraintViolationException ce) {
            responseEntity = createViolationResponse(ce.getConstraintViolations());
        } catch (ValidationException e) {
            Map<String, String> responseObj = new HashMap<>();
            responseObj.put("email", "Email taken");
            responseEntity = new ResponseEntity<>(responseObj, HttpStatus.CONFLICT);
        } catch (Exception e) {
            Map<String, String> responseObj = new HashMap<>();
            responseObj.put("error", e.getMessage());
            responseEntity = new ResponseEntity<>(responseObj, HttpStatus.BAD_REQUEST);
        }

        return responseEntity;
    }

    private ResponseEntity<Object> createViolationResponse(Set<ConstraintViolation<?>> violations) {
        log.debug("Validation completed. Violations found: {}", violations.size());

        Map<String, String> responseObj = new HashMap<>();

        for (ConstraintViolation<?> violation : violations) {
            responseObj.put(violation.getPropertyPath().toString(), violation.getMessage());
        }

        return new ResponseEntity<>(responseObj, HttpStatus.BAD_REQUEST);
    }


}
