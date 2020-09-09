package kr.com.ticketpass.util

import java.security.MessageDigest

fun isValidateEmail(email: String): Boolean {
    //정규표현식 로직
    val emailRegExp = "^[a-zA-Z0-9._%^-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,6}$".toRegex()
    val matchResult = emailRegExp.matches(email)
    return matchResult
}

fun isValidatePassword(password: String): Boolean {
    //정규표현식 로직
    val passwordRegExp = "^(?=.*[0-9])(?=.*[a-z])(?=.*[0-9]).{8,20}$".toRegex()
    val matchResult = passwordRegExp.matches(password)
    return matchResult
}

fun isValidateEmailAndNumber(email: String, number: Int) {
    //이메일과 인증번호 검사
    //통과하면 이메일에 이메일 값 저장
}

fun isValidatePasswordAndRePassword(password: String, rePassword: String): Boolean {
    //비밀번호 정규식에 맞는지
    //비밀번호와 비밀번호 확인 일치 여부
    if (isValidatePassword(password) && isValidatePassword(rePassword)) {
        return password == rePassword
    } else return false
}


class HashingPassword {
    fun hashString(input: String, algorithm: String): String {
        return MessageDigest
            .getInstance(algorithm)
            .digest(input.toByteArray())
            .fold("", { str, it -> str + "%02x".format(it) })
    }
}

fun String.md5(): String {
    return HashingPassword().hashString(this, "MD5")
}

fun String.sha256(): String {
    return HashingPassword().hashString(this, "SHA-256")
}

