package com.example.myapplication;
// кароче эта хуйня отвечает за связь с json типа json нам пишет такую хуйню

//{
//    "message": "User registered successfully",
//    "success": true
//} а эта хуйня преобразует в понятную нам хуйню об ошибке или саксесфуле
public class RegisterResponseJSON {
    private String message;
    private boolean success;

    public String getMessage() {
        return message;
    }

    public boolean isSuccess() {
        return success;
    }

}
