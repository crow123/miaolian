package com.miaolian.cn.mina;

import org.apache.mina.core.service.IoHandlerAdapter;
import org.apache.mina.core.session.IdleStatus;
import org.apache.mina.core.session.IoSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.net.InetSocketAddress;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

@Component
public class BossMinaHandle extends IoHandlerAdapter {

    private static final Logger LOGGER = LoggerFactory.getLogger(BossMinaHandle.class);

    private final int IDLE = 2;//单位秒

    @Autowired
    private MinaCodeFactory minaCodeFactory;

    public static Set<IoSession> sessions = Collections.synchronizedSet(new HashSet<IoSession>());

    public static ConcurrentHashMap<Long, IoSession> sessionsConcurrentHashMap = new ConcurrentHashMap<Long, IoSession>();

    @Override
    public void messageReceived(IoSession session, Object message)
            throws Exception {
        String str = message.toString();
        LOGGER.warn("客户端" + ((InetSocketAddress) session.getRemoteAddress()).getAddress().getHostAddress() + "连接成功！");

        session.setAttribute("type", message);
        String remoteAddress = ((InetSocketAddress) session.getRemoteAddress()).getAddress().getHostAddress();
        session.setAttribute("ip", remoteAddress);
        LOGGER.warn("服务器收到的消息是：" + str);
        session.write("welcome by he");
    }

    @Override
    public void sessionClosed(IoSession session) throws Exception {

        LOGGER.warn("sessionClosed.");
        session.closeOnFlush();
        sessions.remove(session);

        sessionsConcurrentHashMap.remove(session.getAttribute("id"));
        //super.sessionClosed(session);
    }

    @Override
    public void sessionIdle(IoSession session, IdleStatus status)
            throws Exception {
        LOGGER.warn("session idle, so disconnecting......");
        session.closeOnFlush();
        LOGGER.warn("disconnected.");
        //super.sessionIdle(session, status);
    }

    @Override
    public void messageSent(IoSession session, Object message) throws Exception {
        LOGGER.info("发送消息" + message.toString());
    }

    @Override
    public void sessionCreated(IoSession session) throws Exception {
        LOGGER.info("session is create"+session.getId());
        LOGGER.warn("remote client [" + session.getRemoteAddress().toString() + "] connected.");

        sessions.add(session);

        Long time = System.currentTimeMillis();
        session.setAttribute("id", time);
        sessionsConcurrentHashMap.put(time, session);

        //super.sessionCreated(session);
    }

    @Override
    public void sessionOpened(IoSession session) throws Exception {
        LOGGER.info("session is opened"+session.getId());
        session.getConfig().setIdleTime(IdleStatus.BOTH_IDLE, IDLE);
        //super.sessionOpened(session);
    }
}
