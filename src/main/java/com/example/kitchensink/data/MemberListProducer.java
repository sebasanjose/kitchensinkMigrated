package com.example.kitchensink.data;

import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.RequestScope;

import com.example.kitchensink.model.Member;

import java.util.List;

@Component
public class MemberListProducer {

    @Autowired
    private MemberRepository memberRepository;

    private List<Member> members;

    // This method provides access to the members list, similar to @Produces and @Named in Jakarta
    public List<Member> getMembers() {
        return members;
    }

    // Spring's @EventListener replaces @Observes in Jakarta
    @EventListener
    public void onMemberListChanged(Member member) {
        retrieveAllMembersOrderedByName();
    }

    // @PostConstruct is supported in Spring and can be used directly
    @PostConstruct
    public void retrieveAllMembersOrderedByName() {
        members = memberRepository.findAllOrderedByName();
    }
}
