package com.vandersoncamp.transporte.repository;import com.vandersoncamp.transporte.model.Cliente;import org.junit.Test;import org.junit.runner.RunWith;import org.springframework.beans.factory.annotation.Autowired;import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;import org.springframework.test.context.TestPropertySource;import org.springframework.test.context.jdbc.Sql;import org.springframework.test.context.junit4.SpringRunner;import java.util.Optional;import static org.assertj.core.api.Assertions.assertThat;@Sql(value = "/load-database.sql", executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)@Sql(value = "/clean-database.sql", executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)@RunWith(SpringRunner.class)@DataJpaTest@TestPropertySource("classpath:application-test.properties")public class ClienteRepositoryTest {    @Autowired    private ClienteRepository clienteRepository;    @Test    public void clienteFindByNomeTrue() {        Iterable<Cliente> iterable = clienteRepository.findByNome("Cliente válido");        iterable.forEach(cliente ->                assertThat(cliente.getNome()).isEqualTo("Cliente válido")        );    }    @Test    public void clienteFindByNomeFalse() {        Iterable<Cliente> iterable = clienteRepository.findByNome("Cliente null");        iterable.forEach(cliente ->                assertThat(cliente.getNome()).isNullOrEmpty()        );    }    @Test    public void clienteFindByDocumentoTrue() {        Optional<Cliente> optional = clienteRepository.findByDocumento("89576673933");        assertThat(optional.isPresent()).isTrue();        Cliente cliente = optional.get();        assertThat(cliente.getId()).isEqualTo(1L);        assertThat(cliente.getNome()).isEqualTo("Cliente válido");        assertThat(cliente.getDocumento()).isEqualTo("89576673933");        assertThat(cliente.getEmail()).isEqualTo("cliente01.teste@gmail.com");    }    @Test    public void clienteFindByDocumentoFalse() {        Optional<Cliente> optional = clienteRepository.findByDocumento("99999999999");        assertThat(optional.isPresent()).isFalse();    }}