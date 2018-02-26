package com.skcc.fwk.entity;

import com.skcc.fwk.entity.CommonArea;
import lombok.Data;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.springframework.web.context.WebApplicationContext;

@Data
@Component

public class Commons {
    
    CommonArea area;

    
}
