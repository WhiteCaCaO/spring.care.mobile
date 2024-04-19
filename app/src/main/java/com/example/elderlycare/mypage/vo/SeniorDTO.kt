package com.example.elderlycare.mypage.vo

data class SeniorDTO(

    // user 정보
    var userId: Long = 0L,
    val roleStr: String = "",
    val email: String = "",
    var name: String = "",
    var address: String? = null,
//    var password: String, // 주석 처리된 필드
    var phoneNumber: String? = null,
    var country: String? = null,
    var gender: String? = null,
    var image: String? = null,

    // senior 정보
    var seniorId: Long = 0L,
    var serniorName: String? = null,
    var health: String? = null,
    var requirements: String? = null,
    var hasGuardian: Boolean = true,

    // guardian 정보
    var guardianId: Long = 0L,
    var guardianName: String? = null,
    var guardianPhoneNumber: String? = null,
    var relationship: String? = null
)
