package com.example;

public class Jogo {
    protected Monte monte = new Monte();
    protected Jogador jogador = new Jogador();
    protected Computador computador = new Computador();

    public Jogo() {
        monte.embaralhar();
    }

    public Carta distribuirCartaParaJogador(Jogador jogador) {
        if (jogador.parou()) {
            return null;
        }
        Carta carta = monte.virar();
        if (carta != null) {
            jogador.receberCarta(carta);
        }
        return carta;
    }

    public Carta distribuirCartaParaComputador(Computador computador) {
        if (computador.parou()) {
            return null;
        }
        Carta carta = monte.virar();
        if (carta != null) {
            computador.receberCarta(carta);
        }
        return carta;
    }

    public boolean acabou() {
        return (jogador.getPontos() > 21) || (computador.getPontos() > 21) || (jogador.parou() && computador.parou());
    }

    public String resultado() {
        if (jogador.getPontos() == 21) {
            return "O jogador ganhou.";
        } else if (computador.getPontos() == 21) {
            return "O computador ganhou.";
        }
        if (jogador.getPontos() == computador.getPontos()) {
            return "Empate.";
        } else if (jogador.getPontos() > 21 && computador.getPontos() < 21) {
            return "O computador ganhou.";
        } else if (jogador.getPontos() < 21 && computador.getPontos() > 21) {
            return "O jogador ganhou.";
        } else if (jogador.getPontos() < 21 && computador.getPontos() < 21) {
            if (jogador.getPontos() > computador.getPontos()) {
                return "O jogador ganhou.";
            } else if (jogador.getPontos() < computador.getPontos()) {
                return "O computador ganhou.";
            }
        }
        return "Empate.";
    }

    public Computador getComputador() {
        return computador;
    }

    public Jogador getJogador() {
        return jogador;
    }
}
