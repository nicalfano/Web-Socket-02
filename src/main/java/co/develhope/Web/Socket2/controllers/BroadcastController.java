package co.develhope.Web.Socket2.controllers;

import co.develhope.Web.Socket2.entities.ClientMessageDTO;
import co.develhope.Web.Socket2.entities.MessageDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController()
public class BroadcastController {
    @Autowired
    private SimpMessagingTemplate simpMessagingTemplate;
    @PostMapping
    @RequestMapping("/broadcast-message")
    public ResponseEntity notifica(@RequestBody MessageDTO payload){
        simpMessagingTemplate.convertAndSend("/topic/broadcast",payload);
        return  ResponseEntity.status(HttpStatus.OK).build();
    }
    @MessageMapping("/client-message")
    @SendTo("/topic/broadcast")
    public MessageDTO messageClient(@RequestBody ClientMessageDTO payload){
        System.out.println("Arrived something on /app/client-message "+payload.toString());
        return new MessageDTO(payload.getClientName(), payload.getClientAlert(), payload.getClientMsg());
    }
}
