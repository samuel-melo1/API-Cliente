package com.jpaexemplos.ProjetoJpa.controller;

import java.util.List;
import com.jpaexemplos.ProjetoJpa.model.Cliente;
import com.jpaexemplos.ProjetoJpa.repository.ClienteRepository;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequestMapping("/clientes")
public class ClienteController {

    private ClienteRepository clienteRepository;

    ClienteController(ClienteRepository clienteRepository) {
        this.clienteRepository = clienteRepository;
    }
    
    @PostMapping
    @ResponseStatus(code = HttpStatus.CREATED)
    public Cliente cadastrarClientes(@RequestBody Cliente cliente){
        return clienteRepository.save(cliente);
    }
    @GetMapping
    public List<Cliente> listarClientes(){
        return clienteRepository.findAll();
    }
    @GetMapping("/{id}")
    public Cliente buscarPorId(@PathVariable long id){
        var clienteOptional = clienteRepository.findById(id);
        if(clienteOptional.isEmpty()){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
        return clienteOptional.get();
    }
    @DeleteMapping("{id}")
    @ResponseStatus(code = HttpStatus.NO_CONTENT)
    public void excluirPorId(@PathVariable long id){
        var clienteOptional = clienteRepository.findById(id);
        if(clienteOptional.isEmpty()){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
        clienteRepository.delete(clienteOptional.get());
    }
    @PutMapping("/{id}")
    public Cliente atualizarCliente(@PathVariable long id, @RequestBody Cliente cliente){
        var clienteOptional = clienteRepository.findById(id);
        if(clienteOptional.isEmpty()){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
        cliente.setId(id);
        return clienteRepository.save(cliente);
    }


}
