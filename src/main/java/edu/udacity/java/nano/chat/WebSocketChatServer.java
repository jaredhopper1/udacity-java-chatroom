package edu.udacity.java.nano.chat;


import com.alibaba.fastjson.JSON;
import org.springframework.stereotype.Component;


import javax.websocket.*;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;



@Component
@ServerEndpoint("/chat/{username}")
public class WebSocketChatServer {


    private static Map<String, Session> onlineSessions = new ConcurrentHashMap<>();
    private static HashMap<String, String> users = new HashMap<>();

    public static void sendMessageToAll(String msg) {

        onlineSessions.forEach((id, session) -> {
            try{
                session.getBasicRemote().sendText(msg);
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }


    @OnOpen
    public void onOpen(Session session, @PathParam("username") String user) {

        onlineSessions.put(session.getId(), session);
        // Getting username through path param.
        users.put(session.getId(), user);
        sendMessageToAll(Message.strToJson("Entered the chat (system message)", user, onlineSessions.size(), "SPEAK"));
    }


    @OnMessage
    public void onMessage(Session session, String jsonStr) {
        //TODO: add send message.
        Message message = JSON.parseObject(jsonStr, Message.class);
        sendMessageToAll(Message.strToJson(message.getMsg(), message.getUsername(), onlineSessions.size(), "SPEAK"));
    }


    @OnClose
    public void onClose(Session session, @PathParam("username") String user) {

        onlineSessions.remove(session.getId());
        try {
            sendMessageToAll(Message.strToJson("left the chat (system message)", user, onlineSessions.size(), "SPEAK"));
        } catch(Exception e) {
            System.out.println("Error");
        }
    }



    @OnError
    public void onError(Session session, Throwable error) {
        error.printStackTrace();
    }

}