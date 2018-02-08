package com.miaolian.cn.mina;

import org.apache.log4j.Logger;
import org.apache.mina.core.service.IoHandlerAdapter;
import org.apache.mina.core.session.IdleStatus;
import org.apache.mina.core.session.IoSession;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

public class ClientHandler extends IoHandlerAdapter{
	private final Logger LOGGER = Logger.getLogger(ClientHandler.class);

	public static Set<IoSession> sessions = Collections.synchronizedSet(new HashSet<IoSession>());

	public static ConcurrentHashMap<Long, IoSession> sessionsConcurrentHashMap = new ConcurrentHashMap<Long, IoSession>();

	@Override
	public void messageReceived(IoSession session, Object message) throws Exception {
		// TODO Auto-generated method stub
//		System.out.println("message :"+message);
		LOGGER.warn("客户端收到消息：" + message);
		if (message.toString().equals("1111")) {
			//收到心跳包
			LOGGER.warn("收到心跳包");
			session.write("1112");
		}
		LOGGER.warn(session);
	}

	@Override
	public void messageSent(IoSession session, Object message) throws Exception {
		// TODO Auto-generated method stub
		super.messageSent(session, message);
	}

	@Override
	public void sessionClosed(IoSession session) throws Exception {
		// TODO Auto-generated method stub
		super.sessionClosed(session);
	}

	@Override
	public void sessionCreated(IoSession session) throws Exception {
		LOGGER.info("session is create"+session.getId());
		LOGGER.warn("remote client [" + session.getRemoteAddress().toString() + "] connected.");
		sessions.add(session);
		Long time = System.currentTimeMillis();
		session.setAttribute("id", time);
		sessionsConcurrentHashMap.put(time, session);
	}

	@Override
	public void sessionIdle(IoSession session, IdleStatus status) throws Exception {
		// TODO Auto-generated method stub
		super.sessionIdle(session, status);
	}

	@Override
	public void sessionOpened(IoSession session) throws Exception {
		// TODO Auto-generated method stub
		super.sessionOpened(session);
	}
}
