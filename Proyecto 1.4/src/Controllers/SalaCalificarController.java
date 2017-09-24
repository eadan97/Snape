package Controllers;

import Models.Reserva;
import Models.Sala;
import Utils.Utils;
import Views.Main;
import javafx.event.ActionEvent;
import javafx.scene.control.TextField;

public class SalaCalificarController {

    public TextField txtCodigo;
    public TextField txtNota;

    public void calificarSala(ActionEvent actionEvent) throws Exception {
        String codigo=txtCodigo.getText();
        String nota=txtNota.getText();
        if (!Utils.validarCodigoFormato(codigo)
                ||!Utils.validarNumero(nota)
                ||Utils.numeroMayorQue(nota,100)
                ||Utils.numeroMenorQue(nota,1))
        {
            Utils.mostrarError("Error","Error en los datos ingresados","Revise los datos ingresados!");
            return;
        }
        String[] codigoPicado=txtCodigo.getText().split("-");
        String idSala=codigoPicado[0]+"-"+codigoPicado[1];
        int idReserva=Integer.parseInt(codigoPicado[2]);
        int idEstudiante=Integer.parseInt(codigoPicado[3]);
        if (validarCodigo(idSala,idReserva,idEstudiante)) {
            for (Sala sala : Main.getInstance().salas.getLista()) {
                if (sala.getId().equals(idSala))
                    sala.agregarCalificacion(Integer.parseInt(nota), codigo);
            }
            txtCodigo.setText("");
            txtNota.setText("");
            Main.getInstance().salas.saveInXML();
        }else {
            Utils.mostrarError("Error","Error en los datos ingresados","Revise los datos ingresados!");
        }
}

    private boolean validarCodigo(String idSala, int idReserva, int idEstudiante) {
        for(Reserva reserva:Main.getInstance().reservas.getLista()){
            if (reserva.getIdSala().equals(idSala)
                    &&reserva.getId()==idReserva
                    &&reserva.getIdOrganizador()==idEstudiante)
                return true;
        }
        return false;
    }


}
