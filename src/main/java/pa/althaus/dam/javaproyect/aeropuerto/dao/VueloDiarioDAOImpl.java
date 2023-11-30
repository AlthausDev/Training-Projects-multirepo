package pa.althaus.dam.javaproyect.aeropuerto.dao;

import pa.althaus.dam.javaproyect.aeropuerto.dao.VueloDiarioDAO;
import pa.althaus.dam.javaproyect.aeropuerto.model.VueloDiario;

import java.util.List;

public class VueloDiarioDAOImpl implements VueloDiarioDAO {

    private List<VueloDiario> vuelosDiarios;

    @Override
    public void createVueloDiario(VueloDiario vueloDiario) {
        vuelosDiarios.add(vueloDiario);
    }

    @Override
    public VueloDiario readVueloDiario(String codigoVuelo) {
        //return vuelosDiarios.stream()
        //      .filter(vd -> vd.getCodigoVuelo().equals(codigoVuelo))
        //    .findFirst()
        //    .orElse(null);
        return new VueloDiario();
    }

    @Override
    public void updateVueloDiario(String codigoVuelo, VueloDiario nuevoInfo) {
        VueloDiario vueloDiario = readVueloDiario(codigoVuelo);
        if (vueloDiario != null) {
            vueloDiario.setFechaVuelo(nuevoInfo.getFechaVuelo());
            vueloDiario.setHoraSalidaReal(nuevoInfo.getHoraSalidaReal());
            vueloDiario.setHoraLlegadaReal(nuevoInfo.getHoraLlegadaReal());
            vueloDiario.setPlazasOcupadas(nuevoInfo.getPlazasOcupadas());
            vueloDiario.setPrecioVuelo(nuevoInfo.getPrecioVuelo());
        }
    }

    @Override
    public void deleteVueloDiario(String codigoVuelo) {
        //vuelosDiarios.removeIf(vd -> vd.getCodigoVuelo().equals(codigoVuelo));
    }

    @Override
    public List<VueloDiario> obtenerTodosVuelosDiarios() {
        return vuelosDiarios;
    }
}