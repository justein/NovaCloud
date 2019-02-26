package com.nova.sbnetty.protocol;

import lombok.Data;

import java.io.Serializable;

@Data
public class PingPongProtocol extends CustomProtocol implements Serializable {

    private static final long serialVersionUID = 4671171056588401542L;
    private long id;
    private String content;

    public PingPongProtocol(long id, String content) {
        this.id = id;
        this.content = content;
    }
}
