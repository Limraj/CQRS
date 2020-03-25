/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.devwider.scada.system.user;

import com.devwider.scada.common.repository.Unique;
import com.devwider.scada.common.repository.UniqueDocumentary;
import java.util.List;
import java.util.TimeZone;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicLong;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 *
 * @author Kamil-Tomasz
 */
@Getter
@ToString
public class User implements Unique {

    @Setter
    private long id;
    private UUID uuid;
    private String username;
    private String password;
    private String email;
    private String phone;
    private boolean admin;
    private boolean disabled;
    private List<Integer> dataSourcePermissions;
    //private List<DataPointAccess> dataPointPermissions;
    private int selectedWatchList;
    private String homeUrl;
    private long lastLogin;
    private int receiveAlarmEmails;
    private boolean receiveOwnAuditEvents;
    private TimeZone timezone;
    private String zone;

    @Builder
    private User(long id, UUID uuid, String username, String password, String email, String phone,
                 boolean admin, boolean disabled, List<Integer> dataSourcePermissions, int selectedWatchList,
                 String homeUrl, long lastLogin, int receiveAlarmEmails, boolean receiveOwnAuditEvents,
                 TimeZone timezone, String zone) {
        this.id = id;
        this.uuid = uuid == null ? UUID.randomUUID() : uuid;
        this.username = username;
        this.password = password;
        this.email = email;
        this.phone = phone;
        this.admin = admin;
        this.disabled = disabled;
        this.dataSourcePermissions = dataSourcePermissions;
        this.selectedWatchList = selectedWatchList;
        this.homeUrl = homeUrl;
        this.lastLogin = lastLogin;
        this.receiveAlarmEmails = receiveAlarmEmails;
        this.receiveOwnAuditEvents = receiveOwnAuditEvents;
        this.timezone = timezone;
        this.zone = zone;
    }
}
