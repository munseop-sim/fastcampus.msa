//package ms2709.member.application.service
//
//import ms2709.payservice.member.application.port.`in`.RegisterMembershipCommand
//import ms2709.payservice.member.application.service.RegisterMembershipService
//import org.assertj.core.api.Assertions.assertThat
//import org.junit.jupiter.api.DisplayName
//import org.junit.jupiter.api.Test
//import org.springframework.beans.factory.annotation.Autowired
//import org.springframework.boot.test.context.SpringBootTest
//import org.springframework.transaction.annotation.Transactional
//
///**
// *
// * 클래스 설명
// *
// * @class RegisterMembershipServiceTest
// * @author 심문섭
// * @version 1.0
// * @since 2024-04-29 10:27 PM
// */
//@SpringBootTest
//@Transactional
//class RegisterMembershipServiceTest @Autowired constructor(
//    private val sut: RegisterMembershipService
//){
//
//    @DisplayName("멤버십을 등록할 수 있다.")
//    @Test
//    fun shouldRegisterMembership(){
//        // given
//        val command = RegisterMembershipCommand(
//            name = "심문섭",
//            email = "이메일",
//            address = "주소",
//            isCorp = true,
//            isValid = true
//        )
//
//        // when
//        val result = sut.registerMembership(command)
//
//        //then
//        assertThat(result).isNotNull
//        assertThat(result.membershipId).isNotNull()
//        assertThat(result.name).isEqualTo(command.name)
//
//    }
//}