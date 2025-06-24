package com.oracle.oBootMybatis01.handler;

import java.util.HashMap;
import java.util.Iterator;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

@Component
public class SocketHandler extends TextWebSocketHandler {
	// 웹소켓 세션을 저장할 맵
	//  - d5518624-0673-24c7-11a0-5699cfe3e9a3, wss1
	//  - 77021e69-1a55-8a51-5096-3df7c532f88b, wss2
	HashMap<String, WebSocketSession> sessionMap = new HashMap<>();
	
	// 웹소켓 세션 ID와 Member를 저장할 맵
	//  - d5518624-0673-24c7-11a0-5699cfe3e9a3, "사용자1"
	//  - 77021e69-1a55-8a51-5096-3df7c532f88b, "사용자2"
	HashMap<String, String> sessionUserMap = new HashMap<>();
	
	// 웹소켓 세션 ID와 Member를 저장할 JSONObject : 통신에 사용되는 JSONObject
	// - type : "userSave"/"getId"/"message"/"userDelete"
	// - sessionId : "d5518624-0673-24c7-11a0-5699cfe3e9a3"
	// - userName : "사용자1"
	// -- 조건부 데이터(type:userSave/userDelete)
	// --- saveStatus : "Create"/"Delete"
	// -- 조건부 데이터(type:message)
	// --- yourName : "ALL"/"77021e69-1a55-8a51-5096-3df7c532f88b"
	// --- msg : "메시지"
	JSONObject jsonUser = null;

//	@SuppressWarnings("unchecked")//경고 삭제
//	 - 이건 컴파일러가 일반적으로 경고하는 내용 중	"이건 하지마" 하고 제외시킬 때 쓰임
//	 - 따라서 어떤 경고를 제외시킬지 옵션
//	1. all : 모든 경고를 억제
//	2. cast : 캐스트 연산자 관련 경고 억제
//	3. dep-ann : 사용하지 말아야 할 주석 관련 경고 억제
//	4. deprecation : 사용하지 말아야 할 메소드 관련 경고 억제
//	5. fallthrough : switch문에서의 break 누락 관련 경고 억제
//	6. finally : 반환하지 않는 finally 블럭 관련 경고 억제
//	7. null : null 분석 관련 경고 억제
//	8. rawtypes : 제네릭을 사용하는 클래스 매개 변수가 불특정일 때의 경고 억제
//	9. unchecked : 검증되지 않은 연산자 관련 경고 억제
//	10. unused : 사용하지 않는 코드 관련 경고 억제
	
	// 1. 웹소켓 연결이 되면 동작
	@SuppressWarnings("unchecked")
	@Override
	public void afterConnectionEstablished(WebSocketSession session) throws Exception {
		// TODO Auto-generated method stub
		super.afterConnectionEstablished(session);
		System.out.println("SocketHandler afterConnectionEstablished S");
		
		//연결된 소켓(클라이언트)를 맵에 저장
		sessionMap.put(session.getId(), session);
		
		// 메시지 생성
		// - type=getId : 신규 사용자 접속
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("type", "getId");
		jsonObject.put("sessionId", session.getId());
		System.out.println("SocketHandler afterConnectionEstablished 1 jsonObject : " + jsonObject.toJSONString());
		
		// 메시지 전송
		// - 신규 사용자에게 자신의 정보를 전송
		session.sendMessage(new TextMessage(jsonObject.toJSONString()));
	}

	// 2. handleTextMessage 메소드는 메시지를 수신하면 실행
	@Override
	protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
		// TODO Auto-generated method stub
		System.out.println("SocketHandler handleTextMessage S");
		
//		super.handleTextMessage(session, message);
		
		//메시지 수신
		String msg = message.getPayload();
		System.out.println("SocketHandler handleTextMessage 1 msg : " + msg);
		
		JSONObject jsonObject = jsonToObjectParse(msg);
		//type에 따라 메시지 분리
		String msgType = (String) jsonObject.get("type");
		System.out.println("SocketHandler handleTextMessage 2 type : " + msgType);
		
		switch (msgType) {
		case "message":
			jsonUser = new JSONObject(sessionUserMap);
			System.out.println("SocketHandler handleTextMessage message 1 jsonUser : " + jsonUser);
			
			String yourName = (String) jsonObject.get("yourName");
			System.out.println("SocketHandler handleTextMessage message 2 yourName : " + yourName);
			
			//브로드캐스팅 : 전체전송
			if(yourName.equals("ALL")) {
				for(String key : sessionMap.keySet()) {
					WebSocketSession wss = sessionMap.get(key);
					try {
						System.out.println("SocketHandler handleTextMessage message 2-1 key : " + key);
						System.out.println("SocketHandler handleTextMessage message 2-1 wss : " + wss);
						wss.sendMessage(new TextMessage(jsonObject.toJSONString()));
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			}
			//단일전송
			else {
				//대상 전송
				System.out.println("SocketHandler handleTextMessage message 2-2 yourName : " + yourName);
				WebSocketSession wss1 = sessionMap.get(yourName);
				try {
					wss1.sendMessage(new TextMessage(jsonObject.toJSONString()));
				} catch (Exception e) {
					e.printStackTrace();
				}
				//셀프 전송(view의 통일성을 위해 하는듯...)
				String meName = (String) jsonObject.get("sessionId");
				WebSocketSession wss2 = sessionMap.get(meName);
				System.out.println("SocketHandler handleTextMessage message 2-2 meName : " + meName);
				try {
					wss2.sendMessage(new TextMessage(jsonObject.toJSONString()));
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			break;
		case "userSave":
			// sessionUserMap에 등록
			String sessionId = (String) jsonObject.get("sessionId");
			String userName = (String) jsonObject.get("userName");
			String saveStatus = (String) jsonObject.get("saveStatus");
			
			//신규 등록
			if(saveStatus.equals("Create")) {
				sessionUserMap.put(sessionId, userName);
				System.out.println("===================3-1=======================");
				System.out.println("= sessionUserMap 저장/조회 하여					=");
				System.out.println("= arrayJsonUser에 JSONObject jsonUser로 변환	=");
				System.out.println("=  1. type : userSave						=");
				System.out.println("=  2. sessionId : sessionId					=");
				System.out.println("=  3. userName : userName					=");
				System.out.println("=============================================");
			}
			//그 외 작업(비정상접근) : 삭제
			else {
				System.out.println("SocketHandler handleTextMessage message 3-2 session.getId() : " + session.getId());
				
				//웹소켓 종료
				sessionMap.remove(session.getId());
				sessionUserMap.remove(session.getId());
			}
			
			//모두(신입)에게 전달할 모든 접속자들 정보 생성
			JSONArray arrayJsonUser = new JSONArray();
			System.out.println("SocketHandler handleTextMessage message 4");
			Iterator<String> mapIter = sessionUserMap.keySet().iterator();
			System.out.println("SocketHandler handleTextMessage message 5");
			while(mapIter.hasNext()) {
				String key = mapIter.next();
				String value = sessionUserMap.get(key);
				
				System.out.println("SocketHandler handleTextMessage message 6 key : " + key);
				System.out.println("SocketHandler handleTextMessage message 6 value : " + value);
				
				jsonUser = new JSONObject();
				jsonUser.put("type", "userSave");
				jsonUser.put("sessionId", key);
				jsonUser.put("userName", value);
				arrayJsonUser.add(jsonUser);
			}
			System.out.println("SocketHandler handleTextMessage message 7 arrayJsonUser : " + arrayJsonUser);
			
			// 모두에게 접속자 등록하라고 전송
			// 이미 되어 있으면 하지 말고~
			for(String key : sessionMap.keySet()) {
				WebSocketSession wss = sessionMap.get(key);
				try {
					wss.sendMessage(new TextMessage(arrayJsonUser.toJSONString()));
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			break;
		case "userDelete":
			
			break;
		default:
			break;
		}
	}

	// 3. 웹소켓이 종료되면 동작
	@Override
	public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
		// TODO Auto-generated method stub
		System.out.println("SocketHandler afterConnectionClosed S");
		
		// 맵에서 연결된 세션 삭제
		sessionMap.remove(session.getId());
		
		super.afterConnectionClosed(session, status);
	}
	
	// JSON -> Object : handleTextMessage으로 주고받는 통신이 JSON형태로 들어온다
	private static JSONObject jsonToObjectParse(String jsonStr) {
		JSONParser parser = new JSONParser();
		JSONObject jsonObj = null;
		try {
			jsonObj = (JSONObject) parser.parse(jsonStr);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return jsonObj;
	}
}
