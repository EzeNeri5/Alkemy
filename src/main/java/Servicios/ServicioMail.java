package Servicios;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.scheduling.annotation.Async;

@Service
public class ServicioMail {

    @Autowired
    private JavaMailSender sm;
 
    @Async
    public void enviar(String cuerpo, String titulo, String mail){
        
        SimpleMailMesage mensaje = new SimpleMailMessage();
        mensaje.setTo(mail);
        mensaje.setFrom("mundo@disney.com");
        mensaje.setSubject(titulo);
        mensaje.setText(cuerpo);
        
        sm.send(mensaje);
    }
}
