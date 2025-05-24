package com.example;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;

public class PrimaryController {

    @FXML
    private HBox mesaDoJogador;
    @FXML
    private HBox mesaDoComputador;
    @FXML
    private ImageView monte;

    @FXML
    private Label pontosMesa;
    @FXML
    private Label pontosJogador;
    @FXML
    private Label resultado;

    private Jogo jogo = new Jogo();

    public boolean turno() {
        if (!jogo.getJogador().parou()) {
            jogo.distribuirCartaParaJogador(jogo.getJogador());
        }
        if (!jogo.getComputador().parou()) {
            jogo.distribuirCartaParaComputador(jogo.getComputador());
        }
        return jogo.acabou();
    }

    public void atualizar() {
        pontosJogador.setText("Jogador: " + jogo.jogador.getPontos());
        pontosMesa.setText("Mesa: " + jogo.computador.getPontos());
        if (jogo.computador.getPontos() >= 21 || jogo.jogador.getPontos() >= 21) {
            parar();

        } else if (jogo.computador.getPontos() >= 16) {
            resultado.setText(jogo.resultado());
            parar();
        }
        mesaDoJogador.getChildren().clear();
        mesaDoComputador.getChildren().clear();

        jogo.jogador.getCartas().forEach((carta) -> mesaDoJogador.getChildren().add(imagemCarta(carta)));
        jogo.computador.getCartas().forEach((carta) -> mesaDoComputador.getChildren().add(imagemCarta(carta)));
    }

    public void novoJogo() {
        jogo = new Jogo();
        mesaDoJogador.getChildren().clear();
        mesaDoComputador.getChildren().clear();
        pontosJogador.setText("Jogador: 0");
        pontosMesa.setText("Mesa: 0");
        resultado.setText("");
    }

    public void pedirCarta() {
        if (!jogo.jogador.parou()) {
            jogo.distribuirCartaParaJogador(jogo.jogador);
            if (jogo.acabou()) {
                atualizar();
                resultado.setText(jogo.resultado());
            }
            atualizar();
        }
        if (!jogo.computador.parou()) {
            jogo.distribuirCartaParaComputador(jogo.computador);
            if (jogo.acabou()) {
                atualizar();
                resultado.setText(jogo.resultado());
            }
            atualizar();
        }
    }

    public void parar() {
        jogo.jogador.parar();
       while (jogo.computador.getPontos() <= 21) {
        jogo.distribuirCartaParaComputador(jogo.computador);
        atualizar();
       }
       atualizar();
       resultado.setText(jogo.resultado());
       
    }

    private ImageView imagemCarta(Carta carta) {
        return new ImageView(App.class.getResource(carta.imagePath()).toString());
    }

}
