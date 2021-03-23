package com.serikov.passwordmanager.entity;

public enum Permission {
    PASSWORD_READ("password:read"),
    PASSWORD_WRITE("password:write");

    private final String permission;

    Permission(String permission) {
        this.permission = permission;
    }

    public String getPermission() {
        return permission;
    }
}
