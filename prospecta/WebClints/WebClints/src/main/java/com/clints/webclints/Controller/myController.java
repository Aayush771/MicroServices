package com.clints.webclints.Controller;

import com.clints.webclints.Entity.AnsDTO;
import com.clints.webclints.Entity.Data;
import com.clints.webclints.Entity.Entry;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

@RestController
@Service
public class myController {
    @Autowired
    private WebClient.Builder webClientBuilder;
    @Autowired
    WebClient webClient;

    public void test(){
        String out = webClient
                .get()
                .uri("https://api.publicapis.org/entries")
                .retrieve()
                .bodyToMono(String.class)
                .block();

        System.out.println(out);
    }
    @GetMapping("/entries/{category}")
    public List<AnsDTO> getEntriesHandler(@PathVariable("category") String variable){
        Data data = webClientBuilder.build()
                   .get()
                   .uri("https://api.publicapis.org/entries")
                   .retrieve()
                   .bodyToMono(Data.class)
                   .block();
        System.out.println(data);
        assert data != null;
        List<Entry> entries = data.getEntries();

        List<AnsDTO> list = new ArrayList<>();
        for(Entry entry:entries){
            String[] cate = entry.getCategory().toLowerCase(Locale.ROOT).split(" ");

            if(variable.equals(cate[0])){
                list.add(new AnsDTO(entry.getApi(), entry.getDescription()));
            }
        }
        return list;
    }
}
