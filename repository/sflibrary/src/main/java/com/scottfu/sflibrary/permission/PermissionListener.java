package com.scottfu.sflibrary.permission;

import java.util.List;

/**
 * Created by fujindong on 2017/7/10.
 */

public interface PermissionListener {


    void onGranted();

    /**
     *
     * @param deniedPermissions 未授权的权限
     */
    void onDenied(List<String> deniedPermissions);
}
