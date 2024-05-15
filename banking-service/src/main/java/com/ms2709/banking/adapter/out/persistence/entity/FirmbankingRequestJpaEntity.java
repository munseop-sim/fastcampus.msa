package com.ms2709.banking.adapter.out.persistence.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 클래스 설명
 *
 * @author 심문섭
 * @version 1.0
 * @class RequestFirmbankingJpaEntity
 * @since 2024-05-15 오전 11:37
 */
@Entity
@Table(name = "firmbanking_request")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class FirmbankingRequestJpaEntity {

    @Id
    @GeneratedValue(strategy = jakarta.persistence.GenerationType.IDENTITY)
    private Long requestFirmbankingId;

    private String fromBankName;

    private String fromBankAccountNumber;

    private String toBankName;

    private String toBankAccountNumber;

    private Integer moneyAmount;

    private Integer firmBankingStatus; // 0: 요청, 1: 완료, 2: 실패

    private String uuid;

    public FirmbankingRequestJpaEntity(String fromBankName, String fromBankAccountNumber, String toBankName, String toBankAccountNumber, Integer moneyAmount, Integer firmBankingStatus, String uuid) {
        this.fromBankName = fromBankName;
        this.fromBankAccountNumber = fromBankAccountNumber;
        this.toBankName = toBankName;
        this.toBankAccountNumber = toBankAccountNumber;
        this.moneyAmount = moneyAmount;
        this.firmBankingStatus = firmBankingStatus;
        this.uuid = uuid;
    }

    @Override
    public String toString() {
        return "RequestFirmbankingJpaEntity{" +
                "requestFirmbankingId=" + requestFirmbankingId +
                ", fromBankName='" + fromBankName + '\'' +
                ", fromBankAccountNumber='" + fromBankAccountNumber + '\'' +
                ", toBankName='" + toBankName + '\'' +
                ", toBankAccountNumber='" + toBankAccountNumber + '\'' +
                ", moneyAmount=" + moneyAmount +
                ", firmBankingStatus=" + firmBankingStatus +
                ", uuid='" + uuid + '\'' +
                '}';
    }
}
