package com.app.ecommerce.dtos;

public class UserGroupPrivilegeDTO {
    private Long groupsIdUserGroup;
    private Long privilegesIdPrivilege;

    public Long getGroupsIdUserGroup() {
        return this.groupsIdUserGroup;
    }

    public void setGroupsIdUserGroup(Long groupsIdUserGroup) {
        this.groupsIdUserGroup = groupsIdUserGroup;
    }

    public Long getPrivilegesIdPrivilege() {
        return this.privilegesIdPrivilege;
    }

    public void setPrivilegesIdPrivilege(Long privilegesIdPrivilege) {
        this.privilegesIdPrivilege = privilegesIdPrivilege;
    }
}
