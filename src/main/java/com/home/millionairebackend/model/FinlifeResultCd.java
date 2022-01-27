package com.home.millionairebackend.model;

import lombok.Getter;

import java.util.Arrays;
import java.util.Collections;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public enum FinlifeResultCd {
    CD000("000","정상","정상적으로 처리되는 경우"),
    CD010("010","미등록 인증키","등록되지 않은 인증키(auth)를 입력한 경우"),
    CD011("011","중지된 인증키","중지 처리된 인증키(auth)를 입력한 경우"),
    CD012("012","삭제된 인증키","삭제 처리된 인증키(auth)를 입력한 경우"),
    CD013("013","샘플 인증키","샘플 인증키(auth)를 입력한 경우"),
    CD020("020","일일검색 허용횟수 초과","개인의 경우로, 일일허용횟수를 초과하여 사용한 경우"),
    CD021("021","허용된 IP가 아닙니다.","단체의 경우로, 인증키 신청시와 다른 IP를 사용한 경우"),
    CD100("100","{요청변수ID} 누락","요청변수값을 입력하지 않은 경우"),
    CD101("101","{요청변수ID}의 부적절한 값","부적절한 요청변수값을 사용한 경우"),
    CD900("900","정의되지 않은 오류","Open API 서비스 내부 시스템 에러");

    @Getter
    private String code;
    @Getter
    private String message;
    @Getter
    private String desc;

    private static final Map<String, String> CODE_MAP = Collections.unmodifiableMap(
            Stream.of(values()).collect(Collectors.toMap(FinlifeResultCd::getCode, FinlifeResultCd::name)));

    FinlifeResultCd(String code, String message, String desc) {
        this.code = code;
        this.message = message;
        this.desc = desc;
    }

    public static FinlifeResultCd findMessage(final String code) {
        return FinlifeResultCd.valueOf(CODE_MAP.get(code));
    }


//    public FinlifeResultCd getMessage(String code) {
//        return Arrays.stream(FinlifeResultCd.values())
//                .filter(finlifeResultCd -> finlifeResultCd.)
//                .findAny();
//    }
//
//    public boolean hashcode(String code){
//        return
//    }
}
