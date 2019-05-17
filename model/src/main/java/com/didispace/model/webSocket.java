package com.didispace.model;

import java.io.IOException;
import java.util.concurrent.CopyOnWriteArraySet;

import javax.websocket.OnClose;
import javax.websocket.OnError;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;

import org.springframework.stereotype.Component;

/**
 * 双工通信websocket工具类
 * @author wwl
 *
 *
 *该代码链接： https://www.cnblogs.com/ttjsndx/p/9268800.html
 */

/*使用@ServerEndpoint(value="/***") 时会自动注入返回类型为ServerEndpointExporter的bean。
等同于继承了ServerEndpointExporter类。
继承类后再重写onOpen、onClose、onMessage、onError方法*/

@ServerEndpoint(value="/webSocket")
@Component
public class webSocket{
    //静态变量，用来记录当前在线连接数。应该把它设计成线程安全的。
    private static int onlineCount = 0;

    //concurrent包的线程安全Set，用来存放每个客户端对应的MyWebSocket对象。
    private static CopyOnWriteArraySet<webSocket> webSocketSet = new CopyOnWriteArraySet<webSocket>();
    
    //与某个客户端的连接会话，需要通过它来给客户端发送数据
    private Session session;

    /**
     * 连接建立成功调用的方法*/
    @OnOpen
    public void onOpen(Session session) {
        this.session = session;
        webSocketSet.add(this);     //加入set中
        addOnlineCount();           //在线数加1
        System.out.println("有新连接加入！当前在线人数为" + getOnlineCount());
        try {
            sendMessage("您是第" + getOnlineCount() + "个双工通信的用户！");
        } catch (IOException e) {
            System.out.println("IO异常");
        }
    }

    /**
     * 连接关闭调用的方法
     */
    @OnClose
    public void onClose() {
        webSocketSet.remove(this);  //从set中删除
        subOnlineCount();           //在线数减1
        System.out.println("有一连接关闭！当前在线人数为" + getOnlineCount());
    }

    /**
     * 收到客户端消息后调用的方法
     *
     * @param message 客户端发送过来的消息*/
    @OnMessage
    public void onMessage(String message, Session session) {
        System.out.println("来自客户端的消息:" + message);
        //发送消息
        try {
        	
            session.getBasicRemote().sendText(message);

        } catch (IOException e) {
            e.printStackTrace();
        };
        
     
	   /*群发消息	 
	    * for (webSocket item : webSocketSet) {
	         try {
	             item.sendMessage(message);
	         } catch (IOException e) {
	             continue;
	         }
	     };*/
    }

    /**
     * 发生错误时调用
     */
    @OnError
    public void onError(Session session, Throwable error) {
        System.out.println("发生错误");
        error.printStackTrace();
    }

    /**
     * 发送消息
     * @param message
     * @throws IOException
     */
    public void sendMessage(String message) throws IOException {
        this.session.getBasicRemote().sendText(message);
        //this.session.getAsyncRemote().sendText(message);
    }


    /**
     * 群发自定义消息
     * */
    public static void sendInfo(String message) throws IOException {
        for (webSocket item : webSocketSet) {
            try {
                item.sendMessage(message);
            } catch (IOException e) {
                continue;
            }
        }
    }

    public static synchronized int getOnlineCount() {
        return onlineCount;
    }

    public static synchronized void addOnlineCount() {
    	webSocket.onlineCount++;
    }

    public static synchronized void subOnlineCount() {
    	webSocket.onlineCount--;
    }
}