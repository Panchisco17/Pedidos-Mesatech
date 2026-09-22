package cl.duoc.ms_pedidos360_orders.controller;

import cl.duoc.ms_pedidos360_orders.model.Solicitud;
import cl.duoc.ms_pedidos360_orders.service.SolicitudService;
import org.springframework.web.bind.annotation.*;

@RestController
public class SolicitudController {
    
    private final SolicitudService service;

    public SolicitudController(SolicitudService service) {
        this.service = service;
    }

    // Versión 1 de la API
    @PutMapping("/v1/solicitudes/{id}/estado")
    public Solicitud actualizarEstadoV1(@PathVariable Long id, @RequestBody String estado) {
        return service.actualizarEstado(id, estado);
    }

    // Versión 2 de la API
    @PutMapping("/v2/solicitudes/{id}/estado")
    public Solicitud actualizarEstadoV2(@PathVariable Long id, @RequestBody EstadoDTO dto) {
        return service.actualizarEstado(id, dto.getEstado());
    }
    
    public static class EstadoDTO {
        private String estado;
        public String getEstado() { return estado; }
        public void setEstado(String estado) { this.estado = estado; }
    }

    @PostMapping("/v1/solicitudes")
    public Solicitud crearSolicitud(@RequestBody Solicitud nuevaSolicitud) {
        return service.crearSolicitud(nuevaSolicitud);
}
}