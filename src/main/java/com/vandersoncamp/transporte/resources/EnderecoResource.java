package com.vandersoncamp.transporte.resources;import com.vandersoncamp.transporte.model.Endereco;import com.vandersoncamp.transporte.service.endereco.EnderecoService;import org.springframework.beans.factory.annotation.Autowired;import org.springframework.http.HttpStatus;import org.springframework.http.MediaType;import org.springframework.http.ResponseEntity;import org.springframework.web.bind.annotation.*;import org.springframework.web.client.RestTemplate;import java.util.Optional;@RestController@RequestMapping("enderecos")public class EnderecoResource {    @Autowired    private EnderecoService service;    @GetMapping(produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})    public Iterable<Endereco> findAll() {        return service.findAll();    }    @PostMapping    @ResponseStatus(HttpStatus.CREATED)    public Endereco insert(@RequestBody Endereco endereco) {        return service.save(endereco);    }    @GetMapping("{id}")    public Endereco findById(@PathVariable("id") Long id) {        return service.findById(id);    }    @PutMapping("{id}")    public ResponseEntity<Endereco> update(@PathVariable("id") Long id, @RequestBody Endereco endereco) {        if (!id.equals(endereco.getId())) {            return ResponseEntity.badRequest().build();        }        return ResponseEntity.ok(service.save(endereco));    }    @DeleteMapping("{id}")    @ResponseStatus(HttpStatus.NO_CONTENT)    public void delete(@PathVariable("id") Long id) {        service.remove(id);    }    @GetMapping("busca")    public Iterable<Endereco> findByLogradouro(@RequestParam("logradouro") String logradouro) {        return service.findByLogradouro(logradouro);    }    @GetMapping("cep/{cep}")    public Endereco findByCep(@PathVariable("cep") String cep) {        Endereco endereco = service.findByCep(cep);        return Optional.ofNullable(endereco).orElse(findByViaCep(cep));    }    private Endereco findByViaCep(String value) {        String cep = value.trim();        RestTemplate restTemplate = new RestTemplate();        return restTemplate.getForObject("https://viacep.com.br/ws/" + cep + "/json/", Endereco.class);    }}