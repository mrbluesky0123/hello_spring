package com.skcc.fwk.aop;

import com.skcc.fwk.entity.CommonArea;
import com.skcc.fwk.entity.Commons;
import com.skcc.fwk.entity.Transaction;
import com.skcc.start.repository.jpa.TransactionRepository;
import lombok.Builder;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.MDC;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.UUID;

@Aspect
@Component  // 이걸로 instance화 시킴
@Slf4j
public class Advice {

    @Autowired
    ApplicationContext applicationContext;

    @Autowired
    TransactionRepository repoTransaction;

    @Around("PointCutList.controllers()")
    public Object aroundLogController(ProceedingJoinPoint pjp) throws Throwable {

        Object obj;

        // Common area setting
        Commons commons = applicationContext.getBean("commons", Commons.class);
        commons.setArea(new CommonArea());
        //
        CommonArea area = commons.getArea();

        Date currentTime = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("yyMMdd", Locale.KOREA);
        area.setDate(formatter.format(currentTime));
        area.setTime(formatter.format(currentTime));

        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        area.setIp(request.getRemoteAddr());
        area.setUrl(request.getRequestURI());

        String gid = UUID.randomUUID().toString();
        area.setGid(gid);

        try {
            long start = System.currentTimeMillis();

            String methodName = pjp.getSignature().getName();
            // 모든 컨트롤러가 시작될때마다 이 메소드를 거친다.
            log.info("start: " + methodName);

            obj = pjp.proceed();

            long end = System.currentTimeMillis();

            log.info("end: " + (end - start) + "ms");

            area.setElapsed((end - start));

        } catch (Throwable e) {

            throw e;

        }
        // datagrip
        // 거래내역 DB 저장
        Transaction transaction = Transaction.builder()
                .gid(area.getGid())
                .day(area.getDate())
                .time(area.getTime())
                .ip(area.getIp())
                .status("200")
                .url(area.getUrl())
                .elapsed(area.getElapsed())
                .build();

        repoTransaction.save(transaction);

        return obj;

    }

    @Around("PointCutList.service()")
    // around, before, after
    public Object aroundLogService(ProceedingJoinPoint pjp) throws Throwable {

        String fileName = pjp.getSignature().getDeclaringType().getSimpleName();
        Date currentTime = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("yyMMdd", Locale.KOREA);
        fileName += '.' + formatter.format(currentTime);
        MDC.put("logServiceFileName", fileName);

        String bfFileName = MDC.get("logServiceFileName");
        Object obj = pjp.proceed();

        if(bfFileName == null)
            MDC.remove("logServiceFileName");
        else
            MDC.put("logServiceFileName", bfFileName);


        return obj;
    }

}
