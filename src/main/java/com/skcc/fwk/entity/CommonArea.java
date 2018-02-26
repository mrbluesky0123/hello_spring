package com.skcc.fwk.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CommonArea {

    String gid;
    String date;
    String time;
    String url;
    String ip;
    long elapsed;


}
