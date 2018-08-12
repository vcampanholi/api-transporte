package com.vandersoncamp.transporte.service.cliente;import com.vandersoncamp.transporte.exception.BusinessException;import com.vandersoncamp.transporte.model.Cliente;import com.vandersoncamp.transporte.repository.ClienteLinhaRepository;import com.vandersoncamp.transporte.repository.ClienteRepository;import org.junit.Before;import org.junit.Test;import org.junit.runner.RunWith;import org.springframework.boot.test.mock.mockito.MockBean;import org.springframework.test.context.junit4.SpringRunner;import java.util.Optional;import static org.junit.jupiter.api.Assertions.assertEquals;import static org.junit.jupiter.api.Assertions.assertThrows;import static org.mockito.Mockito.verify;import static org.mockito.Mockito.when;@RunWith(SpringRunner.class)public class ClienteServiceTest {    @MockBean    private ClienteRepository repository;    @MockBean    private ClienteLinhaRepository clienteLinhaRepository;    private ClienteService service;    private Cliente cliente;    private Cliente getCliente() {        Cliente cliente = new Cliente();        cliente.setId(1L);        cliente.setDocumento("49765258216");        cliente.setEmail("email@email.com.br");        cliente.setNome("Teste cliente");        cliente.setTelefone(767678689L);        return cliente;    }    @Before    public void setUp() {        service = new ClienteService(repository);        cliente = getCliente();        when(repository.findById(cliente.getId())).thenReturn(Optional.of(new Cliente()));    }    @Test    public void clienteSave() {        service.save(cliente);        verify(repository).save(cliente);    }    @Test    public void clienteFindAll() {        service.findAll();        verify(repository).findAll();    }    @Test    public void clienteFindById() {        service.findById(cliente.getId());        verify(repository).findById(cliente.getId());    }    @Test    public void clienteExistsById() {        service.existsById(cliente.getId());        verify(repository).existsById(cliente.getId());    }    @Test    public void clienteDelete() {        service.remove(cliente.getId());        verify(repository).deleteById(cliente.getId());    }    @Test    public void clienteFindByNome() {        service.findByNome(cliente.getNome());        verify(repository).findByNome(cliente.getNome());    }    @Test    public void clienteFindByDocumento() {        service.findByDocumento(cliente.getDocumento());        verify(repository).findByDocumento(cliente.getDocumento());    }    @Test    public void clienteValidateDocumentoDuplicado() {        Cliente novoCliente = getCliente();        novoCliente.setId(2L);        when(repository.findByDocumento(cliente.getDocumento())).thenReturn(Optional.of(novoCliente));        BusinessException thrown = assertThrows(BusinessException.class, () ->                service.save(cliente)        );        assertEquals("Existe outro cliente com o mesmo cpf", thrown.getMessage());    }    @Test    public void clienteValidateDigitosCpf() {        Cliente novoCliente = getCliente();        novoCliente.setDocumento("1234");        BusinessException thrown = assertThrows(BusinessException.class, () ->                service.save(novoCliente)        );        assertEquals("Cpf inválido ou inexistente", thrown.getMessage());    }    @Test    public void clienteValidateCpfAlfaNumerico() {        Cliente novoCliente = getCliente();        novoCliente.setDocumento("abc123");        BusinessException thrown = assertThrows(BusinessException.class, () ->                service.save(novoCliente)        );        assertEquals("Cpf inválido ou inexistente", thrown.getMessage());    }    @Test    public void clienteValidateCpfMaxDigitos() {        Cliente novoCliente = getCliente();        novoCliente.setDocumento("0004755292948");        BusinessException thrown = assertThrows(BusinessException.class, () ->                service.save(novoCliente)        );        assertEquals("Cpf inválido ou inexistente", thrown.getMessage());    }}