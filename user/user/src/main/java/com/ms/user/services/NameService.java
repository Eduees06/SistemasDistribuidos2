package com.ms.user.services;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import com.ms.user.models.UserModel;
import java.util.Arrays;

@Service
public class NameService {

    private final String API_URL = "https://www.behindthename.com/api/lookup.json";
    private final String API_KEY = "ed875533531";

    public void setCountryAndMeaning(UserModel user) {
        // Cria a URL com o nome do usuário e a chave da API
        String url = API_URL + "?key=" + API_KEY + "&name=" + user.getName();

        // Cria o RestTemplate
        RestTemplate restTemplate = new RestTemplate();

        // Faça a chamada para a API
        NameApiResponse[] response = restTemplate.getForObject(url, NameApiResponse[].class);

        // Verifica se a resposta não é nula e contém dados
        if (response != null && response.length > 0) {
            NameApiResponse nameApiResponse = response[0]; // Pega o primeiro resultado (se houver)

            // Atribui o gênero e os usos ao modelo do usuário
            user.setGender(nameApiResponse.getGender());
            user.setUsages(mapUsages(nameApiResponse.getUsages()));
        } else {
            // Se não houver resposta ou se os dados estiverem ausentes
            user.setGender("Desconhecido");
            user.setUsages(new String[]{"Uso não encontrado"});
        }
    }

    private String[] mapUsages(NameApiResponse.Usage[] usages) {
        // Mapeia os usos para um formato simples de string
        return Arrays.stream(usages)
                .map(usage -> usage.getUsage_full())
                .toArray(String[]::new);
    }
}