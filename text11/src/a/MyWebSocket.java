 package a;

import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

import javax.websocket.OnClose;
import javax.websocket.OnError;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;

//��ע������ָ��һ��url
@ServerEndpoint("/websocket")
public class MyWebSocket {

	//Set���������ÿ���ͻ��˶�Ӧ��MyWebSocket������Ҫʵ�ַ�����뵥һ�ͻ���ͨ�ŵĻ�������ʹ��Map����ţ�����Key����Ϊ�û���ʶ
	private static Set<MyWebSocket> set = new HashSet<MyWebSocket>();

	// ��ĳ���ͻ��˵����ӻỰ����Ҫͨ���������ͻ��˷�������
	private Session session;
	private static int num=0;
	/**
	 * ���ӽ����ɹ����õķ���
	 * 
	 * @param session
	 *            ��ѡ�Ĳ�����sessionΪ��ĳ���ͻ��˵����ӻỰ����Ҫͨ���������ͻ��˷�������
	 */
	@OnOpen
	public void onOpen(Session session) {
		this.session = session;
		
		set.add(this); // ����set��
		num++;
		System.out.println("�������Ӽ��룡"+num);
		for (MyWebSocket item : set) {
			try {
				item.session.getBasicRemote().sendText(String.valueOf(num));
			} catch (IOException e) {
				e.printStackTrace();
				continue;
			}
		}
	}

	/**
	 * ���ӹرյ��õķ���
	 */
	@OnClose
	public void onClose() {
		set.remove(this); // ��set��ɾ��
		num--;
		System.out.println("��һ���ӹرգ�"+num);
		for (MyWebSocket item : set) {
			try {
				item.session.getBasicRemote().sendText(String.valueOf(num));
			} catch (IOException e) {
				e.printStackTrace();
				continue;
			}
		}
	}

	/**
	 * �յ��ͻ�����Ϣ����õķ���
	 * 
	 * @param message
	 *            �ͻ��˷��͹�������Ϣ
	 * @param session
	 *            ��ѡ�Ĳ���
	 */
	@OnMessage
	public void onMessage(String message, Session session) {
		System.out.println("���Կͻ��˵���Ϣ:" + message);

		// Ⱥ����Ϣ
		for (MyWebSocket item : set) {
			try {
				item.session.getBasicRemote().sendText(message);
			} catch (IOException e) {
				e.printStackTrace();
				continue;
			}
		}
	}

	/**
	 * ��������ʱ����
	 * 
	 * @param session
	 * @param error
	 */
	@OnError
	public void onError(Session session, Throwable error) {
		System.out.println("��������");
		error.printStackTrace();
	}

}