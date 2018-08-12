package com.vandersoncamp.transporte.resources;import com.vandersoncamp.transporte.dto.LinhaDTO;import com.vandersoncamp.transporte.model.LinhaOnibus;import com.vandersoncamp.transporte.service.linhatransporte.LinhaDtoService;import com.vandersoncamp.transporte.service.linhatransporte.LinhaOnibusService;import com.vandersoncamp.transporte.utils.RestTemplateConverter;import org.springframework.beans.factory.annotation.Autowired;import org.springframework.core.ParameterizedTypeReference;import org.springframework.http.HttpMethod;import org.springframework.http.MediaType;import org.springframework.http.ResponseEntity;import org.springframework.web.bind.annotation.*;import java.util.List;import java.util.stream.Collectors;@RestController@RequestMapping("linha-onibus")public class LinhaOnibusResource {    @Autowired    private LinhaOnibusService service;    @Autowired    private LinhaDtoService linhaDtoService;    @Autowired    private RestTemplateConverter restTemplateConverter;    @GetMapping("/filter")    public Iterable<LinhaOnibus> findByNome(@RequestParam("nome") String nome) {        return service.findByNome(nome);    }    @GetMapping(value = "/consultar", produces = {MediaType.APPLICATION_JSON_VALUE})    public Iterable<LinhaOnibus> findAll() {        return service.findAll();    }    @PostMapping("/integrar")    public void integrarLinhas() {        List<LinhaOnibus> linhas = findByPoaTransporte();        service.integrarLinhaOnibus(linhas);    }    private List<LinhaOnibus> findByPoaTransporte() {        String url = "http://www.poatransporte.com.br/php/facades/process.php?a=nc&p=%&t=o";        ResponseEntity<List<LinhaDTO>> response = restTemplateConverter.messageConverter().exchange(                url, HttpMethod.GET, null, new ParameterizedTypeReference<List<LinhaDTO>>() {                });        return response.getBody().stream().map(dto ->                linhaDtoService.toEntityOnibus(dto)).collect(Collectors.toList());    }}