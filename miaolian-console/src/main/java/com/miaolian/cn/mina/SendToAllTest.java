package com.miaolian.cn.mina;


import org.apache.mina.core.session.IoSession;

import java.util.Iterator;

/**
 *@function��ģ��Ⱥ�����ڷ���˺Ϳͻ��˶�����֮�󣬿������д˷���Ⱥ����Ϣ
 */
public class SendToAllTest {
	public static void main(String[] args) {
		System.out.println(BossMinaHandle.sessions.size());
		for (Iterator iterator = BossMinaHandle.sessions.iterator(); iterator.hasNext();) {
			IoSession session = (IoSession) iterator.next();
			session.write("����ϵͳ��Ϣ");
		}
	}
}
