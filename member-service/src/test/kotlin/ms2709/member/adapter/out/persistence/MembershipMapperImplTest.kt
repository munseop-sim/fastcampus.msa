package ms2709.payservice.member.adapter.out.persistence

import ms2709.payservice.member.adapter.out.persistence.MembershipMapper
import ms2709.payservice.member.adapter.out.persistence.entity.MembershipJpaEntity
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest

/**
 *
 * 클래스 설명
 *
 * @class MembershipMapperImplTest
 * @author 심문섭
 * @version 1.0
 * @since 2024-04-29 9:35 PM
 */
@SpringBootTest
class MembershipMapperImplTest @Autowired constructor(
    private val sut: MembershipMapper
){
    @DisplayName("MembershipJpaEntity -> DomainEntity 매핑 테스트")
    @Test
    fun shouldCanMembershipJpaEntityToDomainEntity(){
        //given
        val entity = MembershipJpaEntity(
            membershipId = -1L,
            name = "name",
            email = "email",
            address = "address",
            isValid = true,
            isCorp = true
        )

        //when
        val result = sut.mapToDomainEntity(entity)

        //then
        assertThat(result).isNotNull
        assertThat(result.membershipId).isEqualTo(entity.membershipId.toString())
    }
}