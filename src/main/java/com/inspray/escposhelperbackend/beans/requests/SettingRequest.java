package com.inspray.escposhelperbackend.beans.requests;

import lombok.Data;

@Data
public class SettingRequest {
    private String origin;
    private String printerUrl;
    private String ServerUrl;
}
