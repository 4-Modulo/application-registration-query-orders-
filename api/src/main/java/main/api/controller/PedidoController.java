package main.api.controller;
import main.api.Response.MessageResponse;
import main.api.model.Pedido;
import main.api.model.StatusPedido;
import main.api.service.PedidoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/pedidos")
public class PedidoController {

    @Autowired
    private PedidoService pedidoService;

    // Criar Pedido
    @PostMapping
    public ResponseEntity<Pedido> criarPedido(@RequestBody Pedido pedido) {
        Pedido novoPedido = pedidoService.criarPedido(pedido);
        return ResponseEntity.status(HttpStatus.CREATED).body(novoPedido);
    }

    // Listar Todos os Pedidos
    @GetMapping
    public ResponseEntity<List<Pedido>> listarPedidos() {
        List<Pedido> pedidos = pedidoService.listarPedidos();
        return ResponseEntity.ok(pedidos);
    }

    // Obter Detalhes de um Pedido
    @GetMapping("/{id}")
    public ResponseEntity<Pedido> obterPedido(@PathVariable UUID id) {
        Optional<Pedido> pedido = pedidoService.obterPedido(id);
        return pedido.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }
    // Atualizar Status de um Pedido
    @PatchMapping("/{id}")
    public ResponseEntity<MessageResponse> atualizarStatus(@PathVariable UUID id, @RequestBody StatusPedido status) {
        Optional<Pedido> pedido = pedidoService.atualizarStatus(id, status);
        return pedido.map(p -> ResponseEntity.ok(new MessageResponse("Pedido atualizado com sucesso", p.getStatus())))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }


    // Deletar Pedido
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarPedido(@PathVariable UUID id) {
        boolean deletado = pedidoService.deletarPedido(id);
        return deletado ? ResponseEntity.noContent().build() : ResponseEntity.notFound().build();
    }
}
