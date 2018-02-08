package com.miaolian.cn.mina;


import org.apache.mina.core.session.IoSession;

import java.util.Iterator;

/**
 *@function：模拟群发，在服务端和客户端都启动之后，可以运行此方法群发消息
 */
public class SendToAllTest {
	public static void main(String[] args) {
		System.out.println(BossMinaHandle.sessions.size());
		for (Iterator iterator = BossMinaHandle.sessions.iterator(); iterator.hasNext();) {
			IoSession session = (IoSession) iterator.next();
			session.write("发送系统消息");
		}
	}
}
