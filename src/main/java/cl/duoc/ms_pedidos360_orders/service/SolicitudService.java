package cl.duoc.ms_pedidos360_orders.service;

import cl.duoc.ms_pedidos360_orders.model.Solicitud;
import cl.duoc.ms_pedidos360_orders.repository.SolicitudRepository;
import org.springframework.stereotype.Service;

@Service
public class SolicitudService {
    
    private final SolicitudRepository repository;

    public SolicitudService(SolicitudRepository repository) {
        this.repository = repository;
    }

    public Solicitud actualizarEstado(Long id, String nuevoEstado) {
        Solicitud solicitud = repository.findById(id)
            .orElseThrow(() -> new RuntimeException("Solicitud no encontrada"));

        // Regla de negocio estricta para la evaluación
        if (nuevoEstado.equals("RESUELTA") && !solicitud.getEstado().equals("EN_PROCESO")) {
            throw new IllegalStateException("Transición inválida: Debe estar EN_PROCESO para ser RESUELTA");
        }
        
        solicitud.setEstado(nuevoEstado);
        return repository.save(solicitud);
    }

    public Solicitud crearSolicitud(Solicitud nuevaSolicitud) {
        nuevaSolicitud.setEstado("CREADA"); // El ciclo inicia obligatoriamente aquí
        return repository.save(nuevaSolicitud);
}

    
}