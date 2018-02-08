package com.miaolian.cn.mina;

import org.apache.mina.core.future.ConnectFuture;
import org.apache.mina.filter.codec.ProtocolCodecFilter;
import org.apache.mina.filter.codec.textline.TextLineCodecFactory;
import org.apache.mina.filter.logging.LoggingFilter;
import org.apache.mina.transport.socket.nio.NioSocketConnector;

import java.net.InetSocketAddress;
import java.nio.charset.Charset;

/**
 *@function������mina��ܵĿͻ��ˣ��ṹ��mina�����һֱ
 *@date��2016-9-27 ����03:45:30
 *@author:He.
 *@notice��
 */
public class ClientTest {
	public static void main(String[] args) throws InterruptedException {
		//�����ͻ���������. 
		NioSocketConnector connector = new NioSocketConnector();
		connector.getFilterChain().addLast("logger", new LoggingFilter());
		connector.getFilterChain().addLast("codec",
				new ProtocolCodecFilter(new TextLineCodecFactory(Charset.forName("utf-8")))); //���ñ��������
		connector.setHandler(new ClientHandler());//�����¼������� 
		ConnectFuture cf = connector.connect(new InetSocketAddress("127.0.0.1", 9003));//��������
		cf.awaitUninterruptibly();//�ȴ����Ӵ������ 
		cf.getSession().write("hello,��������");//������Ϣ
//		cf.getSession().closeOnFlush();
//		cf.getSession().getCloseFuture().awaitUninterruptibly();//�ȴ����ӶϿ� 
//		connector.dispose();

	}
}
