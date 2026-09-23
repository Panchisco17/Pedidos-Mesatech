package cl.duoc.ms_pedidos360_orders.service;

import cl.duoc.ms_pedidos360_orders.model.Solicitud;
import cl.duoc.ms_pedidos360_orders.repository.SolicitudRepository;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class SolicitudService {

    private final SolicitudRepository repository;

    public SolicitudService(SolicitudRepository repository) {
        this.repository = repository;
    }

    public List<Solicitud> obtenerTodas() {
        return (List<Solicitud>) repository.findAll();
    }

    public Solicitud obtenerPorId(Long id) {
        return repository.findById(id).orElseThrow(() -> new RuntimeException("Solicitud no encontrada"));
    }

    public Solicitud crearSolicitud(Solicitud solicitud) {
        return repository.save(solicitud);
    }

    public Solicitud actualizarSolicitudCompleta(Long id, Solicitud solicitudActualizada) {
        Solicitud existente = obtenerPorId(id);
        solicitudActualizada.setId(existente.getId()); 
        return repository.save(solicitudActualizada);
    }

    public void eliminarSolicitud(Long id) {
        repository.deleteById(id);
    }

    public Solicitud actualizarEstado(Long id, String nuevoEstado) {
        Solicitud existente = obtenerPorId(id);
        existente.setEstado(nuevoEstado); 
        return repository.save(existente);
    }
}