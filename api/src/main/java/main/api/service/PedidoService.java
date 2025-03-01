package main.api.service;

import main.api.model.Pedido;
import main.api.model.StatusPedido;
import main.api.repository.ItemPedidoRepository;
import main.api.repository.PedidoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class PedidoService {
    @Autowired
    private PedidoRepository pedidoRepository;
    @Autowired
    private ItemPedidoRepository itemPedidoRepository;
    // Criar Pedido
    public Pedido criarPedido(Pedido pedido) {
        double total = pedido.getItens().stream()
                .mapToDouble(item -> item.getPreco() * item.getQuantidade())
                .sum();
        pedido.setTotal(total);
        pedido.setStatus(StatusPedido.PENDENTE);
        pedido.setDataCriacao(java.time.LocalDateTime.now());
        pedido.setDataAtualizacao(java.time.LocalDateTime.now());

        return pedidoRepository.save(pedido);
    }

    // Listar Todos os Pedidos
    public List<Pedido> listarPedidos() {
        return pedidoRepository.findAll();
    }

    // Obter Detalhes de um Pedido
    public Optional<Pedido> obterPedido(UUID id) {
        return pedidoRepository.findById(id);
    }

    // Atualizar Status de um Pedido
    public Optional<Pedido> atualizarStatus(UUID id, StatusPedido status) {
        Optional<Pedido> pedido = pedidoRepository.findById(id);
        if (pedido.isPresent()) {
            Pedido p = pedido.get();
            p.setStatus(status);
            p.setDataAtualizacao(java.time.LocalDateTime.now());
            pedidoRepository.save(p);
            return Optional.of(p);
        }
        return Optional.empty();
    }

    public boolean deletarPedido(UUID id) {
        if (pedidoRepository.existsById(id)) {
            pedidoRepository.deleteById(id);
            return true;
        }
        return false;
    }
}
