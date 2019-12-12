package com.android.commonapp.config;

import com.android.commonapp.CommonApplication;
import com.android.commonapp.models.LoginEntity;
import com.android.commonapp.util.SharedPreferencesUtil;

/**
 * @date: 2017/11/17.
 * @author: CHEN
 * @describe: 该SP是和用户信息相关的
 */

public class UserConfig extends SharedPreferencesUtil{

    private static final String FILE_NAME = "user_config";

    public static final String token = "token";

    private static final UserConfig INSTANCE = new UserConfig();

    private UserConfig() {
        super(CommonApplication.getContext(), FILE_NAME);
    }

    /**
     * 单例
     *
     * @return
     */
    public static UserConfig getInstance() {
        return INSTANCE;
    }

    public void saveUserData(LoginEntity loginEntity){
        put(token,loginEntity.getAccess_token());
    }

}
