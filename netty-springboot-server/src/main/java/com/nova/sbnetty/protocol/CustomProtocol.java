package com.nova.sbnetty.protocol;

import lombok.Data;

@Data
public  class CustomProtocol {

    private long id;
    private String content;
    private String version;

    public CustomProtocol() {
    }

    public CustomProtocol(long id, String content) {
        this.id = id;
        this.content = content;
    }
}
