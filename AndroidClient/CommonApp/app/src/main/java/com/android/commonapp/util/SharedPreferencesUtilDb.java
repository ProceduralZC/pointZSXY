package com.android.commonapp.util;

import android.content.Context;
import android.content.SharedPreferences;

public class SharedPreferencesUtilDb {
	private SharedPreferences sp;
    private SharedPreferences.Editor editor;
    
    public static final String UID = "uid";//用户id
    public static final String ISLOG = "islog";//是否登陆
    public static final String username = "username";//用户名称
    public static final String nickname = "nickname";//用户昵称
    public static final String photo = "photo";//头像
    public static final String zhiwei = "zhiwei";//职位
    public static final String sex = "sex";//性别
    public static final String detail = "detail";//简介
    public static final String acount = "acount";//账号
    public static final String phone = "phone";//手机号
    public static final String addtime = "addtime";//时间
    public static final String access_token = "access_token";
    public static final String roleId = "roleId";
    public static final String email = "email";
    public static final String vip = "vip";


    public SharedPreferencesUtilDb(Context context){
        sp = context.getSharedPreferences("stu", Context.MODE_PRIVATE);
        editor = sp.edit();
    }

    public boolean getIsLog() {
        return sp.getBoolean(ISLOG, false);
    }
    public void setIsLog(boolean islog) {
        editor.putBoolean(ISLOG, islog);
        editor.commit();
    }
    
    public String getUid() {//用户id
        return sp.getString(UID, "");
    }
    public void setUid(String uid) {
        editor.putString(UID, uid);
        editor.commit();
    }
    
    public String getemail() {//邮箱
    	return sp.getString(email, "");
    }
    public void setemail(String emails) {
    	editor.putString(email, emails);
    	editor.commit();
    }
    public String getphoto() {//头像
    	return sp.getString(photo, "");
    }
    public void setphoto(String photos) {
    	editor.putString(photo, photos);
    	editor.commit();
    }
    public String getroleId() {//头像
    	return sp.getString(roleId, "");
    }
    public void setroleId(String roleIds) {
    	editor.putString(roleId, roleIds);
    	editor.commit();
    }

    public String getusername() {//用户昵称
    	return sp.getString(username, "");
    }
    public void setusername(String usernames) {
    	editor.putString(username, usernames);
    	editor.commit();
    }
    public String getNickname() {//用户昵称
    	return sp.getString(nickname, "");
    }
    public void setNickname(String nicknames) {
    	editor.putString(nickname, nicknames);
    	editor.commit();
    }

    public String getaccess_token() {//cookie
    	return sp.getString(access_token, "");
    }
    public void setaccess_token(String access_tokens) {
    	editor.putString(access_token, access_tokens);
    	editor.commit();
    }
    public String getzhiwei() {//cookie
    	return sp.getString(zhiwei, "");
    }
    public void setzhiwei(String zhiweis) {
    	editor.putString(zhiwei, zhiweis);
    	editor.commit();
    }
    public String getsex() {//sex
    	return sp.getString(sex, "");
    }
    public void setsex(String sexs) {
    	editor.putString(sex, sexs);
    	editor.commit();
    }
    public String getdetail() {//sex
    	return sp.getString(detail, "");
    }
    public void setdetail(String details) {
    	editor.putString(detail, details);
    	editor.commit();
    }
    public String getacount() {//sex
    	return sp.getString(acount, "");
    }
    public void setacount(String acounts) {
    	editor.putString(acount, acounts);
    	editor.commit();
    }
    public String getphone() {//sex
    	return sp.getString(phone, "");
    }
    public void setphone(String phones) {
    	editor.putString(phone, phones);
    	editor.commit();
    }
    public String getaddtime() {//sex
    	return sp.getString(addtime, "");
    }
    public void setaddtime(String addtimes) {
    	editor.putString(addtime, addtimes);
    	editor.commit();
    }
    public String getvip() {//sex
    	return sp.getString(vip, "");
    }
    public void setvip(String vips) {
    	editor.putString(vip, vips);
    	editor.commit();
    }

    
    public void clean() {
    	editor.clear();
    	editor.commit();
    }
}
