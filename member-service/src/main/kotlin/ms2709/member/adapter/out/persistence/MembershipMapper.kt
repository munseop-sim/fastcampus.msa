package ms2709.member.adapter.out.persistence

import ms2709.member.adapter.out.persistence.entity.MembershipJpaEntity
import ms2709.member.domain.Membership
import ms2709.member.global.Mapper
import kotlin.reflect.KClass
import kotlin.reflect.full.primaryConstructor

/**
 *
 * 멤버십 정보를 DomainEntity로 변환하기 위한 Mapper
 *
 * @class MembershipMapper
 * @author 심문섭
 * @version 1.0
 * @since 2024-04-29 9:11PM
 */
interface MembershipMapper {
    fun mapToDomainEntity(entity:MembershipJpaEntity): Membership
}

@Mapper
class MembershipMapperImpl: MembershipMapper {
    private inline fun<reified T:Any> mapToVal(propName:String, args: Any?):T{
        if(args == null){
            throw IllegalArgumentException("$propName value must not be null")
        }

        val kClass: KClass<T> = T::class
        val constructor = kClass.primaryConstructor
            ?: throw IllegalArgumentException("Class doesn't have a primary constructor")

        return constructor.call(args)
    }

    override fun mapToDomainEntity(entity: MembershipJpaEntity): Membership {
        return Membership.create(
            mapToVal("membershipId",entity.membershipId?.toString()),
            mapToVal("name", entity.name ),
            mapToVal("email",entity.email ),
            mapToVal("address",entity.address ),
            mapToVal("isValid", entity.isValid),
            mapToVal("isCorp", entity.isCorp)
        )
    }
}
