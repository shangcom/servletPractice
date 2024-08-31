package hello.servletPractice.domain.member;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Map;

import static org.assertj.core.api.Assertions.*;

public class MemberRepositoryTest {

    MemberRepository memberRepository = MemberRepository.getInstance();

    @AfterEach
    void afterEach() {
        memberRepository.clearStore();
    }

    @Test
    void save() {
        // Arrange
        // TODO: Initialize test data
        Member member = new Member("hello", 20);

        // Act
        // TODO: Call the method to be tested
        Member savedMember = memberRepository.save(member);

        // Assert
        // TODO: Verify the results
        Member memberFoundbyId = memberRepository.findById(savedMember.getId());
        assertThat(memberFoundbyId).isEqualTo(savedMember);
    }

    @Test
    void findAll() {
        // Arrange
        // TODO: Initialize test data
        Member member1 = new Member("mem1", 10);
        Member member2 = new Member("mem2", 20);
        Member member3 = new Member("mem3", 30);

        memberRepository.save(member1);
        memberRepository.save(member2);
        memberRepository.save(member3);

        // Act
        // TODO: Call the method to be tested
        List<Member> memberRepositoryAll = memberRepository.findAll();

        // Assert
        // TODO: Verify the results
        assertThat(memberRepositoryAll.size()).isEqualTo(3);
        assertThat(memberRepositoryAll).contains(member1, member2, member3);
    }
}
