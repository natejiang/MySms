package com.tanker.thread;

import org.jivesoftware.smack.Chat;
import org.jivesoftware.smack.ChatManagerListener;
import org.jivesoftware.smack.Connection;
import org.jivesoftware.smack.ConnectionConfiguration;
import org.jivesoftware.smack.MessageListener;
import org.jivesoftware.smack.SmackConfiguration;
import org.jivesoftware.smack.XMPPConnection;
import org.jivesoftware.smack.XMPPException;
import org.jivesoftware.smack.packet.Message;


import android.os.Handler;


public class ClientThread implements Runnable{
	Handler handler;
	public ClientThread(Handler handler) {
		this.handler = handler;
	}
	@Override
	public void run() {
		// TODO Auto-generated method stub
		//建立连接
		ConnectionConfiguration config=new ConnectionConfiguration("222.35.41.78",5222);
		Connection con = new XMPPConnection(config);
		try {
			con.connect();
			con.login("test", "test");
		} catch (XMPPException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//接收消息
		SmackConfiguration.setLocalSocks5ProxyPort(17777);
		con.getChatManager().addChatListener(new ChatManagerListener(){

			@Override
			public void chatCreated(Chat chat, boolean arg1) {
				// TODO Auto-generated method stub
				chat.addMessageListener(new MessageListener(){

					@Override
					public void processMessage(Chat chat, Message message) {
						// TODO Auto-generated method stub
						android.os.Message msg = new android.os.Message();
						msg.what =  0x123;
						msg.obj = message.getBody();
						handler.sendMessage(msg);
						
					}


				});

			}

		});
	
	}

}
