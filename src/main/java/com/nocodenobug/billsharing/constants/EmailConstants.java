package com.nocodenobug.billsharing.constants;

public enum EmailConstants {
      SUBJECT(0,"Verify NoCodeNoBug account"),
    ;
    private final String subject;
    EmailConstants(int i, String s) {
        this.subject=s;
    }
}
