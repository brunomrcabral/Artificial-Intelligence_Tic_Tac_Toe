import java.util.Scanner;

public class Minimax {
    public static void min(int primeiro, Matrix galomatriz, int escolha) {
        MiniMax game1 = new MiniMax();
        int k = 1;
        Scanner ler = new Scanner(System.in);
        if (primeiro == 1) {
            System.out.println("Sua vez de jogar: 1 a 9");
            for (int i = 0; i < 3; i++) {
                for (int j = 0; j < 3; j++) {
                    System.out.print(k + "|");
                    k++;
                }
                System.out.println();

            }
            escolha = ler.nextInt();
            game1.search(galomatriz, 2, escolha);
        } else game1.search(galomatriz, 1, escolha);
    }

    public static class MiniMax {
        Matrix jogador2_nos[] = new Matrix[9];
        int jogador2_minimo[] = new int[700];
        int jogador2_index = 0;
        int nos_gerados = 0;
        Scanner ler = new Scanner(System.in);

        void search(Matrix m, int jogador, int jog) {
            if (m.ocupados == 9) {
                Gerais.sucesso(0,nos_gerados);
                resetVars();

            }
            else {
                if (jog != 0) {
                    Matrix newmatrix = new Matrix(m.matriz);
                    newmatrix.play(jogador, jog);
                    nos_gerados++;
                    m.lista_nos.add(newmatrix);
                    jog = 0;
                    search(newmatrix, 1, jog);
                } else if (m.ocupados > 0) { //Gerar todas as hipoteses
                    int posicao = 0;
                    for (int c = 0; c < 3; c++) {
                        for (int d = 0; d < 3; d++) {
                            ++posicao;
                            if (m.matriz[c][d] == 0) {
                                Matrix newmatrix = new Matrix(m.matriz);
                                newmatrix.play(jogador, posicao);
                                nos_gerados++;
                                m.lista_nos.add(newmatrix);
                            }
                        }
                    }
                } else { // 1 jogada
                    Matrix newmatrix;
                    newmatrix = new Matrix(m.matriz);
                    newmatrix.play(jogador, 5);
                    nos_gerados++;
                    m.lista_nos.add(newmatrix);
                }


                // Lado de jogo
                if (jogador == 1) {
                    jogador2_nos = new Matrix[m.lista_nos.size()];
                    jogador2_minimo = new int[m.lista_nos.size()];

                    // Resultados
                    while (!m.lista_nos.isEmpty()) {
                        search(m.lista_nos.poll(), 2, jog); //Lista
                    }
                    // Melhor resultado  , max dos minimos
                    int goodIndex = Gerais.getMaxIndex(jogador2_minimo);
                    jogador2_nos[goodIndex].printMatriz();
                    if (Gerais.getScore(jogador2_nos[goodIndex], 1) == 1000) {
                        Gerais.sucesso(1, nos_gerados);
                        resetVars();
                    } else {
                        if (jogador2_nos[goodIndex].ocupados < 9) {
                            resetVars();
                            boolean valid = false;
                            while (!valid) {
                                valid = jogador2_nos[goodIndex].play(2, ler.nextInt());
                                if (!valid) {
                                    int k = 0;
                                    System.out.println("Posição já ocupada");
                                }
                            }
                            if (Gerais.getScore(jogador2_nos[goodIndex], 2) == 1000) {
                                Gerais.sucesso(2, nos_gerados);
                                resetVars();
                            } else search(jogador2_nos[goodIndex], 1, jog);
                        } else {
                            Gerais.sucesso(0, nos_gerados);
                            resetVars();
                        }
                    }
                } else {
                    jogador2_nos[jogador2_index] = m;

                    // Resultado minimo
                    int[] res = new int[m.lista_nos.size()];
                    int c = 0;
                    while (!m.lista_nos.isEmpty()) { // Decisao
                        Matrix mpoll = m.lista_nos.poll();
                        res[c++] = Gerais.getScore(mpoll, 1) - Gerais.getScore(mpoll, 2); // Melhor numero de opçoes que garantem a vitoria e ao mesmo tempo garante a derrota do adeversario
                    }
                    jogador2_minimo[jogador2_index] = Gerais.getMinValue(res);
                    jogador2_index++;
                }
            }
        }
        //--------------------------------------------------------------------
        void resetVars() {
            jogador2_index = 0;
        }
    }
}
