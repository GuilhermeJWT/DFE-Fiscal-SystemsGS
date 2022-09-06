package br.com.systemsgs.controller;

import br.com.systemsgs.model.Empresa;
import br.com.systemsgs.service.EmpresaService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping(value = "/api/v1/empresa")
public class EmpresaController {

    private final EmpresaService empresaService;

    public EmpresaController(EmpresaService empresaService) {
        this.empresaService = empresaService;
    }

    @PostMapping(value = "/salvar", produces = {"application/json","application/xml","application/x-yaml"})
    public ResponseEntity<Empresa> salvarEmpresa(@Valid @RequestBody Empresa empresa){
        var empresaSalva = empresaService.salvar(empresa);
        return ResponseEntity.ok().body(empresaSalva);
    }

    @GetMapping(value = "/findAll", produces = {"application/json","application/xml","application/x-yaml"})
    public ResponseEntity<?> listarTodas(){
        var listaEmpresa = empresaService.listarEmpresas();
        return ResponseEntity.ok().body(listaEmpresa);
    }

    @GetMapping(value = "/{id}", produces = {"application/json","application/xml","application/x-yaml"})
    public ResponseEntity<Empresa> findById(@PathVariable Long id){
        var empresa = empresaService.findById(id);
        return ResponseEntity.ok().body(empresa);
    }

    @DeleteMapping(value = "/delete/{id}", produces = {"application/json","application/xml","application/x-yaml"})
    public ResponseEntity<?> delete(@PathVariable Long id){
        empresaService.deletar(id);
        return ResponseEntity.ok().body("Empresa Removida com Sucesso!");
    }

}
