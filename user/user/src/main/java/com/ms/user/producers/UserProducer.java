package com.ms.user.producers;

import com.ms.user.dtos.EmailDto;
import com.ms.user.models.UserModel;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class UserProducer {

    final RabbitTemplate rabbitTemplate;

    public UserProducer(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    @Value(value = "${broker.queue.email.name}")
    private String routingKey;

    public void publishMessageEmail(UserModel userModel) {
        var emailDto = new EmailDto();
        emailDto.setUserId(userModel.getUserId());
        emailDto.setEmailTo(userModel.getEmail());
        emailDto.setSubject("Cadastro realizado com sucesso!");

        // Texto do email atualizado para incluir as novas informações
        String emailText = userModel.getName() + ", seja bem-vindo(a)! \n" +
                           "Agradecemos o seu cadastro, saiba a origem do seu nome \n\n" +
                           "Informações adicionais: \n" +
                           "Gênero: " + userModel.getGender() + "\n" +
                           "Origem do nome: \n";

        // Adiciona todos os usos ao email
        for (String usage : userModel.getUsages()) {
            emailText += "- " + usage + "\n";
        }

        emailDto.setText(emailText);

        // Envia a mensagem para a fila do RabbitMQ
        rabbitTemplate.convertAndSend("", routingKey, emailDto);
    }
}